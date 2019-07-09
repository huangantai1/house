package com.kgc.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.service.DistrictService;
import com.kgc.utils.DistrictParam;
import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DistrictController {
    @Autowired
    DistrictService districtService;
    @RequestMapping("getDistrict")
    @ResponseBody
    public Map<String,Object>getDistrict(Page page,String name,DistrictParam districtParam){
        districtParam.setName(name);
        PageInfo<District> pageInfo = districtService.getList(page,districtParam);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;

    }
    @RequestMapping("index")
    @ResponseBody
    public Map<String,Object> getDistrict(Page page, HttpServletRequest request){
        String name = request.getParameter("name");
        DistrictParam districtParam=new DistrictParam();
        districtParam.setName(name);
        PageInfo<District> pageInfo = districtService.getList(page,districtParam);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;

    }
    @RequestMapping(value = "add",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Boolean add(District district){
        if(districtService.add(district)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping(value = "deleteDistrict")
    @ResponseBody
            public String deleteDistrict(Integer id){
    int temp=districtService.deleteDistrict(id);
     return "{\"result\":"+temp+"}";
    }
    @RequestMapping("getSingleDistrict")
    @ResponseBody
    public District getDistrict(Integer id){
        return districtService.getDistrictById(id);
    }
    @RequestMapping(value = "updateDistrict",produces = "text/html;charset=UTF-8;")
    @ResponseBody
    public String updateDistrict(District district){
        int temp=districtService.uodateDistrict(district);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping(value = "deleteDistricts")
    @ResponseBody
    public String deleteDistricts(String id){
        String[]arys=id.split(",");
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<arys.length;i++){
            list.add(Integer.parseInt(arys[i]));
        }
        int temp=districtService.deleteList(list);
        return "{\"result\":"+temp+"}";
    }


}
