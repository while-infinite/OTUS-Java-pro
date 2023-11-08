package org.otus.javapro.jdbc.mapper;


import org.otus.javapro.jdbc.core.repository.DataTemplate;
import org.otus.javapro.jdbc.core.repository.DataTemplateException;
import org.otus.javapro.jdbc.core.repository.executor.DbExecutor;
import org.otus.javapro.jdbc.crm.model.Client;
import org.otus.javapro.jdbc.crm.model.Manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final Class<T> entityType;

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, Class<T> entityType) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityType = entityType;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return (Optional<T>) dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                if (rs.next()) {
                    if (entityType == Client.class)
                        return new Client(rs.getLong("id"), rs.getString("name"));
                    if (entityType == Manager.class)
                        return new Manager(rs.getLong("no"), rs.getString("label"), rs.getString("param1"));
                }
                return null;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), rs -> {
            var entityList = new ArrayList<T>();
            try {
                while (rs.next()) {
                    if (entityType == Client.class)
                        entityList.add((T) new Client(rs.getLong("id"), rs.getString("name")));
                    if (entityType == Manager.class)
                        entityList.add((T) new Manager(rs.getLong("no"), rs.getString("label"), rs.getString("param1")));
                }
                return entityList;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        }).orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    @Override
    public long insert(Connection connection, T entity) {
        try {
            if (entityType == Client.class) {
                var client = (Client) entity;
                return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(),
                        Collections.singletonList(client.getName()));
            }
            if (entityType == Manager.class) {
                var manager = (Manager) entity;
                return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(),
                        Collections.singletonList(manager.getLabel()));
            }
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
        throw new RuntimeException("Wrong type");
    }

    @Override
    public void update(Connection connection, T entity) {
        try {
            if (entityType == Client.class) {
                var client = (Client) entity;
                dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(),
                        List.of(client.getName(), client.getId()));
            }
            if (entityType == Manager.class) {
                var manager = (Manager) entity;
                dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(),
                        List.of(manager.getLabel(), manager.getNo(), manager.getParam1()));
            }
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

//    private Class<T> getParametrizedType() {
//        try {
//            Type superclass = getClass().getGenericSuperclass();
//            Type[] typeArgs = ((ParameterizedType) superclass).getActualTypeArguments();
//            return (Class<T>) typeArgs[0];
//        } catch (ClassCastException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
}
