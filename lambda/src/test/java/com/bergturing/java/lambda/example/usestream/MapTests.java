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
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * 测试映射
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class MapTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(MapTests.class);

    /**
     * 菜肴服务对象
     */
    @Autowired
    private IDishService dishService;

    /**
     * 测试映射
     */
    @Test
    public void testMap() {
        //获取菜单
        List<Dish> menu = this.dishService.getDishs();

        //映射出菜肴的名称
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        //输出结果
        OutputUtils.debugList(logger, dishNames);
    }

    /**
     * 对于一张单词表， 如何返回一张列表， 列出里面各不相同的字符？
     */
    @Test
    public void testFlatMapByWords() {
        //单词表
        List<String> words = Arrays.asList("you", "are", "right");

        //不重复的字符
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, uniqueCharacters);
    }

    /**
     * 对一个数字序列，如何筛选出出现过指定次数的数字，重复的数字也打印出来
     */
    @Test
    public void testFlatMapBySequences() {
        //数字序列
        List<Integer> sequences =
                Arrays.asList(1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 6, 7, 7, 7, 8, 8);

        //分组数据
        Map<Integer, List<Integer>> groupResult = sequences.stream().
                collect(groupingBy(Integer::intValue));

        //处理满足条件的数据
        List<Integer> result = groupResult.keySet().stream()
                .filter(key -> groupResult.get(key).size() >= 3)
                .map(groupResult::get)
                .flatMap(List::stream)
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, result);
    }

    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * 例如，给定[1, 2, 3, 4,5]，应该返回[1, 4, 9, 16, 25]。
     */
    @Test
    public void testExamine1() {
        //输入的数字
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        //计算结果
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, squares);
    }

    /**
     * 给定两个数字列表，如何返回所有的数对呢？
     * 例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
     * 为简单起见，你可以用有两个元素的数组来代表数对。
     */
    @Test
    public void testExamine2() {
        //输入的数字
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        //处理结果
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, pairs, pair -> pair[0] + "-" + pair[1]);
    }

    /**
     * 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
     */
    @Test
    public void testExamine3() {
        //输入的数字
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        //处理结果
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, pairs, pair -> pair[0] + "-" + pair[1]);
    }
}
