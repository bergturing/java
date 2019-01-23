package com.bergturing.java.lambda.example.functionaldataprocess;

import java.util.List;

/**
 * 菜肴服务接口
 *
 * @author bergturing@qq.com
 * @apiNote 2019/1/23
 */
public interface IDishService {
    /**
     * 获取菜肴
     *
     * @return 获得的菜肴
     */
    List<Dish> getDishs();
}
