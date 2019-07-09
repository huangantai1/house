package com.kgc.protal.controller;

import com.kgc.entity.District;
import com.kgc.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "districtConroller2")
@RequestMapping("/page/")
public class DiatrictController {
    @Autowired private DistrictService districtService;
    @RequestMapping("getDistrict")
    @ResponseBody
    public List<District> getDistrict(){
        List<District> list=districtService.getAllDistrict();
        return list;
    }
}
