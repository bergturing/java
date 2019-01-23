package com.bergturing.java.lambda.example.lambdaandinterface.exampleimport;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.parent.utils.OutputUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class AppleServiceTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(AppleServiceTests.class);

    /**
     * 苹果服务对象
     */
    @Autowired
    private IAppleService appleService;

    /**
     * 测试获取红苹果
     */
    @Test
    public void testGetRedApples() {
        List<Apple> apples = this.appleService.getApples();
        List<Apple> redApples = new ArrayList<>();
        for (Apple apple : apples) {
            //筛选获取红色的苹果
            if ("red".equals(apple.getColor())) {
                redApples.add(apple);
            }
        }

        //打印结果
        OutputUtils.debugList(logger, redApples);
    }

    /**
     * 果农突然有增加了获取绿色苹果的需求，
     * 我们发现这里的代码可以抽象出来，将颜色作为一个参数传入：
     */
    @Test
    public void testGetColorApples() {
        List<Apple> apples = this.appleService.getApples();

        //获取红苹果
        List<Apple> redApples = this.appleFilter(apples, "red");
        //获取绿苹果
        List<Apple> greenApples = this.appleFilter(apples, "green");

        //打印结果
        OutputUtils.debugList(logger, redApples);
        OutputUtils.debugList(logger, greenApples);
    }

    /**
     * 使用一个筛选类来筛选苹果
     */
    @Test
    public void testGetApplesByAppleFilter() {
        List<Apple> apples = this.appleService.getApples();

        List<Apple> resultApples = this.appleFilter(apples, new RedAndMoreThan140AppleFilter());

        //打印结果
        OutputUtils.debugList(logger, resultApples);
    }

    /**
     * 使用匿名内部类实现对苹果的筛选
     */
    @Test
    public void testGetApplesByInnerFilter() {
        List<Apple> apples = this.appleService.getApples();

        List<Apple> resultApples = this.appleFilter(apples, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "red".equals(apple.getColor()) && apple.getWeight() >= 140;
            }
        });

        //打印结果
        OutputUtils.debugList(logger, resultApples);
    }

    /**
     * 使用lambda表达式来筛选苹果
     */
    @Test
    public void testGetApplesByLambda() {
        List<Apple> apples = this.appleService.getApples();

        List<Apple> resultApples = this.appleFilter(apples, (Apple apple) -> {
            return "red".equals(apple.getColor()) && apple.getWeight() >= 140;
        });

        //打印结果
        OutputUtils.debugList(logger, resultApples);
    }

    /**
     * 简化后的lambda表达式筛选苹果
     */
    @Test
    public void testGetApplesBySimpleLambda() {
        List<Apple> apples = this.appleService.getApples();

        List<Apple> resultApples = this.appleFilter(apples,
                apple -> "red".equals(apple.getColor()) && apple.getWeight() >= 140);

        //打印结果
        OutputUtils.debugList(logger, resultApples);
    }

    /**
     * 苹果按颜色筛选的方法
     *
     * @param apples 需要筛选的苹果
     * @param color  颜色
     * @return 筛选结果
     */
    private List<Apple> appleFilter(List<Apple> apples, String color) {
        List<Apple> resultApples = new ArrayList<>();

        for (Apple apple : apples) {
            //筛选指定颜色的苹果
            if (color.equals(apple.getColor())) {
                resultApples.add(apple);
            }
        }

        return resultApples;
    }

    /**
     * 苹果按条件筛选的方法
     *
     * @param apples 需要筛选的苹果
     * @param filter 筛选条件对象
     * @return 筛选结果
     */
    private List<Apple> appleFilter(List<Apple> apples, AppleFilter filter) {
        List<Apple> resultApples = new ArrayList<>();

        for (Apple apple : apples) {
            //筛选满足条件的苹果
            if (filter.filter(apple)) {
                resultApples.add(apple);
            }
        }

        return resultApples;
    }

    /**
     * 筛选红色的，重量大于140苹果筛选条件的实现
     */
    public class RedAndMoreThan140AppleFilter implements AppleFilter {
        @Override
        public boolean filter(Apple apple) {
            return "red".equals(apple.getColor()) && apple.getWeight() >= 140;
        }
    }
}
