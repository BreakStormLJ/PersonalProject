package com.liujin.dao;

import com.liujin.domain.Admin;

public interface PersonDao {
    public Admin findPersonByUsername(String username);
}
