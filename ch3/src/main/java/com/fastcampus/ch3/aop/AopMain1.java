package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain1 {
    public static void main(String[] args) throws ReflectiveOperationException {
        MyAdvice myAdvice = new MyAdvice();

        Class<MyClass> myClass = MyClass.class;
        Object obj = myClass.getDeclaredConstructor().newInstance();

        for (Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj);
        }
    }
}

@SuppressWarnings("unused")
class MyAdvice {
    Pattern p = Pattern.compile("a.*");

    // 패턴 : a로 시작하지 않음 && @Transactional 어노테이션 붙음
    boolean matches(Method m) {
        Matcher matcher = p.matcher(m.getName());
        boolean hasTransactional = m.getAnnotation(Transactional.class) != null;

        return !matcher.matches() && hasTransactional;
    }

    void invoke(Method m, Object obj, Object... args) throws ReflectiveOperationException {
        if (matches(m)) {
            System.out.println("[before : (" + Arrays.toString(args) + ")] {");
            m.invoke(obj, args);
            System.out.println("} [after : ()]");
        }
    }

    void invoke(Method m, Object obj) throws ReflectiveOperationException {
        if (matches(m)) {
            System.out.println("[before : ()] {");
            m.invoke(obj);
            System.out.println("} [after : ()]");
        }
    }
}

@SuppressWarnings("unused")
class MyClass {
    public void aaa() {
        System.out.println("aaa() is called.");
    }

    @Transactional
    public void bbb() {
        System.out.println("bbb() is called.");
    }

    public void ccc() {
        System.out.println("ccc() is called.");
    }
}