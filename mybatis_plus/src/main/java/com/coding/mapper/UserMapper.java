package com.coding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coding.pojo.User;
import org.springframework.stereotype.Repository;

/**
 *继承BaseMapper<User>，CRUD就写完了
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    //自己的编写的mapper只需要继承BaseMapper<T> entity
    //所有的CRUD基本业务  95%

    //复杂业务
}
