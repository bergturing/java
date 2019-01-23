package com.bergturing.java.lambda.example.functionaldataprocess;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class Dish {
    /**
     * 名称
     */
    private final String name;

    /**
     * 是否为素食
     */
    private final boolean vegetarian;

    /**
     * 卡路里
     */
    private final int calories;

    /**
     * 类型
     */
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVegetarian() {
        return this.vegetarian;
    }

    public int getCalories() {
        return this.calories;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public enum Type {
        /**
         * 米饭
         */
        MEAT,
        /**
         * 鱼类
         */
        FISH,
        /**
         * 其他
         */
        OTHER
    }
}