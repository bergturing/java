package com.bergturing.java.lambda.example.lambdaandinterface.complexlambda;

import com.bergturing.java.lambda.LambdaApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * 复合Lambda表达式测试：函数复合
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class FunctionComplexTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(FunctionComplexTests.class);

    /**
     * 对输入的数字加1
     */
    private Function<Integer, Integer> addOneFunction = x -> x+1;

    /**
     * 求一个数字的平方数
     */
    private Function<Integer, Integer> squareFunction = x -> x*x;

    /**
     * 测试Function的功能
     */
    @Test
    public void testFunction() {
        logger.debug("3 + 1 = {}", this.addOneFunction.apply(3));
        logger.debug("2 * 2 = {}", this.squareFunction.apply(2));
    }

    /**
     * 测试Function接下来在执行一个Function的功能
     */
    @Test
    public void testAndThen() {
        //复合函数，先加1，再求平方数
        Function<Integer, Integer> complexFunction = this.addOneFunction.andThen(this.squareFunction);

        //输出结果
        logger.debug("(3 + 1)^2 = {}", complexFunction.apply(3));
    }

    /**
     * 测试在执行当前Function之前执行另一个Function
     */
    @Test
    public void testCompose() {
        Function<Integer, Integer> complexFunction = this.addOneFunction.compose(this.squareFunction);

        //输出结果
        logger.debug("3^2 + 1 = {}", complexFunction.apply(3));
    }
}
