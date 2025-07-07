package com.peanut.usercenter.service

import com.peanut.usercenter.model.domain.User
import jakarta.annotation.Resource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

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
}