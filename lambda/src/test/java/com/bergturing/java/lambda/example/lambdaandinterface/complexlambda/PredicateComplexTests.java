package com.bergturing.java.lambda.example.lambdaandinterface.complexlambda;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.lambda.example.lambdaandinterface.exampleimport.Apple;
import com.bergturing.java.lambda.example.lambdaandinterface.exampleimport.IAppleService;
import com.bergturing.java.parent.utils.OutputUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 复合Lambda表达式测试：谓词复合
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class PredicateComplexTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(PredicateComplexTests.class);

    /**
     * 苹果服务
     */
    @Autowired
    private IAppleService appleService;

    /**
     * 红苹果筛选器
     */
    private Predicate<Apple> redApple = apple -> "red".equals(apple.getColor());

    /**
     * 测试谓词
     */
    @Test
    public void testPredicate() {
        //获得苹果
        List<Apple> apples = this.appleService.getApples();

        //获得红苹果
        List<Apple> redApples = apples.stream().filter(this.redApple).collect(Collectors.toList());

        //输出结果
        OutputUtils.debugList(logger, redApples, Apple::getColor);
    }

    /**
     * 测试非
     */
    @Test
    public void testNegate() {
        //获得苹果
        List<Apple> apples = this.appleService.getApples();

        //非红苹果筛选器
        Predicate<Apple> notRedApple = this.redApple.negate();
        //获得非红苹果
        List<Apple> notRedApples = apples.stream().filter(notRedApple).collect(Collectors.toList());

        //输出结果
        OutputUtils.debugList(logger, notRedApples, Apple::getColor);
    }

    /**
     * 测试与
     */
    @Test
    public void testAnd() {
        //获得苹果
        List<Apple> apples = this.appleService.getApples();

        //苹果是红色的，同时重量要大于150
        Predicate<Apple> redAndHeavyApple = this.redApple.and(apple -> apple.getWeight() > 150);
        //获得满足条件的苹果
        List<Apple> redAndHeavyApples = apples.stream().filter(redAndHeavyApple).collect(Collectors.toList());

        //输出结果
        OutputUtils.debugList(logger, redAndHeavyApples);
    }

    /**
     * 测试或
     */
    @Test
    public void testOr() {
        //获得苹果
        List<Apple> apples = this.appleService.getApples();

        //获取红色同时重量超过150，或者绿色的苹果
        Predicate<Apple> redAndHeavyOrGreenApples =
                this.redApple.and(apple -> apple.getWeight() > 150)
                        .or(apple -> "green".equals(apple.getColor()));

        //获得满足条件的苹果
        List<Apple> redAndHeavyApples = apples.stream().filter(redAndHeavyOrGreenApples).collect(Collectors.toList());

        //输出结果
        OutputUtils.debugList(logger, redAndHeavyApples);
    }
}
