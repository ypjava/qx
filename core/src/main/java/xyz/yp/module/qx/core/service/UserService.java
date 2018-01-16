package xyz.yp.module.qx.core.service;

import xyz.yp.module.qx.core.domain.Permission;
import xyz.yp.module.qx.core.domain.Role;
import xyz.yp.module.qx.core.domain.User;

import java.util.Set;

public interface UserService {

    User findByUserName(String userName);

    Set<Role> findRolesByUserName(String userName);

    Set<Permission> findPermissionsByUserName(String userName);

}
