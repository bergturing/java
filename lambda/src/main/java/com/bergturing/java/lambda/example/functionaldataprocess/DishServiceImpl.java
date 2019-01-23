package com.bergturing.java.lambda.example.functionaldataprocess;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 菜肴服务实现类
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
@Service
public class DishServiceImpl implements IDishService {
    /**
     * 菜肴
     */
    private List<Dish> dishs = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 150, Dish.Type.FISH)
    );

    @Override
    public List<Dish> getDishs() {
        return new ArrayList<>(this.dishs);
    }
}
