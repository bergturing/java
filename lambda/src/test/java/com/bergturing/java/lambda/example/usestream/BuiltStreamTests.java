package com.bergturing.java.lambda.example.usestream;

import com.bergturing.java.lambda.LambdaApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 构建流的测试
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class BuiltStreamTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(BuiltStreamTests.class);

    /**
     * 测试由值创建流
     */
    @Test
    public void testBuiltStreamByValues() {
        //由值构建流
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");

        //输出结果
        stream.map(String::toUpperCase).forEach(logger::debug);
    }

    /**
     * 测试由数组创建流
     */
    @Test
    public void testBuiltStreamByArray() {
        //数组值
        int[] numbers = {2, 3, 5, 7, 11, 13};

        //计算总和
        int sum = Arrays.stream(numbers).sum();

        //输出结果
        logger.debug("sum = {}", sum);
    }

    /**
     * 测试由文件创建流
     */
    @Test
    public void testBuiltStreamByFile() {
        //统计结果
        long uniqueWords = 0;

        //读取文件，处理数据
        try (Stream<String> lines =
                     Files.lines((new File(getClass().getResource("/data.txt").toURI())).toPath(), Charset.defaultCharset())) {
            //统计单词数量
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        //输出结果
        logger.debug("count = {}", uniqueWords);
    }

    /**
     * 测试由函数生成流：创建无限流：迭代
     */
    @Test
    public void testBuiltStreamByFunctionIterate() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .map(String::valueOf)
                .forEach(logger::debug);

        //斐波拉契数列元组
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> logger.debug("({}, {})", t[0], t[1]));

        //斐波拉契数列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> String.valueOf(t[0]))
                .forEach(logger::debug);
    }

    /**
     * 测试由函数生成流：创建无限流：生成
     */
    @Test
    public void testBuiltStreamByFunctionGenerate() {
        Stream.generate(Math::random)
                .limit(5)
                .map(String::valueOf)
                .forEach(logger::debug);
    }
}
