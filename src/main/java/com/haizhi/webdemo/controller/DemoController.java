package com.haizhi.webdemo.controller;

import com.haizhi.webdemo.component.NeedMock;
import com.haizhi.webdemo.entity.CommonResponse;
import com.haizhi.webdemo.entity.UserDo;
import com.haizhi.webdemo.service.DemoService;
import com.haizhi.webdemo.service.TestComponent;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @PostMapping("/testMybatis")
    @ApiOperation("测试mybatis")
    public CommonResponse testMybatis(@RequestBody UserDo user) {
        return CommonResponse.success(demoService.testMybatis(user));
    }

    @GetMapping("/delCookies")
    @ApiOperation("测试delCookies")
    public void delCookies(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        try {
//            response.sendRedirect("https://www.hao123.com/");
            response.sendRedirect("http://127.0.0.1:9090/swagger-ui.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/addCookies")
    @ApiOperation("测试addCookies")
    public void addCookies(HttpServletResponse response) {
        Cookie newCookie = new Cookie("token", "token_value" + System.currentTimeMillis());
        newCookie.setMaxAge(10000);
        newCookie.setPath("/");
        response.addCookie(newCookie);
    }

    @GetMapping("/testSql")
    @ApiOperation("测试sql")
    public CommonResponse testSql() {
        return CommonResponse.success(demoService.testSql());
    }


    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(needMock.remoteReq(Mockito.any())).thenReturn("mock inject");
    }

    @Resource
    @InjectMocks
    private TestComponent testComponent;
    @Mock
    private NeedMock needMock;


    @GetMapping("/test")
    public String test(@PathParam("name") String name) {
        return testComponent.test(Test.AAA) + "";
    }

    @GetMapping("/test2")
    public String test2() {
        Test name = Test.AAA;
        return testComponent.test(name);
    }


    public enum Test {
        AAA,
        BBB;
    }
}
