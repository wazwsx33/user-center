package com.peanut.usercenter.model.domain.request

/**
 * 用户登录请求
 *
 * @author wzy
 */
data class UserLoginRequest(
    val userAccount: String? = null,
    val userPassword: String? = null,
)
