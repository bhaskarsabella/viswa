package com.jpmc.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jpmc.model.Login;
import com.jpmc.util.HibernateUtil;

public class LoginDaoImpl implements LoginDao {

public  Login getLoginDetails(String userId, String password){
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("from Login where  userName =:userName and password = :password");
		query.setString("userName", userId);
		query.setString("password",password);
		Login login  = (Login) query.uniqueResult();
		
		return login;
	}
}
