package cn.crabapples.spring.test.dao;

import cn.crabapples.spring.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface UserRepositoryTest extends JpaRepository<SysUser, String> {
    /**
     * 测试方法-使用hql查询
     * @param name @Param里的name
     * @return 查询到的结果集
     */
    @Query("from SysUser user where user.name=:name")
    List<SysUser> findByHQL(@Param("name") String name);

    /**
     * 测试方法-使用sql查询
     * @param name  ?1表示第一个参数
     * @return 查询到的结果集
     */
    @Query(value = "select * from sys_user where name = ?1", nativeQuery = true)
    List<SysUser> findBySQL(String name);
}
