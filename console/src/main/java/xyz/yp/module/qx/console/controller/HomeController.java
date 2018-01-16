package xyz.yp.module.qx.console.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.yp.module.qx.core.domain.User;
import xyz.yp.module.qx.core.service.UserService;

import javax.annotation.Resource;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home/login", method = RequestMethod.GET)
    public @ResponseBody Object login(String userName, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            User user = userService.findByUserName(userName);
            return user;
        } catch (UnknownAccountException e) {
            logger.error("用户不存在异常");
        } catch (IncorrectCredentialsException e) {
            logger.error("密码错误异常");
        } catch (ExcessiveAttemptsException e) {
            logger.error("登陆过多异常");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
