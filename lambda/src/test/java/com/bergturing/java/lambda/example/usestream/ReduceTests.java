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
 * 测试规约
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class ReduceTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(ReduceTests.class);

    /**
     * 菜肴服务对象
     */
    @Autowired
    private IDishService dishService;

    /**
     * 测试求和
     */
    @Test
    public void testSum() {
        //输入
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //求和
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);

        //输出结果
        logger.debug("sum：{}", sum);
    }

    /**
     * 测试使用更简洁的方式来求和
     */
    @Test
    public void testSimpleSum() {
        //输入
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //求和
        int sum = numbers.stream().reduce(0, Integer::sum);

        //输出结果
        logger.debug("sum：{}", sum);
    }

    /**
     * 测试没有初始值的求和
     */
    @Test
    public void testNoInitSum() {
        //输入
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //求和
        Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));

        //输出结果
        logger.debug("sum：{}", sum);
    }

    /**
     * 测试获取最大值
     */
    @Test
    public void testMax() {
        //输入
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //最大值
        Optional<Integer> max = numbers.stream().reduce(Integer::max);

        //输出结果
        logger.debug("max：{}", max);
    }

    /**
     * 测试获取最小值
     */
    @Test
    public void testMin() {
        //输入
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //最小值
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        //输出结果
        logger.debug("min：{}", min);
    }

    /**
     * 用map和reduce方法数一数流中有多少个菜
     */
    @Test
    public void testMenuCount() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //计数
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);

        //输出结果
        logger.debug("count：{}", count);
    }

    /**
     * 测试简化的对流的计数
     */
    @Test
    public void testSimpleCount() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //计数
        long count = menu.stream()
                .filter(Dish::isVegetarian)
                .count();

        //输出结果
        logger.debug("count：{}", count);
    }

    /**
     * 测试归约方法的优势与并行化
     */
    @Test
    public void testParallel() {
        //输入
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //并行流求和
        int sum = numbers.parallelStream().reduce(0, Integer::sum);

        //输出结果
        logger.debug("sum：{}", sum);
    }
}
