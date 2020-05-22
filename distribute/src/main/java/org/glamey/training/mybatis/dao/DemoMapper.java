package org.glamey.training.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface DemoMapper {


    @Select("select * from demo")
    List<Map<String, Object>> queryAll();
}
