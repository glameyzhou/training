package org.glamey.training.mybatis.domain;

import java.sql.Timestamp;

/**
 * Created by zhouyang.zhou.
 */
public class UserEntity implements java.io.Serializable {

  private long id;
  private String username;
  private String email;
  private String address;
  private Timestamp createTime;

  public UserEntity() {
  }

  public UserEntity(String username, String email, String address, Timestamp createTime) {
    this.username = username;
    this.email = email;
    this.address = address;
    this.createTime = createTime;
  }

  public UserEntity(long id, String username, String email, String address, Timestamp createTime) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.address = address;
    this.createTime = createTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", createTime=" + createTime +
            '}';
  }
}
