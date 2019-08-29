package info.picocli.demo;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.concurrent.Callable;

@Component
@Command(name = "myCommand")
public class MyCommand implements Callable<Integer> {


//  @Autowired
//  private SomeService someService;

  // Prevent "Unknown option" error when users use
  // the Spring Boot parameter 'spring.config.location' to specify
  // an alternative location for the application.properties file.
  @Option(names = "--spring.config.location", hidden = true)
  private String springConfigLocation;

  @Option(names = { "-x", "--option" },  description = "example option")
  private boolean flag;

  @Option(names = "--verbose",           negatable = true) boolean verbose;
  @Option(names = "-XX:+PrintGCDetails", negatable = true) boolean printGCDetails;
  @Option(names = "-XX:-UseG1GC",        negatable = true) boolean useG1GC = true;

  public Integer call() throws Exception {

    System.out.println("------------------");
    // business logic here
    return 0;
  }
}
