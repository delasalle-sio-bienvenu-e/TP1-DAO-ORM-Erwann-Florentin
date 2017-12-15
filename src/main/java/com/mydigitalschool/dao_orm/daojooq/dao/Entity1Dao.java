package com.mydigitalschool.dao_orm.daojooq.dao;

import com.mydigitalschool.dao_orm.daojooq.business.Entity1;
import com.mydigitalschool.dao_orm.daojooq.dao.tables.ParcAttractions;

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
public class Entity1Dao {
    @Autowired
    DataSource dataSource;

    public Entity1 saveEntity1(Entity1 entite1) throws SQLException {
        ParcAttractions ParcAttractions = new ParcAttractions();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        // voir https://www.jooq.org/doc/3.3/manual/sql-building/sql-statements/insert-statement/
        dbDSL.insertInto(ParcAttractions, ParcAttractions.ID_SOCIETE, ParcAttractions.NOM, ParcAttractions.TAILLE).values(entite1.id_societe, entite1.nom, entite1.taille);
		return entite1;
    }

    public Entity1 getEntity1(Integer id) throws SQLException {
    	ParcAttractions ParcAttractions = new ParcAttractions();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        Result<Record> result = dbDSL.select().from(ParcAttractions).where(ParcAttractions.ID.eq(id)).fetch();
        Entity1 entite1 = new Entity1();
        for (Record r : result) {
            entite1.id = r.getValue(ParcAttractions.ID);
            entite1.id_societe = r.getValue(ParcAttractions.ID_SOCIETE);
            entite1.nom = r.getValue(ParcAttractions.NOM);
            entite1.taille = r.getValue(ParcAttractions.TAILLE);
        }
		return null;
        
    }
    
    public boolean deletedEntity1(Integer id) throws SQLException {
        ParcAttractions ParcAttractions = new ParcAttractions();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        dbDSL.delete(ParcAttractions).where(ParcAttractions.ID.eq(id)).execute();
		return true;
    }
}
