package lk.ijse.pos.main;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("file:${user.dir}/resources/application.properties")
@EnableTransactionManagement
public class Hibernateconfig {

    @Autowired
    private Environment env;


            @Bean
            public LocalSessionFactoryBean localSessionFactoryBean(DataSource dms){

                LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
                lsfb.setDataSource(dms);
                lsfb.setPackagesToScan("lk.ijse.pos");
                lsfb.setHibernateProperties(hibernateProperties());
                return lsfb;
            }



    @Bean
            public DataSource dataSource(){
                DriverManagerDataSource dms = new DriverManagerDataSource();
                dms.setDriverClassName(env.getRequiredProperty("hibernate.connection.driver_class"));
                dms.setUrl(env.getRequiredProperty("hibernate.connection.url"));
                dms.setUsername(env.getRequiredProperty("hibernate.connection.username"));
                dms.setPassword(env.getRequiredProperty("hibernate.connection.password"));
                return dms;
            }

    private Properties hibernateProperties() {
                Properties pro = new Properties();
                pro.put("hibernate.dialect",env.getRequiredProperty("hibernate.dialect"));
                pro.put("hibernate.show_sql",env.getRequiredProperty("hibernate.show_sql"));
                pro.put("hibernate.hbm2ddl.auto",env.getRequiredProperty("hibernate.hbm2ddl.auto"));
                return pro;

    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory){
                return new HibernateTransactionManager(sessionFactory);
    }


}
