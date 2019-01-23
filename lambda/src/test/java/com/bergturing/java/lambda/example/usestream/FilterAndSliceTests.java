package com.bergturing.java.lambda.example.usestream;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.lambda.example.functionaldataprocess.Dish;
import com.bergturing.java.lambda.example.functionaldataprocess.IDishService;
import com.bergturing.java.parent.utils.OutputUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 筛选与切片测试
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class FilterAndSliceTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(FilterAndSliceTests.class);

    /**
     * 菜肴服务对象
     */
    @Autowired
    private IDishService dishService;

    /**
     * 测试用谓词筛选
     */
    @Test
    public void testFilter() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //素菜
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, vegetarianMenu);
    }

    /**
     * 测试筛选各异的元素
     */
    @Test
    public void testDistinct() {
        //数字序列
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        //筛选偶数，并去重
        List<Integer> result = numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(toList());

        //输出结果
        OutputUtils.debugList(logger, result);
    }

    /**
     * 测试截短流
     */
    @Test
    public void testLimit() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //获取菜单里前三个卡路里大于300的菜肴
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());

        //输出结果
        OutputUtils.debugList(logger, dishes);
    }

    /**
     * 测试跳过元素
     */
    @Test
    public void testSkip() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //获取菜单里卡路里大于300的菜肴(跳过前两个菜肴)
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());

        //输出结果
        OutputUtils.debugList(logger, dishes);
    }
}
