package com.hyy;

import com.hyy.core.config.SystemConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class LitemallWxApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        Integer brand = SystemConfig.getBrand();
        System.out.println(brand);
    }

}
