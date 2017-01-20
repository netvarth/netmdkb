package com.nv.netmdkb.pl.dao.xml.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmdkb.facade.LoginFacade;
import com.nv.netmdkb.rs.dto.User;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/testDataSource.xml","classpath:META-INF/context.xml",  "classpath:META-INF/analytics-context.xml" })




public class CorporateLoginHandlerTest {
	
	@Autowired
    private ApplicationContext applicationContext;

	@Test
    public void authenticateUserTest(){
	LoginFacade loginFacade = (LoginFacade) applicationContext.getBean("login.handler");
	User user = new User();
	
	user.setUserName("paily");
	user.setPassword("netvarth");
	System.out.println(loginFacade.authenticate(user));
	
	
	
	}
	
}
