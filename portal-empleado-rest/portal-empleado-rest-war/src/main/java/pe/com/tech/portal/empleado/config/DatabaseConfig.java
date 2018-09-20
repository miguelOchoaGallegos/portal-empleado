package pe.com.tech.portal.empleado.config;

import static pe.com.tech.portal.empleado.constants.Perfiles.TEST;

import java.beans.PropertyVetoException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Profile({TEST})
public class DatabaseConfig {
    public static final String SQL_SESSION_FACTORY_NAME = "sessionFactory";
    public static final String TX_MANAGER = "txManager";

    @Autowired
    private
    ConfigurationProperties confprops;

    @Bean(name = "datasource")
    DataSource dataSource() throws PropertyVetoException, NamingException {
        JndiTemplate jndi = new JndiTemplate();
        return (DataSource) jndi.lookup(confprops.getStringConneccion());
    }

    @Bean(name = TX_MANAGER)
    public PlatformTransactionManager txManager() throws PropertyVetoException, NamingException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = DatabaseConfig.SQL_SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
}
