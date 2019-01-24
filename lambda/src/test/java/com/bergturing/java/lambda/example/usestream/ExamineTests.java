package com.bergturing.java.lambda.example.usestream;

import com.bergturing.java.lambda.LambdaApplicationTests;
import com.bergturing.java.parent.utils.OutputUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * 付诸行动测试
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/24
 */
public class ExamineTests extends LambdaApplicationTests {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(ExamineTests.class);

    /**
     * 交易记录服务对象
     */
    @Autowired
    private ITransactionService transactionService;

    /**
     * 找出2011年发生的所有交易，并按交易额排序(从低到高)；
     */
    @Test
    public void testExamine1() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //获取2011年的交易
        List<Transaction> tr2011 = transactions
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, tr2011);
    }

    /**
     * 交易员都在哪些不同的城市工作过
     */
    @Test
    public void testExamine2() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //工作的城市
        List<String> cities = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, cities);
    }

    /**
     * 用另外一种方式实现交易员都在哪些不同的城市工作过
     */
    @Test
    public void testExamine2ByOtherMethod() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //工作的城市
        Set<String> cities = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(toSet());

        //打印结果
        OutputUtils.debugList(logger, cities);
    }

    /**
     * 查找所有来自剑桥的交易员，并按姓名排序；
     */
    @Test
    public void testExamine3() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //筛选
        List<Trader> traders = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, traders);
    }

    /**
     * 测试返回所有交易员的姓名字符串，按字母顺序排序；
     */
    @Test
    public void testExamine4() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //处理数据
        String traderStr = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        //输出结果
        logger.debug(traderStr);
    }

    /**
     * 测试使用joining方法，返回所有交易员的姓名字符串，按字母顺序排序；
     */
    @Test
    public void testExamine4ByJoining() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //处理数据
        String traderStr = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());

        //输出结果
        logger.debug(traderStr);
    }

    /**
     * 测试有没有交易员在米兰工作过；
     */
    @Test
    public void testExamine5() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //有没有交易员在米兰工作过
        boolean milanBased = transactions
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        //输出结果
        logger.debug("milan based：{}", milanBased);
    }

    /**
     * 测试打印生活在剑桥的交易员的所有交易额；
     */
    @Test
    public void testExamine6() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //获取交易额
        List<Integer> result = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(toList());

        //打印结果
        OutputUtils.debugList(logger, result);
    }

    /**
     * 测试所有交易中，最高的交易额是多少；
     */
    @Test
    public void testExamine7() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //最高的交易额
        Optional<Integer> highestValue = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        //打印结果
        logger.debug("result：{}", highestValue);
    }

    /**
     * 测试使用IntStream完成所有交易中，最高的交易额是多少；
     */
    @Test
    public void testExamine7ByIntStream() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //最高的交易额
        OptionalInt highestValue = transactions
                .stream()
                .mapToInt(Transaction::getValue)
                .max();

        //打印结果
        logger.debug("result：{}", highestValue);
    }

    /**
     * 测试找到交易额最小的交易
     */
    @Test
    public void testExamine8() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //处理数据
        Optional<Transaction> smallestTransaction = transactions
                .stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        //打印结果
        logger.debug("result：{}", smallestTransaction);
    }

    /**
     * 用另外一种方式测试找到交易额最小的交易
     */
    @Test
    public void testExamine8ByAnotherMethod() {
        //获取交易记录
        List<Transaction> transactions = this.transactionService.getTransactions();

        //处理数据
        Optional<Transaction> smallestTransaction = transactions
                .stream()
                .min(comparing(Transaction::getValue));

        //打印结果
        logger.debug("result：{}", smallestTransaction);
    }
}
