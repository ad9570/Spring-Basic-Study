package com.fastcampus.ch3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
class Computer {
    @Autowired                  // byType 먼저 검색 -> Type 중복 시 byName 검색(기본 이름인 "타입의 첫글자 소문자")
    Core core;
    /*
    @Autowired                  // Type 중복 + 기본 이름 존재하지 않음 + 다른 이름 여러 개 존재 시 Autowire 불가능(에러)
    @Qualifier("windowsOS")     // "@Qualifier로 검색할 이름 지정"
     */
    @Resource(name = "macOS")   // 혹은 @Resource 사용 : byName 먼저 검색
    OperatingSystem os;
    @Value("32")                // 기본형, String : @Value로 값 주입, 기본형은 기본형으로 자동 변환
    int memory;
    @Autowired                  // @Autowired는 배열이나 컬렉션 대상으로도 타입에 맞는 1개의 객체만 주입 가능
    List<Storage> storage;

    @Override
    public String toString() {
        return "Computer{" +
                "core=" + core +
                ", os=" + os +
                ", memory=" + memory +
                ", storage=" + storage +
                '}';
    }
}

@Component("core")
class Core {
}                   // <bean id="core" class="com.fastcampus.ch3.Engine"/>

@Component("intel")
class Intel extends Core {
}

@Component("amd")
class Amd extends Core {
}

class OperatingSystem {
}

@Component("windowsOS")
class WindowsOS extends OperatingSystem {
}

@Component("macOS")
class MacOS extends OperatingSystem {
}

@Component
class Storage {
}

public class SpringDITestAnnotation {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config-component.xml");
        Computer computer = ac.getBean(Computer.class);

        System.out.println("computer = " + computer + "\n");

        try {
            Core core = (Core) ac.getBean("core");  // byName - 상속 등으로 같은 타입이 여러 개 존재 시 byName 사용
            System.out.println("core = " + core);
        } catch (BeansException e) {
            System.out.println(e.getMessage());
        }

        try {
            Core core = ac.getBean(Core.class);     // byType - 상속 등으로 같은 타입이 여러 개 존재 시 ERROR
            System.out.println("core = " + core);
        } catch (BeansException e) {
            System.out.println(e.getMessage());
        }
    }
}