package com.peanut.usercenter.model

import com.baomidou.mybatisplus.annotation.TableName
import lombok.Data

@TableName("`user`")
data class User (
    private val id: Long? = null,
    private val name: String? = null,
    private val age: Int? = null,
    private val email: String? = null,
)