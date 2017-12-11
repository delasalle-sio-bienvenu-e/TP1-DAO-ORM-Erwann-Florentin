package com.mydigitalschool.dao_orm.daojooq.dao;

import com.mydigitalschool.dao_orm.daojooq.business.manege;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class Entity1Dao {
    @Autowired
    DataSource dataSource;

    public manege saveEntity1(manege entity1) throws SQLException {
        // TODO à implémenter
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        // voir https://www.jooq.org/doc/3.3/manual/sql-building/sql-statements/insert-statement/
        // dbDSL.insertInto(Entites1, ...).values(...)...
        throw new UnsupportedOperationException();
    }

    public manege getEntity1(Long id) {
        // TODO à implémenter
        throw new UnsupportedOperationException();
    }
}
