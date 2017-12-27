package com.hiddenfounders.webcc.dao;

import com.hiddenfounders.webcc.model.User;

import java.util.List;

public interface DAOUser {


    User create(User shop);

    User delete(String id);

    List<User> findAll();

    User findById(String id);

    User update(User todo);
    
    
}








