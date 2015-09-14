package com.jpmc.service;

import com.jpmc.model.Login;

public interface LoginService {
	
	
	public  Login getLoginDetails(String userId, String password);


}
