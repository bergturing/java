package com.bergturing.java.lambda.example.functionalinterface.right3;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
@FunctionalInterface
public interface FunctionInterface {
    void interfaceMethod();

    default void me() {
        System.out.println("I'm a functional interface");
    }

    static void yes() {
        System.out.println("Yes. I am");
    }
}