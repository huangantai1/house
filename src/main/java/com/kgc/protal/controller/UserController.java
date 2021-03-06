package com.kgc.protal.controller;

import com.kgc.entity.Users;
import com.kgc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("userController2")
@RequestMapping("/page/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("checkUname")
    @ResponseBody
    public String  checkUname(String name){
        //调用业务
        int temp=userService.checkUserName(name);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("reg")
    public String reg(Users users){
      int temp=userService.addUser(users);
        if(temp>0){
            return "login";
        }else {
            return "reg";
        }
    }
    @RequestMapping("loginAction")
    public String loginAction(String name, String password, Model model, HttpSession session) throws  Exception{
        //调用业务实现注册
        Users user=userService.login(name,password);
        if(user==null){
            model.addAttribute("info","用户名和密码不正确");
            return "login";
        }else{
            //只要登入，使用session作用域保存登入的人
            session.setAttribute("loginInfo",user);
            //设置保存的有效时间
            session.setMaxInactiveInterval(300);  //以秒

            return "redirect:getHouse";   //用户登入后的管理页
        }
    }
}