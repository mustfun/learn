package com.dzy.test.ordiry;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2017/6/26
 * @since 1.0
 */
public class TestNullPointer {

    @Test
    public void testNul(){
        List<String> list=null;

        List<Integer> collect = list.stream().map(String::hashCode).collect(Collectors.toList());

        System.out.println(collect);

    }
}
