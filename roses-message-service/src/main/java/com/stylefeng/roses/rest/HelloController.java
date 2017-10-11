package com.stylefeng.roses.rest;

import com.stylefeng.roses.facade.hello.HelloService;
import com.stylefeng.roses.facade.hello.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * 测试的控制器
 *
 * @author fengshuonan
 * @date 2017-06-18 23:29
 */
@RestController
public class HelloController implements HelloService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String index() {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        System.out.println(serviceInstance.getHost());
        System.out.println(serviceInstance.getServiceId());
        return "Hello World!";
    }

    @Override
    public String hello4(@RequestParam String name) {
        return "Hello " + name;
    }

    @Override
    public User hello5(@RequestHeader String name,@RequestHeader Integer age) {
        return new User(name, age);
    }

    @Override
    public String hello6(@RequestBody User user) {
        return user.toString();
    }
}