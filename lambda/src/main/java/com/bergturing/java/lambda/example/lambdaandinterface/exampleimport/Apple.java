package com.bergturing.java.lambda.example.lambdaandinterface.exampleimport;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class Apple {
    /**
     * 颜色
     */
    private String color;

    /**
     * 重量
     */
    private int weight;

    public Apple (String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return this.color;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}