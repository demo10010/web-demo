package com.haizhi.webdemo.service.impl;

import com.google.common.collect.Maps;
import com.haizhi.webdemo.dao.SqlDemoDao;
import com.haizhi.webdemo.dao.UserDao;
import com.haizhi.webdemo.entity.User;
import com.haizhi.webdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SqlDemoDao sqlDemoDao;

    @Override
    public void testMybatis(User user) {
//        User user = User.builder().id(1).name("name1").age(20).build();
        Integer insertUser = userDao.insertUser(user);
        System.out.println(insertUser);
        user.setAge(25);
        Integer updateUser = userDao.updateUser(user);
        System.out.println(updateUser);

        User byId = userDao.findById(user.getId());
        System.out.println(byId);

        Map<String, Object> map = Maps.newHashMap();
        map.put("name", user.getName());
        User byMap = userDao.findByMap(map);
        System.out.println(byMap);

        User byBean = userDao.findByBean(user);
        System.out.println(byBean);

        Integer deleteUser = userDao.deleteUser(user.getId());
        System.out.println(deleteUser);
    }

    @Override
    public void testSql() {
        Map<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("name","123");
//        sqlDemoDao.paramSql(hashMap);

        sqlDemoDao.paramMultiSql(hashMap,"second");
    }

    private static void getSql() {
////        Configuration configuration = new Configuration();
//        String res = "sqlDemoMapper.xml";
//
//
//        try {
//            InputStream confInput = new ClassPathResource("mybatis-conf.xml").getInputStream();// TODO 路径修改
//            XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(confInput);
//            Configuration configuration = xmlConfigBuilder.parse();
//
//            Collection<MappedStatement> mappedStatements = configuration.getMappedStatement();//存在重复，原因未知
//            mappedStatements.stream().distinct().forEach(m -> {
//                String sql = m.getBoundSql(null).getSql();
//                System.out.println(sql);
//            });
////
////            InputStream inputStream = new ClassPathResource("mybatis-conf.xml").getInputStream();
//////            FileInputStream inputStream = new FileInputStream("D:\\sqlDemoMapper.xml");
////            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration, res, configuration.getSqlFragments());
////
////            xmlMapperBuilder.parse();
////            MappedStatement paramSql = configuration.getMappedStatement("testSql");//paramSql
////            String sql = paramSql.getBoundSql(null).getSql();
////            System.out.println(sql);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
