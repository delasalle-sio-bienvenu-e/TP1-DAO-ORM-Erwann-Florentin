package com.mydigitalschool.dao_orm.daojooq;

import com.mydigitalschool.dao_orm.daojooq.business.Components;
import com.mydigitalschool.dao_orm.daojooq.business.Entity1;
import com.mydigitalschool.dao_orm.daojooq.business.Entity2;
import com.mydigitalschool.dao_orm.daojooq.dao.ComponentDao;
import com.mydigitalschool.dao_orm.daojooq.dao.Entity1Dao;
import com.mydigitalschool.dao_orm.daojooq.dao.Entity2Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
// on aurait utilisé @Controller pour un contrôleur d'application web
public class AppController {
    @Autowired
    Entity1Dao entity1Dao;

    @Autowired
    Entity2Dao entity2Dao;
    
    @Autowired
    ComponentDao componentDao;

    public Entity1 saveEntity1(Entity1 entity1) throws SQLException {
        return entity1Dao.saveEntity1(entity1);
    }

    public Entity1 getEntity1(int id) throws SQLException {
        return entity1Dao.getEntity1(id);
    }
    
    public Boolean deleteEntity1(int id) throws SQLException {
        return entity1Dao.deleteEntity1(id);
    }
    

    public <T> List<Entity1> getEntities1(T property1, int nbValuesToSkip, int nbValuesToReturn) {
        // TODO à implémenter
        throw new UnsupportedOperationException();
    }
    
    public Entity2 saveEntity2(Entity2 entity2) throws SQLException {
        return entity2Dao.saveEntity2(entity2);
    }

    public Entity2 getEntity2(int id) throws SQLException {
        return entity2Dao.getEntity2(id);
    }
    
    public Boolean deleteEntity2(int id) throws SQLException {
        return entity2Dao.deleteEntity2(id);
    }
    
    public Components saveComponents(Components component) throws SQLException {
        return componentDao.saveComponents(component);
    }

    public List<Components> getComponentsFromEntity1(int id) throws SQLException {
    	List<Components> list = new ArrayList();
        list = componentDao.getComponentsFromEntity1(id);
        return list;
    }
    
    public Boolean deleteComponent(int id) throws SQLException {
        return entity2Dao.deleteEntity2(id);
    }
}
