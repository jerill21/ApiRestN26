package com.transactions.statistics.statisticsService;

import java.text.ParseException;
import java.util.List;

import com.transactions.statistics.dto.StatisticsDTO;
import com.transactions.statistics.dto.TransactionDTO;

public interface ITransactionService {

	Integer createTransaction(TransactionDTO transaction) throws ParseException;

	List<TransactionDTO> findAll();

	StatisticsDTO findLastStatistics();
	
}
