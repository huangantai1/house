package com.kgc.service.impl;

import com.kgc.entity.Type;
import com.kgc.entity.TypeExample;
import com.kgc.mapper.TypeMapper;
import com.kgc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired private TypeMapper typeMapper;
    @Override
    public List<Type> getListType() {
        TypeExample typeExample=new TypeExample();

        return typeMapper.selectByExample(typeExample);
    }
}
