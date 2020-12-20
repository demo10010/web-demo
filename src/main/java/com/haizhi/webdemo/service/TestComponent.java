package com.haizhi.webdemo.service;

import com.haizhi.webdemo.component.NeedMock;
import com.haizhi.webdemo.controller.DemoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
    @Autowired
    private NeedMock needMock;

    public String test(DemoController.Test test) {
        return "test component" + needMock.remoteReq(test);
    }
}
