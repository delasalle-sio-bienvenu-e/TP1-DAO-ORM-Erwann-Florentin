package com.mydigitalschool.dao_orm.daojooq.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ComponentDao {
    @Autowired
    DataSource dataSource;
}
