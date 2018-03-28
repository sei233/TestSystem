package com.boot.dao;

import com.boot.bean.po.Entrance;
import com.boot.bean.po.Entrance2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Chenxiang on 2018/1/5.
 * @定义springdatajpa的数据仓库，继承的第一个接口，是最基本的接口，第一个参数是Po，第二个参数是主键的类型
 * @第二个继承的接口，用来做特殊条件查询，继承了之后就支持一些特殊的条件查询，不过你可能用不到
 * @继承了这个接口之后，在运行期，springdatajpa会自动装配实现了这个接口的bean，提供数据存取功能
 */

public interface Entr2Repository extends JpaRepository<Entrance2,Long>,JpaSpecificationExecutor<Entrance2> {
    //简单的查询，findBy(bean字段名，首字母大写)，后面可以继续跟and或者or，按照这种规则写 spring data jpa会自动实现这个方法
    public Entrance2 findByStudent(String name);
    //要想用sql语句直接写复杂查询，按照这个这么写就行了，?1是参数占位符,注意这里就没有按照规则findBy出来
    @Query(value = "SELECT * FROM entrance2 WHERE user_name=?1 AND user_id=?2",nativeQuery = true)
    public Entrance2 findUserByUserNameAndUserId(String userName, Long userId);


}
