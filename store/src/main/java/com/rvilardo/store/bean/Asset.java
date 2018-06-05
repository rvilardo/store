package com.rvilardo.store.bean;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Bean class to be returned with the asset information.
 * 
 * @author rvilardo
 *
 */
@JsonInclude(Include.NON_NULL)
@DynamoDBTable(tableName = "assets")
public class Asset {

	@DynamoDBHashKey
	private String id;
	private String fileName;
	private long size;
	
    /**
     * Getter method for id
     * @return the id
     */
	public String getId() {
		return id;
	}
	
	/**
     * Setter method for id
     * @param id the id to set
     */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
     * Getter method for fileName
     * @return the fileName
     */
	public String getFileName() {
		return fileName;
	}
	
	/**
     * Setter method for fileName
     * @param fileName the id to set
     */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
     * Getter method for size
     * @return the size
     */
	public long getSize() {
		return size;
	}
	
	/**
     * Setter method for size
     * @param size the size to set
     */
	public void setSize(long size) {
		this.size = size;
	}	
	
	/**
     * Retrieves the size in Kb
     * @return the size in kb
     */
	@DynamoDBIgnore
	@JsonIgnore
	public double getSizeInKb() {
		return (size / 1024);
	}

	/**
     * Retrieves the size in mb
     * @return the size in mb
     */
	@DynamoDBIgnore
	@JsonIgnore
	public double getSizeInMb() {
		return (getSizeInKb() / 1024);
	}

}
