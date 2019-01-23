package com.bergturing.java.parent.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public class OutputUtils {
    /**
     * 打印list
     *
     * @param list 待打印的list
     * @param <T>  list内对象的泛型
     */
    public static <T> void printList(List<T> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    /**
     * 打印list
     *
     * @param logger 日志打印对象
     * @param list   待打印的list
     * @param <T>    list内对象的泛型
     */
    public static <T> void debugList(Logger logger, List<T> list) {
        debugList(logger, list, Object::toString);
    }

    /**
     * 按指定的格式打印list
     *
     * @param logger      日志打印对象
     * @param list        待打印的list
     * @param mapFunction 打印结果的处理对象
     * @param <T>         list内对象的泛型
     */
    public static <T, R> void debugList(Logger logger, List<T> list, Function<T, R> mapFunction) {
        Objects.requireNonNull(logger);
        Objects.requireNonNull(mapFunction);

        //打印结果
        if (CollectionUtils.isNotEmpty(list)) {
            logger.debug("[{}]", list.stream()
                    .map(mapFunction)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ")));
        }
    }
}
