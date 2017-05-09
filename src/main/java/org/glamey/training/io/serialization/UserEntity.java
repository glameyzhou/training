package org.glamey.training.io.serialization;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhouyang.zhou  2017/1/7.20.
 */
@Getter
@Setter
public class UserEntity implements java.io.Serializable {
  private Integer id;
  private String email;
  private String password;
  private String nickName;
}
