package com.bergturing.java.lambda.example.lambdaandinterface.complexlambda;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.lambda.example.lambdaandinterface.exampleimport.Apple;
import com.bergturing.java.lambda.example.lambdaandinterface.exampleimport.IAppleService;
import com.bergturing.java.parent.utils.OutputUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

/**
 * 复合Lambda表达式测试：比较器复合
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class ComparatorComplexTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(ComparatorComplexTests.class);

    /**
     * 苹果服务对象
     */
    @Autowired
    private IAppleService appleService;

    /**
     * 测试比较器
     */
    @Test
    public void testComparator() {
        //获取苹果数据
        List<Apple> apples = appleService.getApples();

        //Comparator接口的静态方法comparing，可以根据传入的Function来返回一个Comparator对象
        Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);

        logger.debug("before sort：");
        OutputUtils.debugList(logger, apples);

        //排序
        apples.sort(comparator);

        logger.debug("after sort：");
        OutputUtils.debugList(logger, apples);
    }

    /**
     * 测试逆序
     */
    @Test
    public void testReversedComparator() {
        //获取苹果数据
        List<Apple> apples = appleService.getApples();

        //Comparator接口的静态方法comparing，可以根据传入的Function来返回一个Comparator对象
        Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);

        //排序
        apples.sort(comparator.reversed());

        logger.debug("after sort：");
        OutputUtils.debugList(logger, apples);
    }

    /**
     * 测试比较链
     */
    @Test
    public void testComparatorChain() {
        //获取苹果数据
        List<Apple> apples = appleService.getApples();

        //Comparator接口的静态方法comparing，可以根据传入的Function来返回一个Comparator对象
        Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);

        //排序
        apples.sort(comparator.reversed().thenComparing(Apple::getColor));

        logger.debug("after sort：");
        OutputUtils.debugList(logger, apples);
    }
}
