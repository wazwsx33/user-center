package com.peanut.usercenter.service

import com.peanut.usercenter.model.domain.User
import jakarta.annotation.Resource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.DigestUtils

/**
 * 用户服务测试
 *
 * @author wzy
 */
@SpringBootTest
internal class UserServiceTest {

    @Resource
    private val userService: UserService? = null
    @Test
    fun testAddUser() {
        val user = User(
            username = "wzy",
            userAccount = "123",
            avatarUrl = "https://wx2.sinaimg.cn/mw690/a1ac48b7ly1hw3fsdspucj20u00u048q.jpg",
            gender = 0,
            userPassword = "xxx",
            phone = "123",
            email = "456",
        )
        val result = userService?.save(user)
        System.out.println(result)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun testDigest() {
        val digestPassword = DigestUtils.md5DigestAsHex("123".toByteArray())
        System.out.println(digestPassword)
    }

    @Test
    fun userRegister() {
        var userAccount = "wzy123456"
        var userPassword = "12345678"
        var checkPassword = "12345678"
        var result = userService?.userRegister(userAccount, userPassword, checkPassword, "1") ?: -1
        Assertions.assertEquals(-1, result)

        userAccount = "wzywatermelon222"
        result = userService?.userRegister(userAccount, userPassword, checkPassword, "1") ?: -1
        Assertions.assertTrue(result > 0)
    }
}