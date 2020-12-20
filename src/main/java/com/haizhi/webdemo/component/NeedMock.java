package com.haizhi.webdemo.component;

import com.haizhi.webdemo.controller.DemoController;
import org.springframework.stereotype.Component;

@Component
public class NeedMock {

    public String remoteReq(DemoController.Test param) {
        return "original:" + param.name();
    }
}
