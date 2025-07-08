package com.peanut.usercenter.model.domain.request

import java.io.Serializable

/**
 * 用户注册请求
 *
 * @author wzy
 */
data class UserRegisterRequest(
    val userAccount: String? = null,
    val userPassword: String? = null,
    val checkPassword: String? = null
) : Serializable
