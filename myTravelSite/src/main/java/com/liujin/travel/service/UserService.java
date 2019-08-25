package com.liujin.travel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.liujin.travel.domain.User;

public interface UserService {
    public String register(User user);

    public User login(User user);

    public String getCategoryJson() throws JsonProcessingException;

    public boolean active(String code);
}
