package com.fastcampus.ch3.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/aop-context.xml");
        MyMath mm = (MyMath) ac.getBean("myMath");

        mm.add(4, 7);
        mm.multiply(2, 5);
        mm.add(6, 8, 12);
    }
}