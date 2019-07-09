package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.Users;
import com.kgc.entity.UsersCondition;
import com.kgc.entity.UsersExample;
import com.kgc.mapper.UsersMapper;
import com.kgc.service.UserService;
import com.kgc.utils.MD5Utils;
import com.kgc.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UserService {
    @Autowired private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUserByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        UsersExample usersExample=new UsersExample();
        //添加条件
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //管理员


        List<Users> list=usersMapper.selectByExample(usersExample);

        return new PageInfo<Users>(list);

    }

    @Override
    public PageInfo<Users> getUsers(UsersCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if(condition.getName()!=null){
            criteria.andNameLike("%"+condition.getName()+"%");
        }
        if(condition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }
        if(condition.getStartAge()!=null){
            criteria.andAgeGreaterThanOrEqualTo(condition.getStartAge());
        }
        if(condition.getEndAge()!=null){
            criteria.andAgeLessThanOrEqualTo(condition.getEndAge());
        }




        List<Users> list=usersMapper.selectByExample(usersExample);

        return new PageInfo<Users>(list);

    }

    @Override
    public int checkUserName(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(0);
        List<Users> list=usersMapper.selectByExample(usersExample);
        return list.size();//0表示没有，1表示存在

    }

    @Override
    public int addUser(Users users) {
        users.setIsadmin(0);
        String  mpassword=MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(mpassword);
        return usersMapper.insertSelective(users);



    }

    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list=usersMapper.selectByExample(usersExample);
        if(list.size()==0){
            return null;
        }else {
            return list.get(0);
        }

    }
}
