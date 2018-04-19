package win.bojack.bigdata.spring_test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import win.bojack.bigdata.spring_test.service.WelcomeService;

public class App extends WelcomeService {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        WelcomeService ws = (WelcomeService)ac.getBean("ws");
        ws.sayHello();
    }
}
