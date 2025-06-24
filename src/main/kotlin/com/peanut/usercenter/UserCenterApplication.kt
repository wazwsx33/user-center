package com.peanut.usercenter

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import javax.sql.DataSource


@MapperScan("com.peanut.usercenter.mapper")
@SpringBootApplication
class UserCenterApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<UserCenterApplication>(*args)
        }
    }

    @Primary
    @Bean("db1SqlSessionFactory")
    @Throws(Exception::class)
    fun db1SqlSessionFactory(dataSource: DataSource): SqlSessionFactory? {
        /**
         * 使用 mybatis plus 配置
         */
        val b1 = MybatisSqlSessionFactoryBean()
        b1.setDataSource(dataSource)
        return b1.getObject()
    }
}



