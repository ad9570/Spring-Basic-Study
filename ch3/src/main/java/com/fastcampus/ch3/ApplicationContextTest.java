package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@Scope("prototype")
class Seat {}

@Component
class EngineType {}
@Component
class TurboEngine extends EngineType {}
@Component
class SuperEngine extends EngineType {}

@Component
class Vehicle {
//    @Value("blue")
    String color;
//    @Value("100")
    int oil;
//    @Autowired
    EngineType engineType;
//    @Autowired
    Seat[] seats;

    public Vehicle() {}
    @Autowired  // 다른 생성자가 없을 경우 생략 가능(기본적으로 자동주입)
    public Vehicle(@Value("purple") String color, @Value("100") int oil, EngineType engineType, Seat[] seats) {
        // 주입할 bean이 없는 String, 기본형 타입은 반드시 @Value로 값 주입 해줘야함
        this.color = color;
        this.oil = oil;
        this.engineType = engineType;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engineType=" + engineType +
                ", seats=" + Arrays.toString(seats) +
                '}';
    }
}

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
        Vehicle vehicle = (Vehicle) ac.getBean("vehicle");  // 이름으로 빈 검색
        Vehicle vehicle2 = ac.getBean(Vehicle.class);       // 타입으로 빈 검색
        System.out.println("vehicle = " + vehicle);
        System.out.println("vehicle2 = " + vehicle2);

        // 정의된 빈의 이름을 배열로 반환
        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames()));
        // 정의된 빈의 개수를 반환
        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount());

        // true 빈의 정의가 포함되어 있는지 확인
        System.out.println("ac.containsBeanDefinition(\"vehicle\") = " + ac.containsBeanDefinition("vehicle"));
        // true 빈이 포함되어 있는지 확인
        System.out.println("ac.containsBean(\"vehicle\") = " + ac.containsBean("vehicle"));

        // 빈의 이름으로 타입을 알아낼 수 있음
        System.out.println("ac.getType(\"vehicle\") = " + ac.getType("vehicle"));
        // true 빈이 싱글톤인지 확인
        System.out.println("ac.isSingleton(\"vehicle\") = " + ac.isSingleton("vehicle"));
        // false 빈이 프로토타입인지 확인
        System.out.println("ac.isPrototype(\"vehicle\") = " + ac.isPrototype("vehicle"));
        // true
        System.out.println("ac.isPrototype(\"seat\") = " + ac.isPrototype("seat"));
        // "vehicle"라는 이름의 빈의 타입이 Vehicle인지 확인
        System.out.println("ac.isTypeMatch(\"vehicle\", Vehicle.class) = " + ac.isTypeMatch("vehicle", Vehicle.class));
        // 빈 vehicle에 @Component가 붙어있으면 반환
        System.out.println("ac.findAnnotationOnBean(\"vehicle\", Component.class) = " + ac.findAnnotationOnBean("vehicle", Component.class));
        // @Component가 붙은 빈의 이름을 배열로 반환
        System.out.println("ac.getBeanNamesForAnnotation(Component.class) = " + Arrays.toString(ac.getBeanNamesForAnnotation(Component.class)));
        // EngineType 또는 그 자손 타입인 빈의 이름을 배열로 반환
        System.out.println("ac.getBeanNamesForType(EngineType.class) = " + Arrays.toString(ac.getBeanNamesForType(EngineType.class)));
    }
}