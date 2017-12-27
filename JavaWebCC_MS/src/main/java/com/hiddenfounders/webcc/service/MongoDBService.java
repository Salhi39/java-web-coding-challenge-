package com.hiddenfounders.webcc.service;

import com.hiddenfounders.webcc.model.Shop;

import java.util.List;

/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 12:18 AM
 * @package com.hiddenfounders.webcc.service
 */


public interface MongoDBService {

    String ShopService();

    Shop create(Shop shop);

    Shop delete(String id);

    List<Shop> findAll();

    Shop findById(String id);

    Shop update(Shop todo);


}
