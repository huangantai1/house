package com.kgc.protal.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.entity.House;
import com.kgc.entity.Type;
import com.kgc.entity.Users;
import com.kgc.service.DistrictService;
import com.kgc.service.HouseService;
import com.kgc.service.TypeService;
import com.kgc.service.UserService;
import com.kgc.utils.HouseCondition;
import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;


@Controller()
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private UserService userService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("goFaBu")
    public String goFaBu(Model model) {
        List<Type> types = typeService.getListType();
        List<District> districts = districtService.getAllDistrict();
        model.addAttribute("types", types);
        model.addAttribute("districts", districts);
        return "fabu";
    }

    @RequestMapping(value = "addHouse")
    public String addHouse(HttpSession httpSession, House house, @RequestParam(name = "pfile") CommonsMultipartFile pfile) throws Exception {
        String filename = pfile.getOriginalFilename();
        String expname = filename.substring(filename.lastIndexOf("."));
        String saveFileName = System.currentTimeMillis() + expname;
        String path = "d:/images/" + saveFileName;
        File savefile = new File(path);
        pfile.transferTo(savefile);
        house.setId(System.currentTimeMillis() + "");
        Users user = (Users) httpSession.getAttribute("loginInfo");
        house.setUserId(user.getId());
        house.setPath(saveFileName);
        house.setIsdel(0);
        house.setIspass(0);
        int temp = houseService.addHouse(house);
        if (temp > 0) {
            return "guanli";
        } else {
            return "FaBu";
        }


    }
    @RequestMapping("getHouse")
    public String getHouse(Page page,HttpSession httpSession,Model model) throws Exception{
        Users user = (Users) httpSession.getAttribute("loginInfo");
        Integer uid=user.getId();
        PageInfo<House> info = houseService.getHouseByUser(uid, page);
        model.addAttribute("info",info);
        return "guanli";


    }
    @RequestMapping("goUpdate")
    public String goUpdate(String id,Model model){
        House house = houseService.getHouse(id);
        model.addAttribute("house",house);
        return "update";
    }
    @RequestMapping("updateHouse")
    public String goUpdate(String oldPath,@RequestParam(name = "pfile") CommonsMultipartFile pfile,House house)throws Exception{
        String filename = pfile.getOriginalFilename();
        if(filename.equals("")){

        }else {
            String expname=filename.substring(filename.lastIndexOf("."));
            String saveFileName = System.currentTimeMillis() + expname;
            String path = "d:/images/" + saveFileName;
            File savefile = new File(path);
            pfile.transferTo(savefile);
            new File("d://images//"+oldPath).delete();
            house.setPath(saveFileName);

        }
        houseService.updateHouse(house);
        return "redirect:getHouse";
    }
    @RequestMapping("delHouse")
    public String delHouse(String id){
        houseService.delHouse(id,1);
        return "redirect:getHouse";
    }
    @RequestMapping(value = "getBorswerHouse")
    public String getBorswerHouse(HouseCondition condition, Model model){
        PageInfo<House> pageInfo = houseService.getBorswerHouse(condition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",condition);
        return "list";

    }
}
