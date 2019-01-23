package com.bergturing.java.lambda.example.lambdaandinterface;

import com.bergturing.java.lambda.LambdaApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的测试
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class MethodReferenceTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(MethodReferenceTests.class);

    /**
     * 指向静态方法的方法引用(例如Integer的parseInt方法，
     * 可以写作Integer::parseInt);
     */
    @Test
    public void testStaticMethodReference() {
        //静态方法引用
        Function<String, Integer> staticMethodReference = Integer::parseInt;

        //输出结果
        logger.debug("static method reference：" + staticMethodReference.apply("456"));
    }

    /**
     * 指向任意类型实例方法的方法引用(例如String的length方法，
     * 写作String::length);
     */
    @Test
    public void testClassMethodReference() {
        //类的实例方法引用
        Function<String, Integer> classMethodReference = String::length;

        //输出结果
        logger.debug("class method reference：" + classMethodReference.apply("I Love You"));
    }

    /**
     * 指向现有对象的实例方法的方法引用
     * (假如你有一个局部变量expensiveTransaction用于存放Transaction类型的对象，
     * 它支持实例方法getValue，那么就可以写成expensiveTransaction::getValue)。
     */
    @Test
    public void testObjectMethodReference() {
        //局部变量
        String iLoveYouToo = "I Love You Too";

        //现有对象的实例方法引用
        Supplier<Integer> objectMethodReference = iLoveYouToo::length;

        //输出结果
        logger.debug("object method reference：" + objectMethodReference.get());
    }
}
