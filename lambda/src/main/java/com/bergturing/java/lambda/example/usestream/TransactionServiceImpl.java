package com.bergturing.java.lambda.example.usestream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
@Service
public class TransactionServiceImpl implements ITransactionService {
    /**
     * 交易记录
     */
    private List<Transaction> transactions;

    @Autowired
    public TransactionServiceImpl(ITraderService traderService) {
        //获取交易方
        Map<String, Trader> traders = traderService.getTraders();

        //初始化交易
        this.transactions = Arrays.asList(
                new Transaction(traders.get("brian"), 2011, 300),
                new Transaction(traders.get("raoul"), 2012, 1000),
                new Transaction(traders.get("raoul"), 2011, 400),
                new Transaction(traders.get("mario"), 2012, 710),
                new Transaction(traders.get("mario"), 2012, 700),
                new Transaction(traders.get("alan"), 2012, 950)
        );
    }

    @Override
    public List<Transaction> getTransactions() {
        return new ArrayList<>(this.transactions);
    }
}
