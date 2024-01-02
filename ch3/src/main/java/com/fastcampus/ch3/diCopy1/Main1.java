package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
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

public class Main1 {
    public static void main(String[] args) throws Exception {
        Car car = (Car) getObject("car");
        System.out.println("car = " + car);

        Engine engine = (Engine) getObject("engine");
        System.out.println("engine = " + engine);
    }

    static Object getObject(String key) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config1.txt"));

        Class<?> clazz = Class.forName(p.getProperty(key));
        return clazz.getDeclaredConstructor().newInstance();
    }

//    static Car getCar() throws Exception {
//        Properties p = new Properties();
//        p.load(new FileReader("config1.txt"));
//
//        Class<?> clazz = Class.forName(p.getProperty("car"));
//        return (Car) clazz.getDeclaredConstructor().newInstance();
//    }
}