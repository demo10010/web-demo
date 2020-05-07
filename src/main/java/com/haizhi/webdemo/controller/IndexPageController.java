package com.haizhi.webdemo.controller;


import com.haizhi.webdemo.entity.UserInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//@RestController
@Controller
@RequestMapping("/")
@Slf4j
public class IndexPageController {

    @GetMapping("/doc")
    public ModelAndView index() {
        return new ModelAndView("doc.html#/home");
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录接口",
            notes = "{\"nameOrNo\": \"myName\",\"pageNo\": 1,\"pageSize\": 10}")
    public String login(HttpServletRequest request, @RequestBody @Valid UserInfo userInfo) throws Exception {
        log.info("ShiroController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        log.info("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                log.info("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                log.info("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                log.info("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> " + exception;
                log.info("else -- >" + exception);
            }
        }
        log.info(userInfo.toString());
//        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @GetMapping("/403")
    public String unauthorizedRole() {
        log.info("------没有权限-------");
        return "403";
    }

}
