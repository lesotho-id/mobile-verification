package src.main.java.io.mosip.mobile.verifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = { "src.main.java.io.mosip.mobile.verifier.*", "${mosip.auth.adapter.impl.basepackage}"  }, exclude = { DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		CacheAutoConfiguration.class })
public class MobileVerifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileVerifierApplication.class, args);
	}

}
