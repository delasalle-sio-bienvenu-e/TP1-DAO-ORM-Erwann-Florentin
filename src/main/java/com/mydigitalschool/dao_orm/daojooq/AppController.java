package com.mydigitalschool.dao_orm.daojooq;

import com.mydigitalschool.dao_orm.daojooq.business.manege;
import com.mydigitalschool.dao_orm.daojooq.dao.ManegeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
// on aurait utilisé @Controller pour un contrôleur d'application web
public class AppController {
    @Autowired
    ManegeDao entity1Dao;

//    @Autowired
//    Entity2Dao entity2Dao;

    public manege saveEntity1(manege entity1) throws SQLException {
        return entity1Dao.saveManege(entity1);
    }

    public manege getEntity1(Long id) throws SQLException {
        return entity1Dao.getManege(id);
    }

    public <T> List<manege> getEntities1(T property1, int nbValuesToSkip, int nbValuesToReturn) {
        // TODO à implémenter
        throw new UnsupportedOperationException();
    }
}
