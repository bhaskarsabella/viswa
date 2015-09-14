package com.jpmc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jpmc.common.Constants;


public class HibernateUtil {

	private static SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	public static SessionFactory getSessionFactory() {

		String fileName = Constants.DB_PROPERTIES_FILE;
		FileUtil fileUtil = new FileUtil(fileName);
		
		String userName = fileUtil.getProperty(Constants.DB_USERNAME_KEY);
		String password = fileUtil.getProperty(Constants.DB_PASSWORD_KEY);
		String url = fileUtil.getProperty(Constants.DB_URL_KEY);
		
		if (sessionFactory == null)
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.password", password)
					.setProperty("hibernate.connection.username", userName)
					.setProperty("hibernate.connection.url", url)
					.buildSessionFactory();
		return sessionFactory;
	}

	public static void main(String[] args) {
		System.out.println(HibernateUtil.getSessionFactory());
	}
}