package com.hexhoc.springbootblog.user;

public interface UserService {

    User login(String userName, String password);

    /**
     * Get user information
     *
     * @param loginUserId
     * @return
     */
    User getUserDetailById(Integer loginUserId);

    /**
     * Modify the password of the currently logged-in user
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * Modify the name information of the currently logged in user
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
