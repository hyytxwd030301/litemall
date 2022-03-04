package com.hyy.wx.controller;

import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallBrand;
import com.hyy.db.mapper.LitemallBrandMapper;
import com.hyy.db.service.LitemallBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/brand")
public class WxBrandController {

    @Autowired
    private LitemallBrandService litemallBrandService;

    @RequestMapping("/detail")
    public Object detail(Integer id){
        LitemallBrand litemallBrand = litemallBrandService.findById(id);
        return ResultUtil.ok(litemallBrand);

    }

}
