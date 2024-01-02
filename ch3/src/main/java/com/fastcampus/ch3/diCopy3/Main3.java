package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
class Car {
}

@Component
class SportsCar extends Car {
}

// @Component
class Truck extends Car {
}

@Component
class Engine {
}

@SuppressWarnings("all")
class AppContext {
    Map<Object, Object> map;    // 객체 저장소

    AppContext() {
        map = new HashMap<>();
        doComponentScan();
    }

    private void doComponentScan() {
        try {
            // 패키지 내의 클래스 목록 가져오기
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);
            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for (ClassPath.ClassInfo classInfo : set) {
                // 클래스를 하나씩 읽어 @Component 존재 여부 확인
                Class<?> clazz = classInfo.load();
                Component component = clazz.getAnnotation(Component.class);

                // @Component가 있는 클래스의 객체를 생성해 map에 저장
                if (component != null) {
                    String className = classInfo.getSimpleName();       // com.fastcampus.ch3.diCopy3.Car -> Car
                    String id = StringUtils.uncapitalize(className);    // Car -> car
                    map.put(id, clazz.getDeclaredConstructor().newInstance());
                }
            }
        } catch (Exception e) {
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

public class Main3 {
    public static void main(String[] args) {
        AppContext ac = new AppContext();

        SportsCar sportsCar = (SportsCar) ac.getBean("sportsCar");
        System.out.println("sportsCar = " + sportsCar);

        Truck truck = (Truck) ac.getBean("truck");
        System.out.println("truck = " + truck);

        Car car = (Car) ac.getBean(Car.class);
        System.out.println("car = " + car);

        Engine engine = (Engine) ac.getBean(Engine.class);
        System.out.println("engine = " + engine);
    }
}