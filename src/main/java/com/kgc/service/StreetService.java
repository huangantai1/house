package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Street;
import com.kgc.utils.Page;

import java.util.List;

public interface StreetService {
    public PageInfo<Street> getListByPage(Integer id,Page page);
    public List<Street> getAllStreet(Integer id);
}
