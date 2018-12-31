package cn.harveychan.kaimaa.service.impl;

import cn.harveychan.kaimaa.po.User;
import cn.harveychan.kaimaa.dao.UserRepository;
import cn.harveychan.kaimaa.service.UserService;
import cn.harveychan.kaimaa.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
