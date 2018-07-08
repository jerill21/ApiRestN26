package com.transactions.statistics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.transactions.statistics.controller.StatisticsController;
import com.transactions.statistics.controller.TransactionController;
import com.transactions.statistics.dto.StatisticsDTO;
import com.transactions.statistics.dto.TransactionDTO;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class StatisticsControllerTest {

	@Autowired
	private StatisticsController controller;
	
	@Autowired
	private TransactionController transactionController;
	
	@Test
	public void test00_findLastStatisticsEmpty_accept() {
		
		StatisticsDTO statistics = controller.findLastStatistics();
		
		assertNotNull(statistics);
		assertEquals(0, statistics.getAvg(), 0);
		assertEquals(0, statistics.getCount());
		assertEquals(0, statistics.getMax(), 0);
		assertEquals(0, statistics.getMin(), 0);
		assertEquals(0, statistics.getSum(), 0);
	}
	
	@Test
	public void test01_findLastStatisticsNotEmpty_accept() throws ParseException {
		
		TransactionDTO transaction = new TransactionDTO();
		long currentTimestamp = System.currentTimeMillis();
		transaction.setAmount(100);
		transaction.setTimestamp(currentTimestamp);
		
		transactionController.createTransaction(transaction);
		
		StatisticsDTO statistics = controller.findLastStatistics();
		
		assertNotNull(statistics);
		assertEquals(100, statistics.getAvg(), 0);
		assertEquals(1, statistics.getCount());
		assertEquals(100, statistics.getMax(), 0);
		assertEquals(100, statistics.getMin(), 0);
		assertEquals(100, statistics.getSum(), 0);
	}
	
	@Test
	public void test02_findLastStatisticsNotEmpty2_accept() throws ParseException {
		
		TransactionDTO transaction = new TransactionDTO();
		long currentTimestamp = System.currentTimeMillis();
		transaction.setAmount(200);
		transaction.setTimestamp(currentTimestamp);
		
		transactionController.createTransaction(transaction);
		
		StatisticsDTO statistics = controller.findLastStatistics();
		
		assertNotNull(statistics);
		assertEquals(150, statistics.getAvg(), 0);
		assertEquals(2, statistics.getCount());
		assertEquals(200, statistics.getMax(), 0);
		assertEquals(100, statistics.getMin(), 0);
		assertEquals(300, statistics.getSum(), 0);
	}
	
}
