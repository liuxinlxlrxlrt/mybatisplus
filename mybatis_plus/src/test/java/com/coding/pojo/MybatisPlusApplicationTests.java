package com.coding.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coding.mapper.UserMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void queryAllTest() {
        //Wrapper:MP 自动构建sql
        //查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void insertTest() {
        /**
         * snowflake是Twitter开源的分布式ID生成算法，
         * 结果是一个long型的ID。
         * 其核心思想是：
         * 使用41bit作为毫秒数，10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID），
         * 12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），
         * 最后还有一个符号位，永远是0
         * 保证唯一
         */
        User user = new User();
        user.setName("coding3号");
        user.setAge(25);
        user.setEmail("1123456789@qq.com");
        int i = userMapper.insert(user);
        System.out.println(i);//受影响的行数
        System.out.println(user);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(1335606289199357955L);
        user.setName("coding2号");
        user.setEmail("111@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
        System.out.println(user);
    }

    //删除：悲观锁、乐观锁（面试）

    //逻辑删除 乐观锁

    /**
     * 删除：悲观锁、乐观锁（面试）
     * <p>
     * 悲观锁：认为总是出现问题，无论做什么东西，先锁上再做操作
     * <p>
     * 乐观锁：认为一般情况下不会出现问题，假设出现问题，再找解决方案
     * <p>
     * 逻辑删除：乐观锁
     * <p>
     * 版本控制
     * OptimisticLockerInnerInterceptor
     * 当要更新一条记录的时候，希望这条记录没有被别人更新
     * 乐观锁实现方式：
     * <p>
     * 取出记录时，获取当前version
     * 更新时，带上这个version
     * 执行更新时， set version = newVersion where version = oldVersion
     * 如果version不对，就更新失败
     */

    //测试修改成功
    @Test
    public void optimisticLockerTest() {
        //通过查询修改
        User user = userMapper.selectById(1L);
        user.setName("coding1号");
        user.setAge(11);
        user.setEmail("11111@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
        System.out.println(user);
    }

    //测试插入失败,模拟两个线程，测试结果
    @Test
    public void optimisticLockerFailTest() {
        //Thread 1 查询
        User user1 = userMapper.selectById(1L);
        //Thread 2 查询
        user1.setName("coding2号");
        user1.setAge(111);

        //thread 2 查询
        User user2 = userMapper.selectById(1L);
        user2.setName("coding5号");
        user2.setAge(222);

        //thread 2 执行更新，更新成功
        int i2 = userMapper.updateById(user2);
        System.out.println(i2);
        System.out.println(user2);

        //thread 1 执行更新，更新失败
        int i1 = userMapper.updateById(user1);
        System.out.println(i1);
        System.out.println(user1);
    }

    //查询
    @Test
    public void queryByIdTest(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //批量查询
    @Test
    public void selectBatchIdsTest(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    //简单的条件查询,map,简单的动态sql
    @Test
    public void selectByMapTest(){
        Map<String,Object> map =  new HashMap<>();
        map.put("name","coding");
        map.put("age",4);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //分页插件
    @Test
    public void selectPageTest(){
        Page<User> page = new Page<>(3,3);
        //wapper：条件构造器，模糊查询，排序、多表查询，子查询
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());//获取当前页编号
        System.out.println(page.getPages());//获取页数
        System.out.println(page.getSize());//获取当前页多少条
        System.out.println(page.getTotal());//获取总条数
        System.out.println(page.hasNext());//是否有上一页
        System.out.println(page.hasPrevious());//是否有下一页
    }

    //分页插件2
    @Test
    public void selectMapsPagTest(){
        Page<Map<String,Object>> page = new Page<>(3,3);
        //wapper：条件构造器，模糊查询，排序、多表查询，子查询
        Page<Map<String, Object>> e= userMapper.selectMapsPage(page,null);

        e.getRecords().forEach(System.out::println);
    }


    //本来删除，物理删除，在数据库中被删除了
    //逻辑删除：在数据库中还存在，但在查询中，查不到（本质，在查询中多一个判断）

    //物理删除
    @Test
    public void deleteTest(){
        int i = userMapper.deleteById(5L);
        System.out.println(i);
    }

    //逻辑删除
    @Test
    public void logicDeleteTest(){
        int i = userMapper.deleteById(1L);
        System.out.println(i);
    }
}
