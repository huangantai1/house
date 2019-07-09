package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.entity.Street;
import com.kgc.utils.DistrictParam;
import com.kgc.utils.Page;

import java.util.List;

public interface DistrictService {
    public PageInfo<District> getListByPage(Page page);
    public PageInfo<District> getList(Page page, DistrictParam districtParam);
    public boolean add(District district);
    public int deleteDistrict(Integer id);
    public District getDistrictById(Integer id);
    public int uodateDistrict(District district);
    public int deleteList(List<Integer> ids);
    public List<District> getAllDistrict();

}
