package com.hiddenfounders.webcc.service;

import com.hiddenfounders.webcc.dao.DAOUserImp;
import com.hiddenfounders.webcc.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 11:43 PM
 * @package com.hiddenfounders.webcc.service
 */



@Service
public class MongoDBServiceImp implements MongoDBService {

    @Autowired
    @Qualifier(value = "userRepository")
    private DAOUserImp daoUser;

    @Autowired
    @Qualifier(value = "shopRepository")
    private DAOUserImp daoShop;


    @Override
    public String ShopService() {
        return null;
    }

    @Override
    public Shop create(Shop shop) {
        return null;
    }

    @Override
    public Shop delete(String id) {
        return null;
    }

    @Override
    public List<Shop> findAll() {
        return null;
    }

    @Override
    public Shop findById(String id) {
        return null;
    }

    @Override
    public Shop update(Shop todo) {
        return null;
    }
}
