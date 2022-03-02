package com.hyy;

import com.hyy.db.domain.LitemallBrand;
import com.hyy.db.domain.LitemallCoupon;
import com.hyy.db.mapper.LitemallCategoryMapper;
import com.hyy.db.service.LitemallAdService;
import com.hyy.db.service.LitemallBrandService;
import com.hyy.db.service.LitemallCouponService;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LitemallDbApplicationTests {
    @Autowired
    private LitemallAdService litemallAdService;
    @Autowired
    private LitemallBrandService litemallBrandService;

    @Autowired
    private LitemallCategoryMapper litemallCategoryMapper;
    @Autowired
    private LitemallCouponService litemallCouponService;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        /*List<LitemallAd> litemallAdList = litemallAdService.index();
        System.out.println(litemallAdList);*/
        /*List<LitemallBrand> litemallBrandList = litemallBrandService.initFind(1, 10);
        System.out.println(litemallBrandList);*/
        List<LitemallCoupon> litemallCouponList = litemallCouponService.findInit(1, 3);
        System.out.println(litemallCouponList);
    }

    @Test
    public void test3(){

    }

}
