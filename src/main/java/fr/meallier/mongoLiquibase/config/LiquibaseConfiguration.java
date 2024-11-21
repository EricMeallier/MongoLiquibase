package fr.meallier.mongoLiquibase.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.io.File;

@Configuration
public class LiquibaseConfiguration implements InitializingBean {

    @Value("${spring.data.mongodb.uri}")
        private String uri;

        @Value("${spring.liquibase.changeLog}")
        private Resource[] resources;

        @Override
        public void afterPropertiesSet() throws Exception {
            Database openDatabase = DatabaseFactory.getInstance().openDatabase(uri, null, null, null, null, null);

            for (Resource resource : resources) {
                String filename = resource.getFilename();
                String parentFolder = resource.getFile().getParentFile().getName();
                try (Liquibase liquibase = new Liquibase(String.format("%s%s%s", parentFolder, File.separator, filename)
                        , new ClassLoaderResourceAccessor(), openDatabase)) {
                    liquibase.update(new Contexts());
                }
            }
        }
    }

