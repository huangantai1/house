package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Users;
import com.kgc.entity.UsersCondition;
import com.kgc.service.UserService;
import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser")
    @ResponseBody
    public Map<String,Object> getUser(Page page){
        PageInfo<Users> pageInfo= userService.getUserByPage(page);
        Map<String,Object> objs=new HashMap<>();
        objs.put("total",pageInfo.getTotal());
        objs.put("rows",pageInfo.getList());
        return objs;
    }
        @RequestMapping("getUser1")
        @ResponseBody
        public Map<String,Object> getUser(UsersCondition condition){

//调用业务
            PageInfo<Users> pageInfo= userService.getUsers(condition);
            Map<String,Object> objs=new HashMap<>();
            objs.put("total",pageInfo.getTotal());
            objs.put("rows",pageInfo.getList());
            return objs;
        }
}


