package com.liujin.service.impl;

import com.liujin.dao.PersonDao;
import com.liujin.dao.impl.PersonDaoImpl;
import com.liujin.domain.Admin;
import com.liujin.service.PersonService;

/**
 * @program: myDay13
 * @description:
 * @author: liujin
 * @create: 2019-07-29 20:10
 **/
public class PersonServiceImpl implements PersonService {
    private PersonDao personDao = new PersonDaoImpl();
    @Override
    public Admin findPersonByUsername(String username) {
        return personDao.findPersonByUsername(username);
    }
}
