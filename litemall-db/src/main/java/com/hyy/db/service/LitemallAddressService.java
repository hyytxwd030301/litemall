package com.hyy.db.service;

import com.hyy.db.domain.LitemallAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

public interface LitemallAddressService {
    public List<LitemallAddress> findAllByPageAndSize(Integer userId,Integer page,Integer size,String city);
    Boolean add(LitemallAddress litemallAddress);


    List<LitemallAddress> findAll(Integer page, Integer size, String sort, String order);
}
