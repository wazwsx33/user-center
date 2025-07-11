package com.peanut.usercenter.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.peanut.usercenter.constant.UserConstant
import com.peanut.usercenter.model.domain.User
import com.peanut.usercenter.model.domain.request.UserLoginRequest
import com.peanut.usercenter.model.domain.request.UserRegisterRequest
import com.peanut.usercenter.service.UserService
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
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
        val code = userRegisterRequest.code
        if (userAccount.isNullOrBlank() || userPassword.isNullOrBlank() || checkPassword.isNullOrBlank() || code.isNullOrBlank()) {
            return null
        }
        return userService.userRegister(
            userAccount = userAccount,
            userPassword = userPassword,
            checkPassword = checkPassword,
            code = code
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

    @PostMapping("/logout")
    fun userLogout(request: HttpServletRequest?): Int? {
        request ?: return null
        return userService.userLogout(request)
    }

    @GetMapping("/search")
    fun searchUsers(username: String?, request: HttpServletRequest): List<User> {
        if (!isAdmin(request)) {
            return emptyList()
        }
        val wrapper = QueryWrapper<User>()
        if (username?.isNotBlank() == true) {
            wrapper.like("username", username)
        }
        val userlist = userService.list(wrapper)
        return userlist.mapNotNull { userService.getSafetyUser(it) }
    }

    @PostMapping("/delete")
    fun deleteUser(@RequestBody id: Long?, request: HttpServletRequest): Boolean {
        if (id == null || id <= 0) {
            return false
        }
        if (!isAdmin(request)) {
            return false
        }
        return userService.removeById(id)
    }

    private fun isAdmin(request: HttpServletRequest): Boolean {
        val user = request.session.getAttribute(UserConstant.USER_LOGIN_STATE) as? User
        return user != null && user.role == UserConstant.ADMIN_ROLE
    }


    @GetMapping("/current")
    fun getCurrentUser(request: HttpServletRequest): User? {
        val currentUser = request.session.getAttribute(UserConstant.USER_LOGIN_STATE) as? User
        if (currentUser == null) {
            return null
        }
        //可能用户下次登录的时候，用户信息已经发生了变化，比如手机号邮箱等，这时session中缓存的数据还是老的，这里需要重新获取一下
        val newStateUser = userService.getById(currentUser.id)
        return userService.getSafetyUser(newStateUser)
    }



}
