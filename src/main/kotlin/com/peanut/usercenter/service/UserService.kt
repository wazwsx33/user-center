package com.peanut.usercenter.service

import com.baomidou.mybatisplus.extension.service.IService
import com.peanut.usercenter.model.domain.User

/**
 * @author wangzhenyu
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2025-07-03 17:24:02
 */
interface UserService : IService<User> {
    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 密码确认
     * @return 用户id
     */
    fun userRegister(userAccount: String?, userPassword: String?, checkPassword: String?): Long
}
