package com.fastcampus.ch3.diCopy4;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
class Car {
    @Autowired
    Engine engine;
//    @Autowired
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

@Component
class Engine {}

@Component
class Door {}

class AppContext {
    Map<Object, Object> map;    // 객체 저장소

    AppContext() {
        map = new HashMap<>();
        doComponentScan();
        // 자동으로 객체를 연결 - by Type
        doAutowired();
    }

    private void doComponentScan() {
        try {
            // 패키지 내의 클래스 목록 가져오기
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);
            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

            for (ClassPath.ClassInfo classInfo : set) {
                // 클래스를 하나씩 읽어 @Component 존재 여부 확인
                Class<?> clazz = classInfo.load();
                Component component = clazz.getAnnotation(Component.class);

                // @Component가 있는 클래스의 객체를 생성해 map에 저장
                if (component != null) {
                    String className = classInfo.getSimpleName();
                    String id = StringUtils.uncapitalize(className);
                    map.put(id, clazz.getDeclaredConstructor().newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        try {
            // @Autowired가 붙어 있는 클래스의 타입에 맞는 객체를 map에서 검색
            for (Object bean : map.values()) {
                for (Field field : bean.getClass().getDeclaredFields()) {
                    // 검색해서 가져온 객체를 @Autowired가 붙어 있는 iv에 연결
                    if (field.getAnnotation(Autowired.class) != null)
                        field.set(bean, getBean(field.getType()));  // car.engine = bean(field의 타입);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key) {    // by Name
        return map.get(key);        // map의 key 검색
    }

    Object getBean(Class<?> clazz) {        // by Type
        for (Object value : map.values()) { // map의 value 검색
            if (clazz.isInstance(value))
                return value;
        }
        return null;
    }
}

public class Main4 {
    public static void main(String[] args) {
        AppContext ac = new AppContext();

        Car car = (Car) ac.getBean(Car.class);
        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean("door");

        // 수동으로 객체를 연결
//        car.engine = engine;
//        car.door = door;

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);
    }
}