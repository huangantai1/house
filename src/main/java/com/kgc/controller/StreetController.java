package com.kgc.controller;



import com.github.pagehelper.PageInfo;
import com.kgc.entity.Street;
import com.kgc.service.StreetService;
import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class StreetController {


        @Autowired
        StreetService streetService;
        @RequestMapping("getStreetByDid")
        @ResponseBody
        public Map<String,Object> getStreet(Page page,Integer id){
            PageInfo<Street> pageInfo = streetService.getListByPage(id,page);
            Map<String,Object> map=new HashMap<>();
            map.put("total",pageInfo.getTotal());
            map.put("rows",pageInfo.getList());
            return map;

        }
    }

