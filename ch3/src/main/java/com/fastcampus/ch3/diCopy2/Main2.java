package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car {
}

@SuppressWarnings("unused")
class SportsCar extends Car {
}

@SuppressWarnings("unused")
class Truck extends Car {
}

class Engine {
}

@SuppressWarnings("all")
class AppContext {
    Map<Object, Object> map;    // 객체 저장소

    AppContext() {
//        map = new HashMap<>();
//        map.put("car", new SportsCar());
//        map.put("engine", new Engine());
        try {
            Properties p = new Properties();
            p.load(new FileReader("config2.txt"));   // txt파일의 내용을 Properties<String, String>에 저장
            map = new HashMap<>(p); // Properties<String, String>에 저장된 내용을 Map<Object, Object>에 저장

            for (Object key : map.keySet()) {
                Class<?> clazz = Class.forName((String) map.get(key));
                map.put(key, clazz.getDeclaredConstructor().newInstance()); // 클래스 이름으로 객체를 생성해 Map에 저장
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key) {
        return map.get(key);
    }
}

public class Main2 {
    public static void main(String[] args) {
        AppContext ac = new AppContext();

        Car car = (Car) ac.getBean("car");
        System.out.println("car = " + car);

        Engine engine = (Engine) ac.getBean("engine");
        System.out.println("engine = " + engine);
    }
}