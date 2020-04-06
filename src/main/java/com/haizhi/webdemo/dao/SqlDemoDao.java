package com.haizhi.webdemo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SqlDemoDao {
    Map<String,Object> testSql();

    String paramSql(Map<String, Object> item);

    String paramMultiSql(@Param("item") Map<String, Object> item,@Param("name") String name);

    String paramSqlMap(Map<String, Object> apiName);
}
