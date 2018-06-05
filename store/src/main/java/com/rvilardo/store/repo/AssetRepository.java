package com.rvilardo.store.repo;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.rvilardo.store.bean.Asset;

@EnableScan
public interface AssetRepository extends CrudRepository<Asset, String> {
     
    List<Asset> findAll();
    
    Asset findById(String id);
}

