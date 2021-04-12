package cn.crabapples.system.dao.jpa;

import cn.crabapples.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * TODO 系统用户持久层
 *
 * @author Mr.He
 * 2019/7/4 1422:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser, String> {
    Optional<SysUser> findByDelFlagAndUsername(int delFlag, String username);

    Optional<SysUser> findByDelFlagAndId(int delFlag, String id);

    Optional<SysUser> findByDelFlagAndIdAndStatus(int delFlag, String id, int status);

    List<SysUser> findByDelFlagAndIdIn(int delFlag, List<String> ids);

    List<SysUser> findByDelFlagAndName(int delFlag, String name);

    List<SysUser> findByDelFlagAndNameLike(int delFlag, String name);


    List<SysUser> findByDelFlag(int delFlag);


    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    @Query("update SysUser set delFlag = 1 where id=:name")
    @Modifying
    void delUser(@Param("name") String id);

    /**
     * 禁用用户
     *
     * @param id 用户ID
     */
    @Query("update SysUser set status = 1 where id = :id")
    @Modifying
    void unActiveUser(@Param("id") String id);

    /**
     * 激活用户
     *
     * @param id 用户ID
     */
    @Query("update SysUser set status = 0 where id = :id")
    @Modifying
    void activeUser(@Param("id") String id);

    /**
     * 根据[用户名] [密码] [状态] [删除标记] 查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @param status   状态
     * @param delFlag  删除标记
     * @return 查询到的用户
     */
    Optional<SysUser> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag);


}
