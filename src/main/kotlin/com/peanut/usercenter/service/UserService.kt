package com.peanut.usercenter.service

import com.baomidou.mybatisplus.extension.service.IService
import com.peanut.usercenter.model.domain.User
import jakarta.servlet.http.HttpServletRequest

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

    /**
     * 用户登录
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param request 请求
     * @return 脱敏后的用户信息
     */
    fun userLogin(userAccount: String?, userPassword: String?, request: HttpServletRequest): User?

    /**
     * 获取脱敏后的用户信息
     * @param originUser 用户
     * @return 脱敏后的用户信息
     */
    fun getSafetyUser(originUser: User): User
}
