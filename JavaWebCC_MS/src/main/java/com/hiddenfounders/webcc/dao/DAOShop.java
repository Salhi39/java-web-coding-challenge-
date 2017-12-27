package com.hiddenfounders.webcc.dao;

import com.hiddenfounders.webcc.model.Shop;
import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.utility.Location;

import java.util.List;

public interface DAOShop {

    Shop create(Shop shop);

    Shop delete(String id);

    List<Shop> findAll();

    Shop findById(String id);

    Shop update(Shop todo);
}
