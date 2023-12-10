package ru.otus.appcontainer;

import org.reflections.Reflections;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.IntStream;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?>... initialConfigClass) {
        processConfig(initialConfigClass);
    }

    public AppComponentsContainerImpl(String path) {
        processConfigPath(path);
    }

    private void processConfigPath(String path) {
        Reflections reflections = new Reflections(path);
        Set<Class<?>> classes =  reflections.getSubTypesOf(Object.class);
        Class<?>[] classArr = classes.toArray(new Class<?>[0]);
        processConfig(classArr);
    }

    private void processConfig(Class<?>... configClasses) {
        Arrays.stream(configClasses).forEach(configClass -> {
            checkConfigClass(configClass);
            Object instance = this.getInstance(configClass);
            var componentsWithOrder = getComponentsWithOrder(configClass);
            putIntoAppContainers(componentsWithOrder, instance);
        });
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponents.stream()
                .filter(componentClass::isInstance)
                .findFirst()
                .orElse(null);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }

    private Object getInstance(Class<?> configClass) {
        try {
            Constructor<?> constructor = configClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, List<Method>> getComponentsWithOrder(Class<?> configClass) {
        Method[] methods = configClass.getMethods();
        var appComponentOrder = new HashMap<Integer, List<Method>>();
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .forEach(method -> {
                    var annotation = method.getAnnotation(AppComponent.class);
                    var key = annotation.order();
                    if (appComponentOrder.containsKey(key)) {
                        var value = appComponentOrder.get(key);
                        value.add(method);
                    } else {
                        List<Method> methodList = new ArrayList<>();
                        methodList.add(method);
                        appComponentOrder.put(annotation.order(), methodList);
                    }
                });
        return appComponentOrder;
    }

    private void putIntoAppContainers(Map<Integer, List<Method>> componentsWithOrder, Object instance) {
        IntStream.range(0, componentsWithOrder.size())
                .forEach(i ->
                        componentsWithOrder.get(i).forEach(method -> {
                            Class<?>[] types = method.getParameterTypes();
                            Object[] objects = Arrays.stream(types)
                                    .map(type -> appComponents.stream()
                                            .filter(type::isInstance)
                                            .findFirst()
                                            .orElse(null))
                                    .toArray();
                            try {
                                var object = method.invoke(instance, objects);
                                verifyComponentIsNotAlreadyExist(object)
                                ;
                                appComponents.add(object);
                                var annotation = method.getAnnotation(AppComponent.class);
                                verifyComponentByNameIsNotAlreadyExist(annotation.name());
                                appComponentsByName.put(annotation.name(), object);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        })
                );
    }

    private void verifyComponentIsNotAlreadyExist(Object object) {
        if (appComponents.contains(object))
            throw new RuntimeException("Component (" + object.getClass() + ") already exist");
    }

    private void verifyComponentByNameIsNotAlreadyExist(String name) {
        if (appComponentsByName.containsKey(name))
            throw new RuntimeException("Component with name (" + name + ") already exist");
    }

}
