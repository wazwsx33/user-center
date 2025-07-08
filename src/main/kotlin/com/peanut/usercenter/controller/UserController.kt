package com.peanut.usercenter.controller

import com.peanut.usercenter.model.domain.User
import com.peanut.usercenter.model.domain.request.UserLoginRequest
import com.peanut.usercenter.model.domain.request.UserRegisterRequest
import com.peanut.usercenter.service.UserService
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 用户接口
 *
 * @author wzy
 */
@RestController
@RequestMapping("/user")
class UserController {

    @Resource
    private lateinit var userService: UserService

    @PostMapping("/register")
    fun userRegister(@RequestBody userRegisterRequest: UserRegisterRequest?): Long? {
        userRegisterRequest ?: return null
        val userAccount = userRegisterRequest.userAccount
        val userPassword = userRegisterRequest.userPassword
        val checkPassword = userRegisterRequest.checkPassword
        if (userAccount.isNullOrBlank() || userPassword.isNullOrBlank() || checkPassword.isNullOrBlank()) {
            return null
        }
        return userService.userRegister(
            userAccount = userAccount,
            userPassword = userPassword,
            checkPassword = checkPassword
        )

    }

    @PostMapping("/login")
    fun userLogin(@RequestBody userLoginRequest: UserLoginRequest?, request: HttpServletRequest): User? {
        userLoginRequest ?: return null
        val userAccount = userLoginRequest.userAccount
        val userPassword = userLoginRequest.userPassword
        if (userAccount.isNullOrBlank() || userPassword.isNullOrBlank()) {
            return null
        }
        return userService.userLogin(
            userAccount = userAccount,
            userPassword = userPassword,
            request = request
        )
    }
}
