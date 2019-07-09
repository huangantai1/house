package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.entity.DistrictExample;
import com.kgc.entity.Street;
import com.kgc.entity.StreetExample;
import com.kgc.mapper.StreetMapper;
import com.kgc.service.StreetService;
import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    StreetMapper streetMapper;
    @Override
    public PageInfo<Street> getListByPage(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());

        StreetExample streetExample=new StreetExample();
        //添加条件
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(id);  //通过区域编号查询当前街道

        List<Street> list=streetMapper.selectByExample(streetExample);
        return new PageInfo<Street>(list);
    }

    @Override
    public List<Street> getAllStreet(Integer id) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        if(id!=null) {
            criteria.andDistrictIdEqualTo(id);
        }
        return streetMapper.selectByExample(streetExample);
    }
}
