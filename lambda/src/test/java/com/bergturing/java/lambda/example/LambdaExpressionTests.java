package com.bergturing.java.lambda.example;

import com.bergturing.java.lambda.LambdaApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.function.*;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class LambdaExpressionTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(LambdaExpressionTests.class);

    /**
     * 通用操作
     */
    @Test
    public void generate() {

    }

    /**
     * 测试java8内置的函数接口
     */
    @Test
    public void testInnerFunctionalInterface() {
        //Predicate<T>          T -> boolean
        Predicate<List<Integer>> predicate = list -> null==list || list.isEmpty();
        logger.debug("predicate: " + predicate.test(Collections.emptyList()));

        //Supplier<T>           () -> T
        Supplier<StringBuilder> supplier = StringBuilder::new;
        logger.debug("supplier：" + supplier.get());

        //Consumer<T>           T -> void
        Consumer<String> consumer = logger::debug;
        consumer.accept("consumer：you are right");

        //Function<T, R>        T -> R
        Function<String, Integer> function = Integer::valueOf;
        logger.debug("function：" + function.apply("12"));

        //UnaryOperator<T>      T -> T
        UnaryOperator<Integer> unaryOperator = integer -> integer*integer;
        logger.debug("unaryOperator：" + unaryOperator.apply(5));

        //BinaryOperator<T>     (T, T) -> T
        BinaryOperator<Integer> binaryOperator = (integer1, integer2) -> integer1+integer2;
        logger.debug("binaryOperator：" + binaryOperator.apply(1,  2));

        //BiPredicate<L, R>     (L, R) -> boolean
        BiPredicate<Integer, Integer> biPredicate = (integer1, integer2) -> integer1>integer2;
        logger.debug("biPredicate：" + biPredicate.test(1, 2));

        //BiConsumer<T, U>      (T, U) -> void
        BiConsumer<String, String> biConsumer = (string1, string2) -> logger.debug(string1+string2);
        biConsumer.accept("biConsumer：", "you are right");

        //BiFunction<T, U, R>   (T, U) -> R
        BiFunction<Integer, Integer, Integer> biFunction = (integer1, integer2) -> integer1+integer2;
        logger.debug("biFunction：" + biFunction.apply(1, 2));
    }
}
