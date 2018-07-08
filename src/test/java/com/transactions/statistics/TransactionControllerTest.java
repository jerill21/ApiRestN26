package com.transactions.statistics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.transactions.statistics.controller.TransactionController;
import com.transactions.statistics.dto.TransactionDTO;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class TransactionControllerTest {

	@Autowired
	TransactionController controller;
	
	@Test
	public void test01_createTransaction201_accept() throws ParseException {
		
		TransactionDTO transaction = new TransactionDTO();
		long currentTimestamp = System.currentTimeMillis();
		transaction.setAmount(100);
		transaction.setTimestamp(currentTimestamp);
		
		int code = controller.createTransaction(transaction);
		assertEquals(201, code);
	}

	@Test
	public void test02_createTransaction204_accept() throws ParseException {
		
		TransactionDTO transaction = new TransactionDTO();
		long currentTimestamp = System.currentTimeMillis() - 70000;
		transaction.setAmount(100);
		transaction.setTimestamp(currentTimestamp);
		
		int code = controller.createTransaction(transaction);
		assertEquals(204, code);
	}
	
	@Test
	public void test03_findAll_accept() {
		
		List<TransactionDTO> transactionList = controller.findAllTransactions();
		assertNotNull(transactionList);
		assertTrue(!transactionList.isEmpty());
	}
}
