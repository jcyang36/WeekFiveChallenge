package com.example.demo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by student on 6/23/17.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

    public List<Transaction> findAllByAcctNumber(Integer acctNumber);

}
