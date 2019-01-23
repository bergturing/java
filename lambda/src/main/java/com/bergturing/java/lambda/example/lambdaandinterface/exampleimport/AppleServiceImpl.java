package com.bergturing.java.lambda.example.lambdaandinterface.exampleimport;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
@Service
public class AppleServiceImpl implements IAppleService {
    /**
     * 存储的苹果
     */
    private List<Apple> apples = new ArrayList<>();

    {
        this.apples.add(new Apple("red", 120));
        this.apples.add(new Apple("red", 140));
        this.apples.add(new Apple("red", 160));
        this.apples.add(new Apple("green", 120));
        this.apples.add(new Apple("green", 140));
        this.apples.add(new Apple("green", 160));
    }

    @Override
    public List<Apple> getApples() {
        return new ArrayList<>(this.apples);
    }
}