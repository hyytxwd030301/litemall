package com.hyy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class PropertiesTest {
    @Autowired
    private Environment environment;
    @Test
    public void test1(){
        environment.getProperty("name");

    }
}
