package com.coding;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
    public static void main(String[] args) {
        //1.创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //2.全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取当前项目路径
        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setOutputDir("D:\\javaCode\\21_ProjectStorageFolder\\TestNG\\mybatisplus\\mybatisplus_autogeneration\\src\\main\\java");
        gc.setAuthor("Coding");
        gc.setOpen(false);//生成后是否打开资源管理器
        gc.setFileOverride(false);//重新生成文件时是否覆盖
        gc.setIdType(IdType.ID_WORKER_STR);//主键策略
        gc.setServiceName("%sService");//所有自动生成的Service接口的首字母I去除
        gc.setDateType(DateType.ONLY_DATE); //设置日期类型
        gc.setSwagger2(true); //实体属性Swagger2注解

        mpg.setGlobalConfig(gc);

        //3.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&serverTimezone=GMT%2B8");
        dsc.setUsername("root");
        dsc.setPassword("lx@lx19870613");
        dsc.setDbType(DbType.MYSQL);//设置数据库类型
        mpg.setDataSource(dsc);

        //4.生成的包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("edu");
        pc.setParent("com.coding");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //5.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("edu" + "_\\w*");//映射所有的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库映射到实体类的策略
        strategy.setTablePrefix("edu_");//不生成表的前缀

        strategy.setEntityLombokModel(true);//自动添加lombok的注解

        //逻辑删除
        strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去除布尔值的is_前缀

        //自动填充
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFill = new ArrayList<>();
        tableFill.add(gmtCreate);
        tableFill.add(gmtModified);
        strategy.setTableFillList(tableFill);

        //设置乐观锁列
        strategy.setVersionFieldName("version");

        /**
         * RestFull API 表现层状态转化
         * 	REST，即Representational State Transfer的缩写。直接翻译的意思是"表现层状态转化"。
         * 	它是一种互联网应用程序的API设计理念：URL定位资源，用HTTP动词（GET,POST,DELETE,DETC）描述操作。
         */
        strategy.setRestControllerStyle(true);
        //url驼峰命名 转化为 _
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        //6.执行
        mpg.execute();
    }
}
