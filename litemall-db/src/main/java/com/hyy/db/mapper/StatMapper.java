package com.hyy.db.mapper;

import java.util.List;
import java.util.Map;

public interface StatMapper {
    List<Map> statUser();

    List<Map> statOrder();

    List<Map> statGoods();
}