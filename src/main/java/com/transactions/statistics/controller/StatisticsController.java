package com.transactions.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.statistics.dto.StatisticsDTO;
import com.transactions.statistics.statisticsService.ITransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/statistics")
@Api(tags = {"statistics"})
public class StatisticsController {

	@Autowired
	ITransactionService transactionService;
//	
//    @RequestMapping(method = RequestMethod.GET)
//    @ApiOperation(value = "Get a list of all transactions.", notes = "")
//    public StatisticsDTO findLastStatistics() {
////        return transactionService.findLastStatistics();
//    }
    
}
