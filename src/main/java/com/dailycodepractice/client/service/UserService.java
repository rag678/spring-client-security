package com.dailycodepractice.client.service;

import com.dailycodepractice.client.entity.User;
import com.dailycodepractice.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);
}
