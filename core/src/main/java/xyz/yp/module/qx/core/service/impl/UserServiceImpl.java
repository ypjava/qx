package xyz.yp.module.qx.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yp.module.qx.core.domain.Permission;
import xyz.yp.module.qx.core.domain.Role;
import xyz.yp.module.qx.core.domain.User;
import xyz.yp.module.qx.core.mapper.PermissionMapper;
import xyz.yp.module.qx.core.mapper.RoleMapper;
import xyz.yp.module.qx.core.mapper.UserMapper;
import xyz.yp.module.qx.core.service.UserService;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User findByUserName(String userName) {
        User user = userMapper.selectByUserName(userName);
        return user;
    }

    @Override
    public Set<Role> findRolesByUserName(String userName) {
        Set<Role> roles = roleMapper.selectByUserName(userName);
        return roles;
    }

    @Override
    public Set<Permission> findPermissionsByUserName(String userName) {
        Set<Permission> permissions = permissionMapper.selectByUserName(userName);
        return permissions;
    }
}
