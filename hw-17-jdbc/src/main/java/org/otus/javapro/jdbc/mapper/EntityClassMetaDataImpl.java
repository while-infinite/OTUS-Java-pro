package org.otus.javapro.jdbc.mapper;

import org.otus.javapro.jdbc.annotation.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> entityType;

    public EntityClassMetaDataImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public String getName() {
        return entityType.getName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return entityType.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Field getIdField() {
        Field[] fields = entityType.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        Field[] declaredFields = entityType.getDeclaredFields();
        return new ArrayList<>(Arrays.asList(declaredFields));
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        List<Field> fieldsWithoutId = new ArrayList<>();
        Field[] declaredFields = entityType.getDeclaredFields();
        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(Id.class)) {
                fieldsWithoutId.add(field);
            }
        }
        return fieldsWithoutId;
    }
}
