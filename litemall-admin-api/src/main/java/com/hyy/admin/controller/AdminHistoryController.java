package com.hyy.admin.controller;

import com.hyy.core.util.ResultUtil;
import com.hyy.db.domain.LitemallSearchHistory;
import com.hyy.db.service.LitemallHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/history")
public class AdminHistoryController {

    @Autowired
    private LitemallHistoryService litemallHistoryService;

    @RequestMapping("/list")
    public Object list(Integer userId, String keyword,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order){
        List<LitemallSearchHistory> litemallHistoryList = litemallHistoryService.findAll(userId,keyword,page,size,sort,order);

        return ResultUtil.ok(litemallHistoryList);
    }
}
