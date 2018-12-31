package cn.harveychan.kaimaa.service;

import cn.harveychan.kaimaa.po.User;

public interface UserService {

    public User checkUser(String username, String password);
}
