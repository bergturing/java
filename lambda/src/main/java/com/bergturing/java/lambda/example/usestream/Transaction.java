package com.bergturing.java.lambda.example.usestream;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class Transaction {
    /**
     * 员工
     */
    private final Trader trader;

    /**
     * 交易日期
     */
    private final int year;

    /**
     * 交易额
     */
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}
