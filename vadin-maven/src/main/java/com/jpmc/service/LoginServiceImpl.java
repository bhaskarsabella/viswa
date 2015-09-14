package com.jpmc.service;

import com.jpmc.dao.LoginDao;
import com.jpmc.dao.LoginDaoImpl;
import com.jpmc.model.Login;

public  class LoginServiceImpl implements LoginService {

	public Login getLoginDetails(String userId, String password) {
		// TODO Auto-generated method stub
		LoginDao dao = new LoginDaoImpl();
		
		return dao.getLoginDetails(userId, password);
	}
	
	

}
