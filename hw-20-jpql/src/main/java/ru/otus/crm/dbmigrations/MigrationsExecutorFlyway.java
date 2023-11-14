package ru.otus.crm.dbmigrations;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MigrationsExecutorFlyway {
    private static final Logger log = LoggerFactory.getLogger(MigrationsExecutorFlyway.class);

    private final Flyway flyway;

    public MigrationsExecutorFlyway(String dbUrl, String dbUserName, String dbPassword) {
        flyway = Flyway.configure()
                .dataSource(dbUrl, dbUserName, dbPassword)
                .locations("classpath:/db/migration")
                .load();
    }

    public void executeMigrations() {
        log.info("db migration started...");
        flyway.migrate();
        log.info("db migration finished.");
    }
}
