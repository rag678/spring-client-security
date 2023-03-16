package com.dailycodepractice.client.event.listener;

import com.dailycodepractice.client.entity.User;
import com.dailycodepractice.client.event.RegistrationCompleteEvent;
import com.dailycodepractice.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create the verification token for the user with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);

        String url = event.getApplicationUrl() + "/verifyRegistration?token="+token;


        //send verificationEmail()
        log.info("Click the link to verify your link:{}",url);

        // send mail to the user
    }
}
