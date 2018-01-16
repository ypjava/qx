package xyz.yp.module.qx.core.mapper;

import org.springframework.stereotype.Repository;
import xyz.yp.module.qx.common.mapper.BaseMapper;
import xyz.yp.module.qx.core.domain.User;

@Repository
public interface UserMapper extends BaseMapper<User, Long> {

    User selectByUserName(String userName);

}
