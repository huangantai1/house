package com.kgc.mapper;

import com.kgc.entity.House;

import com.kgc.entity.HouseExample;
import com.kgc.utils.HouseCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    //查询用户所有
    //各层之间传递数据
    public List<House> getAllHouse(Integer uid);
    public House  getHouse(String id);
    public List<House> getHouseByIsPass(Integer ispass);
    public List<House> getHouseByDid(@Param("dname") String dname,@Param("sname") String sname);
    public List<House> getBorswerHouse(HouseCondition houseCondition);
}