package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Users;
import com.kgc.entity.UsersCondition;
import com.kgc.utils.Page;


public interface UserService {
    public PageInfo<Users> getUserByPage(Page page);
    public PageInfo<Users> getUsers(UsersCondition usersCondition);
    public int checkUserName(String name);
    public int addUser(Users users);
    public Users login(String username,String password);
}
