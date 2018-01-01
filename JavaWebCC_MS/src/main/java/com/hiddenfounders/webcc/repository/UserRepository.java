package com.hiddenfounders.webcc.repository;

import com.hiddenfounders.webcc.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, ObjectId> {


}
