package com.hyy.db.service.impl;

import com.hyy.db.domain.LitemallOrder;
import com.hyy.db.domain.LitemallOrderExample;
import com.hyy.db.mapper.LitemallOrderMapper;
import com.hyy.db.service.LitemallOrderService;
import com.hyy.db.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LitemallOrderServiceImpl implements LitemallOrderService {
    @Autowired
    private LitemallOrderMapper litemallOrderMapper;

    @Override
    public Map<String, Integer> orderInfo(Integer userId){
        LitemallOrderExample litemallOrderExample=new LitemallOrderExample();
        litemallOrderExample.or().andDeletedEqualTo(false);
        List<LitemallOrder> litemallOrderList = litemallOrderMapper.selectByExample(litemallOrderExample);
        int nopaid=0;
        int noship=0;
        int noreceipt=0;
        int nocomment=0;
        if (litemallOrderList==null){
            return null;
        }
        for (LitemallOrder litemallOrder:litemallOrderList){
            if (OrderUtil.isCreateStatus(litemallOrder)){
                nopaid++;
            }else if (OrderUtil.isPayStatus(litemallOrder)){
                noship++;
            }else if (OrderUtil.isShipStatus(litemallOrder)){
                noreceipt++;
            }else if (OrderUtil.isConfirmStatus(litemallOrder)){
                nocomment++;
            }
        }

        Map<String,Integer> orderInfo=new HashMap<>();
        orderInfo.put("nopaid",nopaid);
        orderInfo.put("noship",noship);
        orderInfo.put("noreceipt",noreceipt);
        orderInfo.put("nocomment",nocomment);
        return orderInfo;
    }
}
