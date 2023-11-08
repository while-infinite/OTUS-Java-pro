package org.otus.javapro.jdbc.mapper;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData entityClassMetaData;
    private static final String CLIENT = "org.otus.javapro.jdbc.crm.model.Client";
    private static final String MANAGER = "org.otus.javapro.jdbc.crm.model.Manager";
    private static final String WRONG_CLASS = "Wrong class type";

    public EntitySQLMetaDataImpl(EntityClassMetaData entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        if (entityClassMetaData.getName().equals(CLIENT))
            return "select * from client";
        if (entityClassMetaData.getName().equals(MANAGER))
            return "select * from manager";
        throw new IllegalArgumentException(WRONG_CLASS);
    }

    @Override
    public String getSelectByIdSql() {
        if (entityClassMetaData.getName().equals(CLIENT))
            return "select id, name from client where id  = ?";
        if (entityClassMetaData.getName().equals(MANAGER))
            return "select no, label, param1 from manager where no  = ?";
        throw new IllegalArgumentException(WRONG_CLASS);
    }

    @Override
    public String getInsertSql() {
        if (entityClassMetaData.getName().equals(CLIENT))
            return "insert into client(name) values (?)";
        if (entityClassMetaData.getName().equals(MANAGER))
            return "insert into manager(label) values (?)";
        throw new IllegalArgumentException(WRONG_CLASS);
    }

    @Override
    public String getUpdateSql() {
        if (entityClassMetaData.getName().equals(CLIENT))
            return  "update client set name = ? where id = ?";
        if (entityClassMetaData.getName().equals(MANAGER))
            return  "update manager set label = ? where no = ?";
        throw new IllegalArgumentException(WRONG_CLASS);
    }
}
