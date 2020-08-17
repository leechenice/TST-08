import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("applications.xml");

        final Duck d1 = context.getBean("d1", Duck.class);
        System.out.println(d1);
        final Duck2 dk1 = context.getBean("dk1", Duck2.class);
        System.out.println(dk1);
        final DuckShop shop = context.getBean("shop", DuckShop.class);
        System.out.println(shop);


    }
}