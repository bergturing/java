package com.bergturing.java.lambda.example.usestream;

import java.util.List;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public interface ITransactionService {
    /**
     * 获取交易记录
     *
     * @return 交易记录
     */
    List<Transaction> getTransactions();
}
