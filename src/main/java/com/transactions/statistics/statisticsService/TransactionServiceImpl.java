package com.transactions.statistics.statisticsService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactions.statistics.dto.StatisticsDTO;
import com.transactions.statistics.dto.TransactionDTO;
import com.transactions.statistics.model.Transaction;
import com.transactions.statistics.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private TransactionRepository repository;
	
	@Override
	public Integer createTransaction(TransactionDTO transactionDTO) throws ParseException {

		Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
		repository.save(transaction);
		
		long offsetSeconds = (System.currentTimeMillis()/1000) - (transactionDTO.getTimestamp()/1000);
		
		if (offsetSeconds > 60) {
			return 204;
		}
		else {
			return 201;
		}

	}

	@Override
	public List<TransactionDTO> findAll() {
		
		List<Transaction> list = repository.findAll();
		List<TransactionDTO> listDTO = new ArrayList<TransactionDTO>();
		
		for (Transaction transaction : list) {
			listDTO.add(modelMapper.map(transaction, TransactionDTO.class));
		}
		
		return listDTO;
	}

	@Override
	public StatisticsDTO findLastStatistics() {

		long belowLimit = System.currentTimeMillis() - 60000;
		
		List<Transaction> list = repository.findByTimestampGreaterThanQuery(belowLimit);
		
		double sum = 0;
		double avg = 0;
		double min = Double.MAX_VALUE;
		double max = -1;
		
		for (Transaction t: list) {
		
			sum += t.getAmount();
			
			if (t.getAmount() > max) {
				max = t.getAmount();
			}
			
			if (t.getAmount() < min) {
				min = t.getAmount();
			}
		}
		avg = sum/list.size();

		StatisticsDTO dto = new StatisticsDTO();
		dto.setAvg(avg);
		dto.setCount(list.size());
		dto.setMax(max);
		dto.setMin(min);
		dto.setSum(sum);
		
		return dto;
	}

	
	
}
