package org.glamey.training.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.Data;

public class UserServiceCommand extends HystrixCommand<String> {


    private UserService userService;


    protected UserServiceCommand(HystrixCommandGroupKey group) {
        super(group);
        this.userService = new UserService();
    }

    @Override
    protected String run() throws Exception {
        return userService.getUser("").getName();
    }


    class UserService {
        public User getUser(String code) {
            User user = new User();
            user.setCode(code);
            user.setName(String.format("name_%s", code));
            user.setAddress(String.format("address_%s", code));
            return user;
        }
    }


    @Data
    class User {
        private String code;
        private String name;
        private String address;
    }

}
