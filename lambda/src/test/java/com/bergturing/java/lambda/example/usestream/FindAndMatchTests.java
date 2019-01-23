package com.bergturing.java.lambda.example.usestream;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.lambda.example.functionaldataprocess.Dish;
import com.bergturing.java.lambda.example.functionaldataprocess.IDishService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 测试查找与匹配
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class FindAndMatchTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(FindAndMatchTests.class);

    /**
     * 菜肴服务对象
     */
    @Autowired
    private IDishService dishService;

    /**
     * 测试检查谓词中是否至少匹配一个元素
     */
    @Test
    public void testAnyMatch() {
        //菜单
        List<Dish> menu = this.dishService.getDishs();

        //是否有一个素菜
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            logger.debug("The menu is (somewhat) vegetarian friendly!!");
        }
    }

    /**
     * 检查谓词中是否匹配所有元素
     */
    @Test
    public void testAllMatch() {
        //菜单
        List<Dish> menu = this.dishService.getDishs();

        //是否全部满足
        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);

        //输出结果
        logger.debug("all match: {}", isHealthy);
    }

    /**
     * 检查谓词中不匹配所有元素
     */
    @Test
    public void testNoneMatch() {
        //菜单
        List<Dish> menu = this.dishService.getDishs();

        //是否都不满足
        boolean isHealthy = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);

        //输出结果
        logger.debug("none match: {}", isHealthy);
    }

    /**
     * 查找任意一个
     */
    @Test
    public void testFindAny() {
        //菜单
        List<Dish> menu = this.dishService.getDishs();

        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        //输出结果
        logger.debug("find any: {}", dish);
    }

    /**
     * 查找第一个
     */
    @Test
    public void testFindFirst() {
        //输入的数据
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);

        //处理数据
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst(); // 9

        //输出结果
        logger.debug("find first: {}", firstSquareDivisibleByThree);
    }
}
