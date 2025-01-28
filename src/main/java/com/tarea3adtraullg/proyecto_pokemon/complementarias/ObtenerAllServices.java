package com.tarea3adtraullg.proyecto_pokemon.complementarias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ObtenerAllServices {

    private ApplicationContext context;

    @Autowired
    public ObtenerAllServices(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    public <T> T getService(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public Object getService(String beanName) {
        return context.getBean(beanName);
    }
}
