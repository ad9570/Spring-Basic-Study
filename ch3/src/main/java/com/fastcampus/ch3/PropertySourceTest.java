package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

@Component
@PropertySource("setting.properties")
class SysInfo {
    @Value("#{systemProperties['user.timezone']}")
    String timeZone;
    @Value("#{systemEnvironment['LOCALAPPDATA']}")
    String appData;
    @Value("${autosaveDir}")
    String autosaveDir;
    @Value("${autosaveItvl}")
    int autosaveItvl;
    @Value("${autosaveAct}")
    boolean autosaveAct;

    @Override
    public String toString() {
        return "SysInfo{" +
                "timeZone='" + timeZone + '\'' +
                ", appData='" + appData + '\'' +
                ", autosaveDir='" + autosaveDir + '\'' +
                ", autosaveItvl=" + autosaveItvl +
                ", autosaveAct=" + autosaveAct +
                '}';
    }
}

public class PropertySourceTest {
    public static void main(String[] args) {
        Properties prop = System.getProperties();
        System.out.println("System.getProperties() = " + prop + "\n");

        Map<String, String> map = System.getenv();
        System.out.println("System.getenv() = " + map + "\n");

        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
        System.out.println("getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
    }
}
