package com.haizhi.webdemo.annotation.handler;

import com.haizhi.webdemo.annotation.EsRepository;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


@Slf4j
public class EsRepositoryProxy implements InvocationHandler {

    private EsRepositoryProxy() {
    }

    private Object target;

    // 这里的参数o就是要代理的对象
    public static Object getInstance(Object o) {
        EsRepositoryProxy pm = new EsRepositoryProxy();
        pm.target = o;// 赋值,设置这个代理对象
        // 通过Proxy的方法创建代理对象，第一个参数是要代理对象的ClassLoader装载器
        // 第二个参数是要代理对象实现的所有接口
        // 第三个参数是实现了InvocationHandler接口的对象
        // 此时的result就是一个代理对象，代理的是o
        Object result = Proxy.newProxyInstance(o.getClass().getClassLoader(), o
                .getClass().getInterfaces(), pm);
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //如果下边不判断方法名的话，就会拦截到被代理对象的所有方法的执行。
//		if(method.getName().equalsIgnoreCase("add")){
//			//输入日志
//			Logger.info("动态代理日志信息.");
//		}
        //2.自己定义一个annotation的类(LogAnnotation)，进行注解拦截。
        if (method.isAnnotationPresent(EsRepository.class)) {
            EsRepository esRepository = method.getAnnotation(EsRepository.class);
            boolean value = esRepository.value();
        }
        log.info("--->执行的是" + method.getName() + "方法.");
//        Object obj = method.invoke(target, args);// JVM通过这条语句执行原来的方法(反射机制)
        return null;
    }
}
