package com.transactions.statistics.controller;

import java.text.ParseException;
import java.util.List;

//import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.statistics.dto.TransactionDTO;
import com.transactions.statistics.statisticsService.ITransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
@Api(tags = {"transactions"})
public class TransactionController {

	@Autowired
	ITransactionService transactionService;
	
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a transaction resource.", notes = ".")
    public Integer createTransaction(@RequestBody TransactionDTO transaction) throws ParseException {
    	
    	return transactionService.createTransaction(transaction);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get a list of all transactions.", notes = "")
    public List<TransactionDTO> findAllTransactions() {
        return transactionService.findAll();
    }
    
}
