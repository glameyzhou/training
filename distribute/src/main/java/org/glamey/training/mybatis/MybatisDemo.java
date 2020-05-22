package org.glamey.training.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo {

    public static void main(String[] args) throws IOException {
        String resource = "";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<Object> userList = sqlSession.selectList("getUserByUserId", 2);
            if (!CollectionUtils.isEmpty(userList)) {
            }
        }

    }
}
