package com.transactions.statistics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.transactions.statistics.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
