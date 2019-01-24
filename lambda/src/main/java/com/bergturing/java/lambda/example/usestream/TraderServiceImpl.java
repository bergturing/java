package com.bergturing.java.lambda.example.usestream;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
@Service
public class TraderServiceImpl implements ITraderService {
    /**
     * 交易方
     */
    private Map<String, Trader> traders = new HashMap<>(8);

    {
        traders.put("raoul", new Trader("Raoul", "Cambridge"));
        traders.put("mario", new Trader("Mario", "Milan"));
        traders.put("alan", new Trader("Alan", "Cambridge"));
        traders.put("brian", new Trader("Brian", "Cambridge"));
    }

    @Override
    public Map<String, Trader> getTraders() {
        return new HashMap<>(this.traders);
    }
}
