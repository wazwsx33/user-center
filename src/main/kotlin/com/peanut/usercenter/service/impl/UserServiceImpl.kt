package com.peanut.usercenter.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.peanut.usercenter.mapper.UserMapper
import com.peanut.usercenter.model.domain.User
import com.peanut.usercenter.service.UserService
import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils

/**
 * @author wangzhenyu
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-07-03 17:24:02
 */
@Service
class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService {

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
        val salt = "salt" //二次混淆密码
        val digestPassword = DigestUtils.md5DigestAsHex("$salt + $userPassword".toByteArray())

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
}




