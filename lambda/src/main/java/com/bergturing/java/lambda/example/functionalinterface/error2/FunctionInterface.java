package com.bergturing.java.lambda.example.functionalinterface.error2;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */

//继承的父接口中有抽象方法，自己也定义了一个抽象方法
//相当于有两个抽象方法，与函数接口定义不符合
//@FunctionalInterface
public interface FunctionInterface  extends SuperInterface {
    void interfaceMethod();
}
