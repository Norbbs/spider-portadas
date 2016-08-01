package com.norbs.spider.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Clase que equivale al archivo applicationContext de la configuracion xml.
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.norbs.spider.*"})
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {

    /**
     * 
     * @return Bean que posee la unidad de persistencia con la que se va a trabajar
     * y que esta definida en la capa DAO.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("UnidadPersistencia");
        return em;
    }

    /**
     * 
     * @return Bean que gestiona las transacciones entre el EntityManagerFactory
     * y los componentes involucrados.
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new JtaTransactionManager();
    }
}
