package com.dzy.test.ordiry;

import com.dzy.model.UserInfo;
import com.dzy.util.crypto.MD5Util;
import org.junit.Test;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by dengzhiyuan on 16/10/26.
 */
public class TestLambda {

    @Test
    public void testLambda(){
        List<UserInfo> list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
             UserInfo user=new UserInfo();
            user.setId((long)i);
            user.setUname("itar"+i);
            user.setUnumber("20123023"+i);
            list.add(user);
        }

        //list.stream().forEach(System.out::println);
        /**
         * 改变list里面的一个值
         */
        list.stream().map(user->{
            user.setUnumber(MD5Util.toMD5(user.getUnumber()));
            return user;
        }).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUnumber());

        }

        System.out.println("==================================");
        /**
         * 拼接一个newlist
         */
        List<String> collect = list.stream().map(user -> user.getUnumber()).collect(Collectors.toList());
        for (int i = 0; i < collect.stream().count(); i++) {
            System.out.println(collect.get(i));

        }


    }
}
