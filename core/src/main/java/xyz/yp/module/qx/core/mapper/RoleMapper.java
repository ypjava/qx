package xyz.yp.module.qx.core.mapper;

import org.springframework.stereotype.Repository;
import xyz.yp.module.qx.common.mapper.BaseMapper;
import xyz.yp.module.qx.core.domain.Role;

import java.util.Set;

@Repository
public interface RoleMapper extends BaseMapper<Role, Long> {

    Set<Role> selectByUserName(String userName);

}
