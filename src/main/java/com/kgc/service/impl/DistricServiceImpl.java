package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.entity.DistrictExample;
import com.kgc.entity.Street;
import com.kgc.mapper.DistrictMapper;
import com.kgc.mapper.StreetMapper;
import com.kgc.service.DistrictService;
import com.kgc.utils.DistrictParam;
import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistricServiceImpl implements DistrictService {
    @Autowired
    DistrictMapper districtMapper;
    @Autowired
    StreetMapper streetMapper;

    @Override
    public PageInfo<District> getListByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        DistrictExample districtExample=new DistrictExample();
        districtExample.setOrderByClause("id DESC");
        List<District> list = districtMapper.selectByExample(districtExample);
        PageInfo<District> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<District> getList(Page page, DistrictParam districtParam) {
        PageHelper.startPage(page.getPage(),page.getRows());
        DistrictExample districtExample=new DistrictExample();
        DistrictExample.Criteria criteria = districtExample.createCriteria();
        districtExample.setOrderByClause("id DESC");
        if(districtParam!=null){
            if(districtParam.getName()!=null&&!districtParam.getName().equals("")){
                criteria.andNameLike("%"+districtParam.getName()+"%");
            }
        }
        List<District> list = districtMapper.selectByExample(districtExample);
        PageInfo<District> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean add(District district) {
        return districtMapper.insertSelective(district)>0?true:false;
    }
    @Transactional
    @Override
    public int deleteDistrict(Integer id) {

         districtMapper.deleteByPrimaryKey(id);
         streetMapper.deleteStreetByDistrict(id);
         return 1;
    }

    @Override
    public District getDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int uodateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    public int deleteList(List<Integer> ids) {
       return districtMapper.deleteList(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        DistrictExample districtExample=new DistrictExample();
        return districtMapper.selectByExample(districtExample);
    }


}
