package com.hiddenfounders.webcc.repository;

import com.hiddenfounders.webcc.model.Shop;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;


public interface ShopRepository  extends MongoRepository<Shop, ObjectId> {


}
