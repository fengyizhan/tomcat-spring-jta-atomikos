package com.mcnc.mbanking.auth.test.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcnc.mbanking.auth.dao.CustomerDAO;
import com.mcnc.mbanking.auth.domain.Account;
import com.mcnc.mbanking.auth.domain.Customer;
import com.mcnc.mbanking.auth.service.AccountService;
import com.mcnc.mbanking.auth.service.CustomerService;


@RunWith(SpringRunner.class)
@ContextConfiguration(value = "classpath:/testcontext-root.xml")
public class TestAccountService {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerDAO dao; 

	@Test
	public void testGetAccount() throws IOException {
		
		Account account = accountService.getAccountByAccountNumber("5555");
		assertNotNull(account);
		
		
		ObjectMapper objectMapper = new ObjectMapper();

		String json = objectMapper.writeValueAsString(account);
		System.out.println(json);
		
		account = objectMapper.readValue(json, Account.class);
		
		System.out.println(account);
		
	}
	
	
	@Test
	public void test() {
		Customer customerDetail = dao.getCustomerDetail(1L);
		System.out.println(customerDetail);
	}
	
	
	@Test
	public void test2() {
		Customer customer = new Customer();
		customer.setFirstName("a");
		customer.setLastName("b");
		customer.setPhone("3");
		Customer customerByDetail = customerService.getCustomerByDetail(customer);
		assertNull(customerByDetail);
		
		
		new Customer();
		customer.setFirstName("sok");
		customer.setLastName("dara");
		customer.setPhone("016303030");
		customerByDetail = customerService.getCustomerByDetail(customer);
		assertNotNull(customerByDetail);
	}

}
