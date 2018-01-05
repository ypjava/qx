package xyz.yp.module.qx.core.mapper;

import org.springframework.stereotype.Repository;
import xyz.yp.module.qx.common.mapper.BaseMapper;
import xyz.yp.module.qx.core.domain.Permission;

@Repository
public interface PermissionMapper extends BaseMapper<Permission, Long> {
}
