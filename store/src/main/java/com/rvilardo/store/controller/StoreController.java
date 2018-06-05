package com.rvilardo.store.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.Base64;
import com.rvilardo.store.bean.Asset;
import com.rvilardo.store.bean.AssetResponse;
import com.rvilardo.store.service.AssetService;

@RestController
@RequestMapping(value = "/store/{version}", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class StoreController {

	@Autowired
	private AssetService service;

	/**
	 * This method is used to retrieve the image for a requested asset. 
	 * An HTTP 404 error code is returned, if there is no asset
	 * 
	 * @param programId
	 * @param width
	 * @param height
	 * @param categoryPrecedence
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadAsset", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public ResponseEntity<byte[]> downloadAsset(@RequestParam String id) throws Exception {
        AssetResponse resp = service.retrieveAsset(id);
		return ResponseEntity.ok().headers(resp.getHeaders()).body(Base64.decode(resp.getContent()));
	}
	
	/**
	 * 
	 * @param fileName
	 * @param image
	 * @return
	 */
    @RequestMapping(value = "/uploadAsset", method = { RequestMethod.POST, RequestMethod.PUT }, consumes = MediaType.ALL_VALUE)
    public Asset uploadAsset(
        @RequestParam @NotEmpty(message = "Filename cannot be null or empty") String name, @RequestBody byte[] image) {
        return service.uploadAsset(name, image);
    }
    
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Asset> getAssets() {
		
    	return service.retrieveAssetList();
    	 //return Arrays.stream(listHandler.retrieveAssetList()).collect(Collectors.toList());
	}
}