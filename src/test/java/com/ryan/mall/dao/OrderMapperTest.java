package com.ryan.mall.dao;

import com.ryan.mall.MallApplicationTests;
import com.ryan.mall.pojo.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ryan Li
 * @date 2019/12/12
 */
public class OrderMapperTest extends MallApplicationTests {

    @Autowired(required=false)
    private OrderMapper orderMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        Order order = orderMapper.selectByPrimaryKey(1);
        System.out.println(order.toString());
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
        Order order = orderMapper.selectByPrimaryKey(1);
        orderMapper.updateByPrimaryKey(order);
        System.out.println(order.toString());
    }
}