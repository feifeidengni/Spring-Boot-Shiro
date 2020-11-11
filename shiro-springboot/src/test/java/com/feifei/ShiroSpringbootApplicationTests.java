package com.feifei;

import com.feifei.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        System.out.println(this.userService.findByUserCode("feifei"));
    }

}
