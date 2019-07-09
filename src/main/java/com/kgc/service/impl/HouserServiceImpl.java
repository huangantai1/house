package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.House;
import com.kgc.mapper.HouseMapper;
import com.kgc.service.HouseService;
import com.kgc.utils.HouseCondition;
import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class HouserServiceImpl implements HouseService {
    @Autowired private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUser(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> allHouse = houseMapper.getAllHouse(id);
        return new PageInfo<>(allHouse);

    }

    @Override
    public House getHouse(String id) {
        return houseMapper.getHouse(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delHouse(String id, Integer isdel) {
        House house=new House();
        house.setId(id);
        house.setIsdel(isdel);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByIspass(Page page, Integer ispass) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list = houseMapper.getHouseByIsPass(ispass);
        return new PageInfo<>(list);
    }

    @Override
    public int houseNoPass(String id, Integer ispass) {
        House house=new House();
        house.setId(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house);

    }

    @Override
    public PageInfo<House> getHouseByDid(String sname, String dname,Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list = houseMapper.getHouseByDid(sname, dname);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<House> getBorswerHouse(HouseCondition houseCondition) {
        PageHelper.startPage(houseCondition.getPage(),houseCondition.getRows());
        List<House> list = houseMapper.getBorswerHouse(houseCondition);
        return new PageInfo<>(list);
    }
}
