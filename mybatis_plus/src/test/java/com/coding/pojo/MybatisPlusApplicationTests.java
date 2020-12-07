package com.coding.pojo;

import com.coding.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
}
