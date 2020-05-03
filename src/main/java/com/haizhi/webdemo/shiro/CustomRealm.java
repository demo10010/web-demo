package com.haizhi.webdemo.shiro;

import com.haizhi.webdemo.dao.UserDao;
import com.haizhi.webdemo.entity.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        UserDo user = userDao.getUserByName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }

    public CustomRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名去数据库查询用户信息
        UserDo user = userDao.getUserByName(name);
//        SysUser user = loginService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(user.getName());
        return simpleAuthorizationInfo;
    }

//    @Autowired
//    private LoginService loginService;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
//        if (authenticationToken.getPrincipal() == null) {
//            return null;
//        }
//        //获取用户信息
//        String name = authenticationToken.getPrincipal().toString();
//        User user = loginService.getUserByName(name);
//        if (user == null) {
//            //这里返回后会报出对应异常
//            return null;
//        } else {
//            //这里验证authenticationToken和simpleAuthenticationInfo的信息
//            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
//            return simpleAuthenticationInfo;
//        }
//    }

//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        // 权限检验-当前用户选择了角色登录
//        if (!StringUtils.isEmpty(this.getCurrentRoleNo())) {
//            log.info("权限检验-当前用户选择了角色登录");
//            List<String> permissions = new ArrayList<>();
//            permissions.add("role:check");
//            authorizationInfo.addStringPermissions(permissions);
//        }
//        log.info("加载校验用户是否选择角色");
//        return authorizationInfo;
//    }
//
//    /**
//     * 选择角色重新加载权限
//     *
//     */
//    public void loadPermission(RoleDo roleDo) {
//        Subject subject = SecurityUtils.getSubject();
//        UserRoleVo user = (UserRoleVo) subject.getPrincipal();
//        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
//        // 修改属性
//        RoleVo roleVo = new RoleVo();
//        roleVo.setRoleNo(roleDo.getRoleNo());
//        roleVo.setRoleType(roleDo.getRoleType());//角色类型(1:客户经理,2:管理岗)
//        user.setRoleVo(roleVo);
//        // 第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户
//        SimplePrincipalCollection principals = new SimplePrincipalCollection(user, realmName);
//        subject.runAs(principals);
//        clearCachedAuthorizationInfo(principals);
//    }
//
//    // 获取当前登录用户选择的角色编号
//    public String getCurrentRoleNo() {
//        Subject subject = SecurityUtils.getSubject();
//        UserRoleVo user = (UserRoleVo) subject.getPrincipal();
//        if (user == null) {
//            return null;
//        }
//        if (user.getRoleVo() != null) {
//            return user.getRoleVo().getRoleNo();
//        }
//        return null;
//    }
//
//    /**
//     * *获取当前登录用户选择的角色类型
//     * *角色类型(1:客户经理,2:管理岗)
//     * @return
//     */
//    public Integer getCurrentRoleType() {
//        Subject subject = SecurityUtils.getSubject();
//        UserRoleVo user = (UserRoleVo) subject.getPrincipal();
//        if (user == null) {
//            return 0;
//        }
//        if (user.getRoleVo() != null) {
//            return user.getRoleVo().getRoleType();
//        }
//        return 0;
//    }
//
//    /**
//     **  获取认证信息       
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        try {
//            log.info("登录获取认证信息");
//            UsernamePasswordToken credential = (UsernamePasswordToken) token;
//            String userNo = credential.getUsername();// 登录用户名
//            UserDo user = userDao.findOneByUserNo(userNo);
//            log.info("user="+user);
//            if (user == null) {
//                throw new AccountException("未查询到登录用户信息,userNo="+userNo);
//            }
////            UserRoleVo userRoleDto = new UserRoleVo(user);
//            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.get(),
//                    user.getUserNo());
//            return authenticationInfo;
//        } catch (Exception e) {
//            log.info("获取认证信息发生异常:",e);
//            throw new AccountException();
//        }
//
//    }
}
