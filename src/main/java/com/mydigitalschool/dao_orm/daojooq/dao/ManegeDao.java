package com.mydigitalschool.dao_orm.daojooq.dao;

import com.mydigitalschool.dao_orm.daojooq.business.manege;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class ManegeDao {
    @Autowired
    DataSource dataSource;

    public manege saveManege(manege manege) throws SQLException {
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        // voir https://www.jooq.org/doc/3.3/manual/sql-building/sql-statements/insert-statement/
        dbDSL.insertInto(manege, manege.nom, manege.type, manege.nombrePlaces).values("grande roue", "familliale", 100);

        throw new UnsupportedOperationException();
    }

    public manege getManege(Long id) throws SQLException {
    	DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
    	manege manege = new manege();
    	Result<Record> result = dbDSL.select().from(manege).where(manege.id = id).fetch();
        throw new UnsupportedOperationException();
    }
}
