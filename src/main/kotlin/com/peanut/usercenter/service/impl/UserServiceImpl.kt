package com.peanut.usercenter.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.peanut.usercenter.mapper.UserMapper
import com.peanut.usercenter.model.domain.User
import com.peanut.usercenter.service.UserService
import org.springframework.stereotype.Service

/**
 * @author wangzhenyu
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-07-03 17:24:02
 */
@Service
class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService




