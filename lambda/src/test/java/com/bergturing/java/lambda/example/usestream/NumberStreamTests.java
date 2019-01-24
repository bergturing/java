package com.bergturing.java.lambda.example.usestream;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.parent.utils.OutputUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * 数值流测试
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class NumberStreamTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(NumberStreamTests.class);

    /**
     * 测试勾股数
     */
    @Test
    public void testPythagoreanNumber() {
        //生成1到100的勾股数
        List<int[]> pythagoreanTriples = IntStream
                .rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                ).collect(toList());

        //打印结果
        OutputUtils.debugList(logger, pythagoreanTriples, item -> item[0] + "^2+" + item[1] + "^2=" + item[2] + "^2");
    }

    /**
     * 测试勾股数：执行更快的版本
     */
    @Test
    public void testPythagoreanNumberMoreFast() {
        //生成1到100的勾股数
        List<double[]> pythagoreanTriples = IntStream
                .rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0))
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, pythagoreanTriples, item -> item[0] + "^2+" + item[1] + "^2=" + item[2] + "^2");
    }
}
