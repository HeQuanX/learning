package cn.crabapples.spring.system.dao;

import cn.crabapples.spring.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * TODO JPA中SQL和HQL演示
 * 
 * @author Mr.He
 * 2019/7/4 1422:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * 测试方法-使用hql查询
     * @param name @Param里的name
     * @return 查询到的结果集
     */
    @Query("from User user where user.name=:name")
    List<User> findByHQL(@Param("name") String name);

    /**
     * 测试方法-使用sql查询
     * @param name  ?1表示第一个参数
     * @return 查询到的结果集
     */
    @Query(value = "select * from user where name = ?1", nativeQuery = true)
    List<User> findBySQL(String name);

    /**
     * 根据名字查询
     */
    List<User> findByName(String name);

    /**
     * 根据名字和年龄查询
     */
    List<User> findByNameAndAge(String name, Integer age);

    /**
     * 根据名字模糊查询
     */
    List<User> findByNameLike(String name);

    /**
     * 删除用户
     */
    @Query("update User set delFlag = 1 where id=:name")
    @Modifying
    void delUser(@Param("name")String id);

    /**
     * 禁用用户
     */
    @Query("update User set status = 1 where id = :id")
    @Modifying
    void unActiveUser(@Param("id")String id);

    /**
     * 激活用户
     */
    @Query("update User set status = 0 where id = :id")
    @Modifying
    void activeUser(@Param("id") String id);

    /**
     * 根据用户名、密码、用户状态、删除标记查询
     */
    Optional<User> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag);
}
