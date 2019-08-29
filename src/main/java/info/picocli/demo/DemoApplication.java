package info.picocli.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner, ExitCodeGenerator {

  private IFactory factory;    // auto-configured to inject PicocliSpringFactory
  private MyCommand myCommand; // your @picocli.CommandLine.Command-annotated class
  private int exitCode;

  // constructor injection
  DemoApplication(IFactory factory, MyCommand myCommand) {
    this.factory = factory;
    this.myCommand = myCommand;
  }

  @Override
  public void run(String... args) {
    // let picocli parse command line args and run the business logic
    exitCode = new CommandLine(myCommand, factory).execute(args);
  }

  @Override
  public int getExitCode() {
    return exitCode;
  }

  public static void main(String[] args) {
    // let Spring instantiate and inject dependencies
    System.exit(SpringApplication.exit(SpringApplication.run(DemoApplication.class, args)));
  }
}
