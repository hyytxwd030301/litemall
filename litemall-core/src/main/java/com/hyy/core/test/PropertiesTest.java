package com.hyy.core.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class PropertiesTest {
    @Autowired
    private Environment environment;
    public static void main(String[] args) {
        System.out.println(new PropertiesTest().environment.getProperty("name"));

    }
}
