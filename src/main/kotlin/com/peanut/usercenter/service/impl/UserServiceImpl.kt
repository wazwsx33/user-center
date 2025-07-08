package com.peanut.usercenter.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.peanut.usercenter.mapper.UserMapper
import com.peanut.usercenter.model.domain.User
import com.peanut.usercenter.service.UserService
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils

/**
 * @author wangzhenyu
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-07-03 17:24:02
 */
@Service
class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService {

    companion object {
        private const val SALT = "salt" //二次混淆密码
        private val LOGGER = LoggerFactory.getLogger(UserServiceImpl::class.java)
        private const val USER_LOGIN_STATE = "user_login_state"
    }

    @Resource
    lateinit var userMapper: UserMapper

    override fun userRegister(
        userAccount: String?,
        userPassword: String?,
        checkPassword: String?
    ): Long {
        //非空校验
        if (userAccount.isNullOrBlank() || userPassword.isNullOrBlank() || checkPassword.isNullOrBlank()) {
            return -1
        }

        //长度校验
        if (userAccount.length < 4) {
            return -1
        }
        if (userPassword.length < 8 || checkPassword.length < 8) {
            return -1
        }

        //特殊字符校验
        if (userAccount.matches("""[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?`~\s]""".toRegex())) {
            return -1
        }

        if (userPassword != checkPassword) {
            return -1
        }

        //重复账户校验
        //放到最后，参数校验都通过后才查询数据库，避免资源浪费
        val count = userMapper.selectCount(QueryWrapper<User>().also {
            it.eq("userAccount", userAccount)
        })
        if (count > 0) {
            return -1
        }

        //密码加密
        val digestPassword = DigestUtils.md5DigestAsHex("$SALT + $userPassword".toByteArray())

        //创建对象
        val user = User(
            userAccount = userAccount,
            userPassword = digestPassword
        )

        val saveResult = save(user)
        if (!saveResult) {
            return -1
        }

        return user.id ?: -1
    }

    override fun userLogin(
        userAccount: String?,
        userPassword: String?,
        request: HttpServletRequest
    ): User? {
        //非空校验
        if (userAccount.isNullOrBlank() || userPassword.isNullOrBlank()) {
            return null
        }

        //长度校验
        if (userAccount.length < 4) {
            return null
        }
        if (userPassword.length < 8) {
            return null
        }

        //特殊字符校验
        if (userAccount.matches("""[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?`~\s]""".toRegex())) {
            return null
        }

        //密码加密
        val digestPassword = DigestUtils.md5DigestAsHex("$SALT + $userPassword".toByteArray())
        //查询数据库
        val wrapper = QueryWrapper<User>().also {
            it.eq("userAccount", userAccount)
            it.eq("userPassword", digestPassword)
        }
        val user = userMapper.selectOne(wrapper)
        //用户不存在
        if (user == null) {
            LOGGER.info("user login failed, userAccount cannot match userPassword")
            return null
        }

        // 用户脱敏，防止敏感信息泄露
        val safetyUser = User(
            id = user.id,
            username = user.username,
            userAccount = user.userAccount,
            avatarUrl = user.avatarUrl,
            gender = user.gender,
            phone = user.phone,
            email = user.email,
            userStatus = user.userStatus,
            createTime = user.createTime,
        )

        //记录用户登录态
        request.session.setAttribute(USER_LOGIN_STATE, safetyUser)

        return safetyUser
    }
}




