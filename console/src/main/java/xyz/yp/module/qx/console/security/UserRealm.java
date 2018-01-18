package xyz.yp.module.qx.console.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.yp.module.qx.core.domain.Permission;
import xyz.yp.module.qx.core.domain.Role;
import xyz.yp.module.qx.core.domain.User;
import xyz.yp.module.qx.core.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);
    @Autowired
    private UserService userService;

    public UserRealm() {
        logger.info("创建UserRealm");
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String userName = (String) principal.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Set<Role> roles = userService.findRolesByUserName(userName);
        Set<String> roleNames = new HashSet<String>();
        for (Role role : roles) {
            roleNames.add(role.getName());
        }
        authorizationInfo.addRoles(roleNames);

        Set<Permission> permissions = userService.findPermissionsByUserName(userName);
        Set<String> permissionCodes = new HashSet<String>();
        for (Permission permission : permissions) {
            permissionCodes.add(permission.getCode());
        }
        authorizationInfo.addStringPermissions(permissionCodes);

        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return authenticationInfo;
    }

    /*public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
}
