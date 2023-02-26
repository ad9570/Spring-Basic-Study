package com.fastcampus.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
    public static void main(String[] args) throws Exception {
        // 1. DateTellerMVC 클래스의 객체 생성
        Class<?> clazz = Class.forName("com.fastcampus.ch2.DateTellerMVC");

        // 2. 모든 메서드 정보를 가져와서 배열에 저장
        Method[] methodArr = clazz.getDeclaredMethods();

        for (Method m : methodArr) {
            String name = m.getName();    // 메서드 이름
            Parameter[] paramArr = m.getParameters();    // 매개변수 목록
//			Class[] paramTypeArr = m.getParameterTypes();
            Class<?> returnType = m.getReturnType();    // 매서드의 반환타입

            StringJoiner paramList = new StringJoiner(", ", "(", ")");    // StringJoiner(구분자, 접두사, 접미사)

            for (Parameter param : paramArr) {
                String paramName = param.getName();
                Class<?> paramType = param.getType();

                paramList.add(paramType.getName() + " " + paramName);    // StringJoiner에 요소 추가
            }

            // 컴파일된 상태에선 매개변수 이름을 저장하지 않음 -> (arg0, arg1)
            // jdk 1.8 ~ : Reflection API -parameters 옵션 사용
            // 혹은 클래스 파일에서 추출
            // -> (request, response)
            System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
        }
    }
}