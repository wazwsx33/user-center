package com.peanut.usercenter.model.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableLogic
import com.baomidou.mybatisplus.annotation.TableName
import java.util.*

/**
 * 用户
 * @TableName user
 */
@TableName(value = "user")
data class User (
    /**
     * id
     */
    @TableId(type = IdType.AUTO) val id: Long? = null,

    /**
     * 用户昵称
     */
    val username: String? = null,

    /**
     * 账号
     */
    val userAccount: String? = null,

    /**
     * 用户头像
     */
    val avatarUrl: String? = null,

    /**
     * 性别
     */
    val gender: Int? = null,

    /**
     * 密码
     */
    val userPassword: String? = null,

    /**
     * 电话
     */
    val phone: String? = null,

    /**
     * 邮箱
     */
    val email: String? = null,

    /**
     * 用户状态  0-正常
     */
    val userStatus: Int? = null,

    /**
     * 创建时间
     */
    val createTime: Date? = null,

    /**
     * 更新时间
     */
    val updateTime: Date? = null,

    /**
     * 是否删除
     */
    @TableLogic val isDelete: Int? = null,
)