package com.rvilardo.store.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.Base64;
import com.amazonaws.util.IOUtils;
import com.rvilardo.store.bean.Asset;
import com.rvilardo.store.bean.AssetResponse;
import com.rvilardo.store.exception.NotFoundException;
import com.rvilardo.store.repo.AssetRepository;

@Service
public class AssetService {
	
	private static final Logger LOGGER = Logger.getLogger(AssetService.class);
	
	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private AmazonS3 s3client;
	
	@Value("${amazon.aws.s3.bucket}")
	private String bucketName;

	public AssetResponse retrieveAsset(String id) {
		
		Asset asset = assetRepository.findById(id);
		if (asset == null) {
			throw new NotFoundException("ID %s not found", id);
		}
		LOGGER.info("Download requested for=" + id + "::item retrieved from S3=" + asset.getFileName());

		S3Object o = s3client.getObject(bucketName, asset.getFileName());
		S3ObjectInputStream s3is = o.getObjectContent();
		
		AssetResponse response = new AssetResponse();
		try {
			String encodedString = Base64.encodeAsString(IOUtils.toByteArray(s3is));
			ObjectMetadata metadata = o.getObjectMetadata();

			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.parseMediaType(metadata.getContentType()));
	        headers.setContentLength(metadata.getContentLength());
	        headers.setContentDispositionFormData("attachment", asset.getFileName());
			
			response.setIsBase64Encoded(true);
			response.setStatusCode(200);
			response.setHeaders(headers);
			response.setContent(encodedString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}

	public Asset uploadAsset(String fileName, byte[] image) {
    	String id = UUID.randomUUID().toString();

    	LOGGER.info("Uploading file [" + fileName + "]");
    	//TODO Check if file already exists
		InputStream fileInputStream = new ByteArrayInputStream(image);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(image.length);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, fileInputStream, metadata);
		s3client.putObject(putObjectRequest);

    	Asset asset = new Asset();
    	asset.setId(id);
    	asset.setFileName(fileName);
    	asset.setSize(image.length);
		assetRepository.save(asset);
    	
		LOGGER.info("Uploading file [" + fileName + "] - success");
        return asset;

	}

	public List<Asset> retrieveAssetList() {
		List<Asset> assets = assetRepository.findAll();
		System.out.println("size=" + assets.size());
		return assets;
	}

}
