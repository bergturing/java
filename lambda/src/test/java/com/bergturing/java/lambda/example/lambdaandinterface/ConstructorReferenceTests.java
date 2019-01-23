package com.bergturing.java.lambda.example.lambdaandinterface;

import com.bergturing.java.lambda.LambdaApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * 构造函数引用测试
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class ConstructorReferenceTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(ConstructorReferenceTests.class);

    /**
     * 构造函数引用
     */
    @Test
    public void testConstructorReference() {
        //构造函数引用
        Function<String, Integer> constructorReference = Integer::new;

        //打印结果
        logger.debug("constructor reference：" + constructorReference.apply("123"));
    }
}
