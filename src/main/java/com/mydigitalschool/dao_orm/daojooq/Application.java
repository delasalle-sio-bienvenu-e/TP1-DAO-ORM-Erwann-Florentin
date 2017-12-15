package com.mydigitalschool.dao_orm.daojooq;

import com.mydigitalschool.dao_orm.daojooq.business.Entity1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.Objects;

@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
        try {
            ApplicationContext context = SpringApplication.run(Application.class, args);
            AppController appController = context.getBean(AppController.class);

            // débuts de vos tests
            elaborationDaoEntity1Insert(appController);
        } catch (SQLException e) {
            LOGGER.error("SQL error", e);
        }
    }

    public static void elaborationDaoEntity1Insert(AppController appController) throws SQLException {

        Entity1 entity1 = new Entity1();
        // définir les propriétés et les composants liés
        assert Objects.isNull(entity1.id);
        entity1.id_societe = 1;
        entity1.nom = "ok";
        entity1.taille = 10;
  
        Entity1 savedEntity1 = appController.saveEntity1(entity1);

        // vérification que les clefs primaires sont bien renseignées
       
        // savedEntity1.composants.forEach(composant -> assert !Objects.isNull(composant.id));
    }

    public static void elaborationDaoEntity1Select(AppController appController, Integer id) throws SQLException {
    	
    	Entity1 selectedEntity1 = appController.getEntity1(id);
    }
}
