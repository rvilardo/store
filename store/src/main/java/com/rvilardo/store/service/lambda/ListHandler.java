package com.rvilardo.store.service.lambda;

import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.rvilardo.store.bean.Asset;

/**
 * Class responsible to list the available assets
 * @author rvilardo
 *
 */
@Service
public class ListHandler implements RequestHandler<String, Asset[]> {

//	private static final Logger LOGGER = Logger.getLogger(ListHandler.class);
//	private DynamoDBMapper dynamoMapper;

	public ListHandler() {
//		dynamoMapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());
	}

	/**
	 * 
	 * 
	 */
	@Override
	public Asset[] handleRequest(String input, Context context) {
		return retrieveAssetList();
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public Asset[] retrieveAssetList() {
//		final List<Asset> paginatedAssets = dynamoMapper.scan(Asset.class, new DynamoDBScanExpression());
//		if (paginatedAssets != null && !paginatedAssets.isEmpty()) {
//			LOGGER.info("assets list size=" + paginatedAssets.size());
//			final Asset[] result = paginatedAssets.toArray(new Asset[paginatedAssets.size()]);
//			return result;
//		}

		return new Asset[0];
	} 

}
