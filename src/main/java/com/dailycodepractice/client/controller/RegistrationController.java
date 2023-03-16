package com.dailycodepractice.client.controller;

import com.dailycodepractice.client.entity.User;
import com.dailycodepractice.client.event.RegistrationCompleteEvent;
import com.dailycodepractice.client.model.UserModel;
import com.dailycodepractice.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,apliactionUrl(request)
        ));
        return "Success";
    }
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")){
            return "User Verified Successfully";
        }
        return "Bad User Credential";
    }

    private String apliactionUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":" +
                request.getServerPort()+
                request.getContextPath();
    }
}
