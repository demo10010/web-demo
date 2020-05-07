package com.haizhi.webdemo.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ChainDefinitionProvider {

    public static final Map<String, String> getAllRolesPermissions() {
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面

        // 对swagger 不设权限
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html#/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/auth/singleLogin", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/shiro/login", "anon");
        filterChainDefinitionMap.put("/doc", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        filterChainDefinitionMap.put("/**", "authc");
        //TODO 提供一些额外的控制
        return filterChainDefinitionMap;
    }
}
