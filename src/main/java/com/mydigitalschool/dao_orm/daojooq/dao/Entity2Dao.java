package com.mydigitalschool.dao_orm.daojooq.dao;

import com.mydigitalschool.dao_orm.daojooq.business.Entity2;
import com.mydigitalschool.dao_orm.daojooq.dao.tables.ParcAttractions;
import com.mydigitalschool.dao_orm.daojooq.dao.tables.Societe;

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
public class Entity2Dao {
    @Autowired
    DataSource dataSource;

    public Entity2 saveEntity2(Entity2 entite2) throws SQLException {
        Societe societe = new Societe();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        // voir https://www.jooq.org/doc/3.3/manual/sql-building/sql-statements/insert-statement/
        dbDSL.insertInto(societe, societe.ID, societe.NOM, societe.ADRESSE).values(entite2.id, entite2.nom, entite2.adresse);
		return entite2;
    }

    public Entity2 getEntity2(Integer id) throws SQLException {
    	Societe societe = new Societe();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        Result<Record> result = dbDSL.select().from(societe).where(societe.ID.eq(id)).fetch();
        Entity2 entite2 = new Entity2();
        for (Record r : result) {
            entite2.id = r.getValue(societe.ID);
            entite2.nom = r.getValue(societe.NOM);
            entite2.adresse = r.getValue(societe.ADRESSE);
        }
		return entite2;
        
    }
    
    public boolean deleteEntity2(Integer id) throws SQLException {
        Societe Societe = new Societe();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        dbDSL.delete(Societe).where(Societe.ID.eq(id)).execute();
		return true;
    }
}
