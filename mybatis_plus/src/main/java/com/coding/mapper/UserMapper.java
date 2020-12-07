package com.coding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.pojo.User;
import org.springframework.stereotype.Repository;

/**
 *继承BaseMapper<User>，CRUD就写完了
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    //自己的编写的mapper只需要继承BaseMapper<T> entity
}
