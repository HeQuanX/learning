package cn.crabapples.system.dao.jpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * TODO JAP接口基础方法
 *
 * @author Mr.He
 * 2021/4/12 20:04
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface BaseRepository<T> {
    default long count(int delFlag) {
        return countByDelFlag(delFlag);
    }

    long countByDelFlag(int delFlag);

    Optional<T> findByDelFlagAndId(int delFlag, String id);

    List<T> findByDelFlagAndIdIn(int delFlag, List<String> ids);

    Page<T> findByDelFlag(Pageable pageable, int delFlag);

    List<T> findByDelFlag(int delFlag);

    //    Optional<SysRoles> findByDelFlagAndIdAndStatus(int delFlag, String id, int status);


}
