package com.bergturing.java.lambda.example.functionaldataprocess;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.parent.utils.OutputUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class FunctionalDataProcessTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(FunctionalDataProcessTests.class);

    /**
     * 菜肴服务对象
     */
    @Autowired
    private IDishService dishService;

    /**
     * 测试在Java8之前筛选和映射数据
     */
    @Test
    public void testFilterAndMapBeforeJava8() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //筛选结果
        List<Dish> lowCaloricDishs = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishs.add(dish);
            }
        }

        //排序
        Collections.sort(lowCaloricDishs, new Comparator<Dish>() {
            public int compare(Dish dish1, Dish dish2) {
                return Integer.compare(dish1.getCalories(), dish2.getCalories());
            }
        });

        //获取菜肴名称
        List<String> lowCaloricDishsName = new ArrayList<>();
        for (Dish dish : lowCaloricDishs) {
            lowCaloricDishsName.add(dish.getName());
        }

        //输出结果
        OutputUtils.debugList(logger, lowCaloricDishsName);
    }

    /**
     * 测试在Java8中筛选和映射数据
     */
    @Test
    public void testFilterAndMapInJava8() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //过滤与映射
        List<String> lowCaloricDishsName = menu
                .stream()
                .filter(dish -> dish.getCalories() < 400)    //选出卡路里400以下的菜肴
                .sorted(comparing(Dish::getCalories))        //按卡路里排序
                .map(Dish::getName)                          //获取菜肴的名字
                .collect(toList());                          //将结果收集为一个list

        //输出结果
        OutputUtils.debugList(logger, lowCaloricDishsName);
    }

    /**
     * 测试在Java8中使用并行流筛选和映射数据
     */
    @Test
    public void testFilterAndMapWithParallelInJava8() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //过滤与映射
        List<String> lowCaloricDishsName = menu
                .parallelStream()
                .filter(dish -> dish.getCalories() < 400)    //选出卡路里400以下的菜肴
                .sorted(comparing(Dish::getCalories))        //按卡路里排序
                .map(Dish::getName)                          //获取菜肴的名字
                .collect(toList());                          //将结果收集为一个list

        //输出结果
        OutputUtils.debugList(logger, lowCaloricDishsName);
    }

    /**
     * 测试流操作
     */
    @Test
    public void testStreamOperate() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //流操作
        List<String> names = menu
                .stream()
                .filter(dish -> {
                    System.out.println("filtering " + dish.getName());
                    return dish.getCalories() > 300;
                }).map(dish -> {
                    System.out.println("mapping " + dish.getName());
                    return dish.getName();
                }).limit(3)
                .collect(toList());

        //输出结果
        OutputUtils.debugList(logger, names);
    }
}
