package com.jpmc.dao;

import com.jpmc.model.Login;



public interface LoginDao {
	
	public  Login getLoginDetails(String userId, String password);

}
