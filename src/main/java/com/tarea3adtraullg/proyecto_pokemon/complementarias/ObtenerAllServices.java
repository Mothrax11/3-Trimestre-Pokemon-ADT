package com.tarea3adtraullg.proyecto_pokemon.complementarias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ObtenerAllServices {

    private static ApplicationContext context;
    private static ObtenerAllServices instance;

    public ObtenerAllServices(){

    }

    public static ObtenerAllServices getInstance(){
        if(instance == null){
            return instance = new ObtenerAllServices();
        }
        return instance;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static <T> T getService(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static Object getService(String beanName) {
        return context.getBean(beanName);
    }
}
