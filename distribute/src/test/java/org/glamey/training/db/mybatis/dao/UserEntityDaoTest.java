package org.glamey.training.db.mybatis.dao;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.glamey.training.mybatis.dao.UserEntityDao;
import org.glamey.training.mybatis.domain.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhouyang.zhou. 2017.01.10.16.
 */
public class UserEntityDaoTest {

  static SqlSessionFactory sqlSessionFactory = null;
  static SqlSession sqlSession = null;
  static UserEntityDao userEntityDao;

  static {
    String resource = "mybatis/mybatis-config-idea.xml";
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader(resource);
    } catch (IOException e) {
      System.out.println(e);
    }
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
  }

  @Before
  public void openSqlSession() {
    sqlSession = sqlSessionFactory.openSession();
    userEntityDao = sqlSession.getMapper(UserEntityDao.class);
    System.out.println("=================================================");
  }

  @After
  public void closeSqlSession() {
    sqlSession.close();
  }

  @Test
  public void testInsertUser() throws Exception {
    UserEntity userEntity = new UserEntity("username_1", "1@qunar.com", "suzhoujie_1", new Timestamp(new Date().getTime()));
    userEntityDao.insertUser(userEntity);
    sqlSession.commit();
    System.out.println(userEntity.getId());
  }

  @Test
  public void testInsertUserBatch() throws Exception {
    List<UserEntity> list = Lists.newArrayList();
    for (int i = 100; i <= 110; i++) {
      UserEntity useEntity = new UserEntity("username_" + i, "email_" + i, "address_" + i, new Timestamp(new Date().getTime()));
      list.add(useEntity);
    }

    userEntityDao.insertUserBatch(list);
    sqlSession.commit();
  }

  @Test
  public void testGetUserById() throws Exception {
    UserEntity userEntity = userEntityDao.getUserById(1);
    System.out.println(userEntity);
  }

  @Test
  public void testUpdateUser() throws Exception {
    UserEntity userEntity = userEntityDao.getUserById(1);
    System.out.println(userEntity);
    userEntity.setEmail("new_email");
    userEntityDao.updateUser(userEntity);
    sqlSession.commit();
    System.out.println(userEntityDao.getUserById(1));
  }

  @Test
  public void testDeleteUserById() throws Exception {
    System.out.println(userEntityDao.deleteUserById(1));
  }

  @Test
  public void testGetUserAll() throws Exception {
    List<UserEntity> list = userEntityDao.getUserAll();
    for (UserEntity userEntity : list) {
      System.out.println(userEntity);
    }
  }

  @Test
  public void testGetAll() throws Exception {
    List<HashMap<String, Object>> list = userEntityDao.getAll();
    for (HashMap<String, Object> stringObjectHashMap : list) {
      for (Map.Entry<String, Object> entry : stringObjectHashMap.entrySet()) {
        System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
                /*id=14
                createTime=2015-04-09 09:43:03.0
                username=username_109
                address=address_109
                email=email_109*/
      }
      System.out.println("====<>=====");
      System.out.println("id=" + stringObjectHashMap.get("id"));
      System.out.println("username=" + stringObjectHashMap.get("username"));
      System.out.println("createTime=" + ((Timestamp) stringObjectHashMap.get("createTime")).toGMTString());
    }
  }

  @Test
  public void testGuavaCheckArguments() {
    String a = null;
    String b = null;
    Preconditions.checkArgument(a != null && b != null, "a b 参数为空");
    Preconditions.checkArgument(a != null, "为空");
    Preconditions.checkArgument(b != null, "为空");
  }

  @Test
  public void testMonth() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    //获取前月的第一天
    Calendar cal_1 = Calendar.getInstance();//获取当前日期
    cal_1.add(Calendar.MONTH, -1);
    cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
    String firstDay = format.format(cal_1.getTime());
    System.out.println("-----1------firstDay:" + firstDay);
    //获取前月的最后一天
    Calendar cale = Calendar.getInstance();
    cale.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
    String lastDay = format.format(cale.getTime());
    System.out.println("-----2------lastDay:" + lastDay);

    //获取当前月第一天：
    Calendar c = Calendar.getInstance();
    c.add(Calendar.MONTH, 0);
    c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
    String first = format.format(c.getTime());
    System.out.println("===============first:" + first);

    //获取当前月最后一天
    Calendar ca = Calendar.getInstance();
    ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
    String last = format.format(ca.getTime());
    System.out.println("===============last:" + last);
  }
}