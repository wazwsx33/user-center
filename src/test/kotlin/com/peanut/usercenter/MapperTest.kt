package com.peanut.usercenter

import com.peanut.usercenter.mapper.UserMapper
import com.peanut.usercenter.model.User
import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert
import java.util.function.Consumer

@SpringBootTest
class MapperTest {
    @Resource
    lateinit var userMapper: UserMapper

    @Test
    fun testSelect() {
        println(("----- selectAll method test ------"))
        val userList: MutableList<User?> = userMapper.selectList(null)
        Assert.isTrue(5 == userList.size, "")
        userList.forEach(Consumer { x: User? -> println(x) })
    }
}