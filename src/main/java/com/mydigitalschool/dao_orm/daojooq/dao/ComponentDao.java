package com.mydigitalschool.dao_orm.daojooq.dao;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mydigitalschool.dao_orm.daojooq.business.Components;
import com.mydigitalschool.dao_orm.daojooq.business.Entity1;
import com.mydigitalschool.dao_orm.daojooq.dao.tables.ParcAttractions;
import com.mydigitalschool.dao_orm.daojooq.dao.tables.TarifParc;
import com.mydigitalschool.dao_orm.daojooq.dao.tables.records.TarifParcRecord;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Component
public class ComponentDao {
    @Autowired
    DataSource dataSource;
    
    public Components saveComponents(Components component) throws SQLException {
        TarifParc TarifParc = new TarifParc();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        TarifParcRecord componentsRecord = dbDSL.newRecord(TarifParc);
        componentsRecord.setIdParc(component.id);
        componentsRecord.setTarif(component.tarif);
   
        return component;
    }

    public List<Components> getComponentsFromEntity1(Integer id) throws SQLException {
    	TarifParc TarifParc = new TarifParc();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        Result<Record> result = dbDSL.select().from(TarifParc).where(TarifParc.ID_PARC.eq(id)).fetch();
        List<Components> list = new ArrayList();
        for (Record r : result) {
        	Components component = new Components();
        	component.id = r.getValue(TarifParc.ID);
        	component.idParc = r.getValue(TarifParc.ID_PARC);
        	component.tarif = r.getValue(TarifParc.TARIF);
        	list.add(component);
        }
		return list;
        
    }
    
    public boolean deleteComponent(Integer id) throws SQLException {
    	TarifParc TarifParc = new TarifParc();
        DSLContext dbDSL = DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
        dbDSL.delete(TarifParc).where(TarifParc.ID.eq(id)).execute();
		return true;
    }
    
}

