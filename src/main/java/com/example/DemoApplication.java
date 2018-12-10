package com.example;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class DemoApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
//    }
//}


//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.BeansException;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//
//@MapperScan("com.example.dao")
////排除DataSource自动配置类,否则会默认自动配置,不会使用我们自定义的DataSource,并且启动报错
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//public class DemoApplication implements CommandLineRunner,ApplicationContextAware{
//
//    public static void main(String[] args) {
//        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(DemoApplication.class);
//        springApplicationBuilder.profiles("dev").logStartupInfo(true).run(args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//    }
//}



import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;


@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class, MybatisAutoConfiguration.class})
@SpringBootApplication(scanBasePackages={"com.example"})
public class DemoApplication
{
    public static void main( String[] args )
    {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
        System.out.println( "启动成功" );
    }
}
