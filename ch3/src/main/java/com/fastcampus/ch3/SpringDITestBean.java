package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

@SuppressWarnings("unused")
class Car {
    String color;
    String oil;
    Engine engine;
    Door[] doors;

    public void setColor(String color) {
        this.color = color;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }

    public Car() {
        this("white", "electric", new Engine(), new Door[]{new Door(), new Door()});
    }

    public Car(String color, String oil, Engine engine, Door[] doors) {
        this.color = color;
        this.oil = oil;
        this.engine = engine;
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil='" + oil + '\'' +
                ", engine=" + engine +
                ", doors=" + Arrays.toString(doors) +
                '}';
    }
}

@SuppressWarnings("unused")
class Sedan extends Car {
    public Sedan() {
        super();
    }

    public Sedan(String color, String oil, Engine engine, Door[] doors) {
        super(color, oil, engine, doors);
    }
}

@SuppressWarnings("unused")
class Bus extends Car {
    public Bus() {
        super();
    }

    public Bus(String color, String oil, Engine engine, Door[] doors) {
        super(color, oil, engine, doors);
    }
}

class Engine {
}

class Door {
}

public class SpringDITestBean {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config-bean.xml");
        Car car = (Car) ac.getBean("car");          // byName

        /* xml의 <bean> 태그로 대체 : 클래스 멤버의 Setter를 이용하므로 Setter 메서드 필수

        Engine engine = ac.getBean(Engine.class);           // byType(형변환 불필요)
        Engine engine2 = (Engine) ac.getBean("engine");     // getBean 할 때마다 같은 객체 반환(scope="singleton")
        Door door1 = ac.getBean("door", Door.class);    // byName + byType(형변환 불필요)
        Door door2 = ac.getBean(Door.class);           // getBean 할 때마다 새로운 객체 반환(scope="prototype")

        car.setColor("gray");
        car.setOil("gasolin");
        car.setEngine(engine);
        car.setDoors(new Door[]{door1, door2});

         */

        System.out.println("car = " + car);

        Sedan sedan = ac.getBean("sedan", Sedan.class);
        Bus bus = ac.getBean(Bus.class);

        System.out.println("sedan = " + sedan);
        System.out.println("bus = " + bus);
    }
}