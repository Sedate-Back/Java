import com.itheima.config.SpringConfig;
import com.itheima.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        BookDao bookDao = ctx.getBean(BookDao.class);
        bookDao.update();
        // 打印对象的类名
        System.out.println(bookDao.getClass()); // class com.sun.proxy.$Proxy19

        // 根据上面的答案可以看出，AOP是通过代理的方式获取Bean和操作
    }
}
