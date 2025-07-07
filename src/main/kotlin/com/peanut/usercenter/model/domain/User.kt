package com.peanut.usercenter.model.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.Data
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
    private val username: String? = null,

    /**
     * 账号
     */
    private val userAccount: String? = null,

    /**
     * 用户头像
     */
    private val avatarUrl: String? = null,

    /**
     * 性别
     */
    private val gender: Int? = null,

    /**
     * 密码
     */
    private val userPassword: String? = null,

    /**
     * 电话
     */
    private val phone: String? = null,

    /**
     * 邮箱
     */
    private val email: String? = null,

    /**
     * 用户状态  0-正常
     */
    private val userStatus: Int? = null,

    /**
     * 创建时间
     */
    private val createTime: Date? = null,

    /**
     * 更新时间
     */
    private val updateTime: Date? = null,

    /**
     * 是否删除
     */
    private val idDelete: Int? = null,
)