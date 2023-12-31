package com.fastcampus.ch2;

@SuppressWarnings("unused")
public class Person {
    private Car car = new Car();

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}