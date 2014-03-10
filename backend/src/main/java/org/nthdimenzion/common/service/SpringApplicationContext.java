package org.nthdimenzion.common.service;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 13/9/13
 * Time: 3:07 PM
 */
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T)CONTEXT.getBean(beanName);
    }

    public static CommandGateway getCommandGateway(){
        return CONTEXT.getBean("commandGateway", DefaultCommandGateway.class);
    }
}
