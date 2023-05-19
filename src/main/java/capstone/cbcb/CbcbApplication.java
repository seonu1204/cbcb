package capstone.cbcb;

import org.springframework.boot.SpringApplication;
<<<<<<< HEAD
import org.springframework.boot.autoconfigure.SpringBootApplication;
=======
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
>>>>>>> f12f1ddbf0c21e5b5ffab39888d3314038a2467e

@SpringBootApplication
public class CbcbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbcbApplication.class, args);
	}

}
