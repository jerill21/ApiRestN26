package com.transactions.statistics.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.transactions.statistics.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

	@Query("{'timestamp' : {$gt : ?0}}")
    public List<Transaction> findByTimestampGreaterThanQuery(long timestamp);
	
}
