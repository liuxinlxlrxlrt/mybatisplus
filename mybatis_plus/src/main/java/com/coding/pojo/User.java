package com.coding.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //主键生成策略
    @TableId(type = IdType.AUTO)
    private Long id;
    private  String name;
    private int age;
    private String email;

    //时间操作，希望自动完成，new Date();
    //方式一：数据库级别的自动填充
    //方式二：数据库MP级别的自动填充时间
    @TableField(fill = FieldFill.INSERT) //插入的时候自动填充
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //插入更新的时候自动填充
    private Date modifyTime;

    //version范围：int,Integer,Long,long,Date,TimsStamp
    @Version //注解千万不要忘记 // 乐观锁添加注解
    private Integer version;

    //逻辑删除字段
    @TableLogic
    private Integer deleted;
}
