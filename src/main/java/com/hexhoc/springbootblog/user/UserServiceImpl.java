package com.hexhoc.springbootblog.user;

import com.hexhoc.springbootblog.common.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User login(String username, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return userRepository.login(username, passwordMd5);
    }

    @Override
    public User getUserDetailById(Integer loginUserId) {
        return null;
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        return null;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        return null;
    }
}
