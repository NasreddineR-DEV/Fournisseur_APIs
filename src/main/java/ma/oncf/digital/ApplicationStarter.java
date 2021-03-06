package ma.oncf.digital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStarter {
	public final static Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
    }

}            