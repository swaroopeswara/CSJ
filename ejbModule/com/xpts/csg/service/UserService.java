package com.xpts.csg.service;

import javax.ejb.Local;

import com.xpts.csg.model.CsgUser;

@Local
public interface UserService {

	void saveUser(String userJson);
	
	CsgUser findUser(Integer id);
	
	String getUserJson(Integer id);
}
