package org.glamey.training.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import org.glamey.training.mybatis.domain.UserEntity;

/**
 * Created by zhouyang.zhou.
 */
public interface UserEntityDao {

  UserEntity getUserById(long id);

  long insertUser(UserEntity userEntity);

  void insertUserBatch(List<UserEntity> list);

  long updateUser(UserEntity userEntity);

  long deleteUserById(long id);

  List<UserEntity> getUserAll();

  List<HashMap<String, Object>> getAll();
}
