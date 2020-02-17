package com.hexhoc.springbootblog.user;

import com.hexhoc.springbootblog.common.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public User getUserDetailById(Long loginUserId) {
        return userRepository.findById(loginUserId).get();
    }

    @Override
    public Boolean updatePassword(Long loginUserId, String originalPassword, String newPassword) {
        // TODO: 25.07.2021 add transaction and try catch

        Optional<User> userOptional = userRepository.findById(loginUserId);
        //The current user is not empty to make changes
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //Compare whether the original password is correct
            if (originalPasswordMd5.equals(user.getPassword())) {
                //Set a new password and modify
                user.setPassword(newPasswordMd5);
                userRepository.save(user);
                //Return true if the modification is successful
                return true;

            }
        }
        return false;

    }

    @Override
    public Boolean updateName(Long loginUserId, String loginUserName, String nickName) {
        // TODO: 25.07.2021 add transaction and try catch
        Optional<User> userOptional = userRepository.findById(loginUserId);
        //The current user is not empty to make changes
        if (userOptional.isPresent()) {
            //Modify information
            User user = userOptional.get();
            user.setUsername(loginUserName);
            user.setNickname(nickName);
            userRepository.save(user);
            //Return true if the modification is successful
            return true;
        }
        return false;
    }
}
