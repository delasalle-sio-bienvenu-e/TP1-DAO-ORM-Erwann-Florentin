package com.mydigitalschool.dao_orm.daojooq;

import com.mydigitalschool.dao_orm.daojooq.business.manege;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;

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
        manege entity1 = new manege();
        // définir les propriétés et les composants liés
        assert Objects.isNull(entity1.id);
        manege savedEntity1 = appController.saveEntity1(entity1);

        // vérification que les clefs primaires sont bien renseignées
        assert !Objects.isNull(savedEntity1.id);
        // savedEntity1.composants.forEach(composant -> assert !Objects.isNull(composant.id));
    }

    public static void elaborationDaoEntity1Select(AppController appController) throws SQLException {
	    // TODO à implémenter
	    throw new UnsupportedOperationException();
    }
}
