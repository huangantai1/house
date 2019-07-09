package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.House;
import com.kgc.utils.HouseCondition;
import com.kgc.utils.Page;

import java.util.List;

public interface HouseService{
    public int addHouse(House house);
    public PageInfo<House> getHouseByUser(Integer id, Page page);
    public House getHouse(String id);
    public  int updateHouse(House house);
    public int delHouse(String id,Integer isdel);
    public PageInfo<House> getHouseByIspass( Page page,Integer ispass);
    public int houseNoPass(String id,Integer ispass);
    public PageInfo<House> getHouseByDid(String sname,String dname,Page page);
    public PageInfo<House> getBorswerHouse(HouseCondition houseCondition);
}
