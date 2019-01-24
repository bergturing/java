package com.bergturing.java.lambda.example.usestream;

import java.util.Map;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public interface ITraderService {
    /**
     * 获取交易方
     *
     * @return 交易方
     */
    Map<String, Trader> getTraders();
}
