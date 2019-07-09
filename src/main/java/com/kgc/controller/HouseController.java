package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.House;

import com.kgc.entity.Users;
import com.kgc.service.HouseService;

import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller(value = "houseController2")
@RequestMapping("/")
public class HouseController {
    @Autowired
    HouseService houseService;
    @RequestMapping("getHouseYesPass")
    @ResponseBody
    public Map<String,Object> getHouseYesPass(Page page){
        PageInfo<House> pageInfo = houseService.getHouseByIspass(page,1);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;

    }
    @RequestMapping("houseNo")
    @ResponseBody
    public String getHouseNoPass(String id){
        //调用业务   1表示审核
        int temp=houseService.houseNoPass(id,0);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("getHouse")
    @ResponseBody
    public Map<String,Object> getHouse(String sname,String dname,Page page){

//调用业务
        PageInfo<House> pageInfo = houseService.getHouseByDid(sname, dname, page);
        Map<String,Object> objs=new HashMap<>();
        objs.put("total",pageInfo.getTotal());
        objs.put("rows",pageInfo.getList());
        return objs;
    }
}


