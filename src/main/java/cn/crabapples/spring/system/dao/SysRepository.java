package cn.crabapples.spring.system.dao;

import cn.crabapples.spring.system.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO
 *
 * @author Mr.He
 * 3/2/20 12:15 AM
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name root
 */
public interface SysRepository extends JpaRepository<SysMenu,String> {
}
