package com.hyy.core.config;

import com.hyy.db.domain.LitemallSystem;
import com.hyy.db.service.LitemallSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SystemInitConfig {
    @Autowired
    private LitemallSystemConfigService litemallSystemConfigService;

    private static Map<String,String> systemConfig=new HashMap<>();


    @PostConstruct
    public void init(){
        List<LitemallSystem> litemallSystemList = litemallSystemConfigService.findAll();
        for (LitemallSystem litemallSystem:litemallSystemList){
            systemConfig.put(litemallSystem.getKeyName(),litemallSystem.getKeyValue());
        }
        SystemConfig.setConfig(systemConfig);


    }

}
