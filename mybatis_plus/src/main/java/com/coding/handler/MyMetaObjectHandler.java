package com.coding.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入填充
    @Override
    public void insertFill(MetaObject metaObject) {
        //自动插入时间
        log.info("insertFile running......");

        //setFieldValByName(自动填充的字段，填充的值，metaObject)
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("modifyTime",new Date(),metaObject);

    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("updateFile running......");
        this.setFieldValByName("modifyTime",new Date(),metaObject);
    }
}
