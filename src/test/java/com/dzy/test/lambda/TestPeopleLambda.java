package com.dzy.test.lambda;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by dengzhiyuan on 2017/3/27.
 */
public class TestPeopleLambda {

    private static List<Person> personList=new ArrayList<>();

    static {
        for (int i = 1; i <= 20; i++) {
            Person person=new Person();
            person.setAge(i);
            person.setName("xiaoming"+i);
            //待会儿筛选属性用到
            if (i%10==0){person.setAddress("shanghai10村");}else{
                person.setAddress("shanghai"+i+"村");
            }
            person.setCountry("中国"+i+"区");
            personList.add(person);
        }
    }


    @Test
    public void testLambda(){
        //下面我要将personList转化为peoplelist里面去，少一个或者多一个属性，看下我怎么做
        List<People> peopleList = personList.stream().map(person -> {
            People people = new People();
            BeanUtils.copyProperties(person, people);
            return people;
            //下面我做个工作，将shanghai 10村的人合并到一起，也就是说最终会输出17个人people，看看
        }).filter(distinctByKey(People::getAddress)).collect(Collectors.toList());

        peopleList.forEach(System.out::println);
    }

    /**
     * 定义一个过滤器进行去重
     * 不涉及到共享变量，没有线程安全问题
     * 为什么是这样写的，因为上面的filter是需要一个Predicate返回的参数的
     */
    public static<T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }
}
