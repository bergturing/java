package com.bergturing.java.lambda.example.usestream;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class Trader {
    /**
     * 姓名
     */
    private final String name;

    /**
     * 城市
     */
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    @Override
    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}