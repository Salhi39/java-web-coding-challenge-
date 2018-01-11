package com.hiddenfounders.webcc.repository;

import com.hiddenfounders.webcc.model.Status;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;


public interface StatusRepository extends MongoRepository<Status, ObjectId> {


}
