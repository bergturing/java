package com.bergturing.java.lambda.example.collectbystream;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.lambda.example.functionaldataprocess.Dish;
import com.bergturing.java.lambda.example.functionaldataprocess.IDishService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 规约和汇总测试
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class ReduceAndSumTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(ReduceAndSumTests.class);

    /**
     * 菜肴服务对象
     */
    @Autowired
    private IDishService dishService;

    /**
     * 测试查找流中的最大值
     */
    @Test
    public void testMax() {
        //菜单
        List<Dish> menu = this.dishService.getDishs();

        //查找流中卡路里最大的菜肴
        Optional<Dish> mostCalories = menu.stream()
                .collect(maxBy(Comparator.comparingInt(Dish::getCalories)));

        //输出结构
        logger.debug("result：{}", mostCalories);
    }

    /**
     * 测试查找流中的最小值
     */
    @Test
    public void testMin() {
        //菜单
        List<Dish> menu = this.dishService.getDishs();

        //查找流中卡路里最小的菜肴
        Optional<Dish> mostCalories = menu.stream()
                .collect(minBy(Comparator.comparingInt(Dish::getCalories)));

        //输出结构
        logger.debug("result：{}", mostCalories);
    }

    /**
     * 测试聚合
     */
    @Test
    public void testPolymerization() {
        //菜单
        List<Dish> menu = this.dishService.getDishs();

        //总的卡路里
        int totalCalories = menu.stream()
                .collect(summingInt(Dish::getCalories));
        //输出结果
        logger.debug("total calories：{}", totalCalories);

        //平均的卡路里
        double avgCalories = menu.stream()
                .collect(averagingInt(Dish::getCalories));
        //输出结果
        logger.debug("avg calories：{}", avgCalories);

        //聚合结果
        IntSummaryStatistics menuStatistics = menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        //输出结果
        logger.debug("summary result：{}", menuStatistics);
    }

    /**
     * 测试连接字符串
     */
    @Test
    public void testJoining() {
        //处理数据
        String joiningResult = Stream.of("hello", "how", "are", "you")
                .collect(joining(" "));

        //输出结果
        logger.debug(joiningResult);
    }
}
