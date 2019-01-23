package com.bergturing.java.parent.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;

import java.util.List;
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
        if (null!=logger && CollectionUtils.isNotEmpty(list)) {
            logger.debug(list.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }
    }
}
