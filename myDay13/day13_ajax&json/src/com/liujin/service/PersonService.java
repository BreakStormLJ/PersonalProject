package com.liujin.service;

import com.liujin.domain.Admin;

public interface PersonService {
    public Admin findPersonByUsername(String username);
}
