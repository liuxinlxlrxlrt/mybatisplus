package com.coding.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement  //增加事务的控制
@Configuration  //appliacitonContext.xml
@MapperScan(basePackages = {"com.coding.mapper"})
public class MybatisPlusConfig {

    //乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInnerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
}
