package com.hanergy.reportForms.commons.utils;

import com.hanergy.reportForms.mapper.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @ClassName SpringBeanUtils
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/14 16:28
 * @Version 1.0
 **/
public class SpringBeanUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext = null;

    private static Logger logger = LogManager.getLogger(SpringBeanUtils.class);

    /***
     * 当继承了ApplicationContextAware类之后，那么程序在调用 getBean(String)的时候会自动调用该方法，不用自己操作
     */
    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanUtils.applicationContext == null) {
            SpringBeanUtils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * check 是否注入此工具类
     */
    private static void checkApplicationContext() {
        if (applicationContext == null)
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringBeanUtil");
    }

    /***
     * 根据一个bean的id获取配置文件中相应的bean
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    public static Object getBean(String beanName) throws BeansException {
        checkApplicationContext();
        return applicationContext.getBean(beanName);
    }

    /***
     * 类似于getBean(String beanName)只是在参数中提供了需要返回到的类型。
     *
     * @param beanName
     * @param requiredType
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        checkApplicationContext();
        return (T) applicationContext.getBean(beanName, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        checkApplicationContext();
        return (T) applicationContext.getBean(requiredType);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param beanName
     * @return boolean
     */
    public static boolean containsBean(String beanName) {
        checkApplicationContext();
        return applicationContext.containsBean(beanName);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param beanName
     * @return boolean
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String beanName) throws NoSuchBeanDefinitionException {
        checkApplicationContext();
        return applicationContext.isSingleton(beanName);
    }

    /**
     * @param beanName
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException
     */
    @SuppressWarnings("rawtypes")
    public static Class getType(String beanName) throws NoSuchBeanDefinitionException {
        checkApplicationContext();
        return applicationContext.getType(beanName);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param beanName
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String beanName) throws NoSuchBeanDefinitionException {
        checkApplicationContext();
        return applicationContext.getAliases(beanName);
    }

    /**
     * 向spring容器中动态注入bean实例 <br>
     *
     * @param beanName
     * @param obj
     */
    public static void setBean(String beanName, Object obj) {
        checkApplicationContext();
        ConfigurableListableBeanFactory configurableListableBeanFactory = getBeanFactory(applicationContext);
        if (configurableListableBeanFactory == null) {
            throw new IllegalStateException("获取ConfigurableListableBeanFactory error");
        }
        // 放入单例对象
        configurableListableBeanFactory.registerSingleton(beanName, obj);

        // 注册关闭需要销毁的单例对象
        if (obj instanceof DisposableBean && configurableListableBeanFactory instanceof SingletonBeanRegistry) {
            ((DefaultSingletonBeanRegistry) configurableListableBeanFactory).registerDisposableBean(beanName, (DisposableBean) obj);
        }
    }

    /**
     * 根据不同的application获取不同的ConfigurableListableBeanFactory
     *
     * @param applicationContext
     * @return
     */
    private static ConfigurableListableBeanFactory getBeanFactory(ApplicationContext applicationContext) {
        ConfigurableListableBeanFactory beanFactory = null;
        if (applicationContext instanceof AbstractRefreshableApplicationContext) {
            // suit both XmlWebApplicationContext and ClassPathXmlApplicationContext
            AbstractRefreshableApplicationContext springContext = (AbstractRefreshableApplicationContext) applicationContext;
            if (!(springContext.getBeanFactory() instanceof DefaultListableBeanFactory)) {
                logger.error("No suitable bean factory! The current factory class is {}" + springContext.getBeanFactory().getClass());
            }
            beanFactory = (DefaultListableBeanFactory) springContext.getBeanFactory();
        } else if (applicationContext instanceof GenericApplicationContext) {
            // suit GenericApplicationContext
            GenericApplicationContext springContext = (GenericApplicationContext) applicationContext;
            beanFactory = springContext.getDefaultListableBeanFactory();
        }
        return beanFactory;
    }

    public static void main(String[] args) {
        User user = SpringBeanUtils.getBean(User.class);
        user.setId(1L);
        System.out.println(user);
    }
}
