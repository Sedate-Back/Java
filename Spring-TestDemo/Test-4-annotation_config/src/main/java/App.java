import com.itheima.config.SpringConfig;
import com.itheima.dao.BookDao;
import com.itheima.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = (BookDao) applicationContext.getBean("bookDao");
        System.out.println(bookDao);
        // 按类型获取Bean
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);
        bookService.save();
    }
}
