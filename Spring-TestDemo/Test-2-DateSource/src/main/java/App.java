import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        ComboPooledDataSource dataSource2 = (ComboPooledDataSource) ctx.getBean("dataSource2");
        System.out.println(dataSource);
        System.out.println(dataSource2);


    }
}
