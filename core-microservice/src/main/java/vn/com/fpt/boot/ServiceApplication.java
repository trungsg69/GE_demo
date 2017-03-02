package vn.com.fpt.boot;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import vn.com.fpt.boot.beans.components.DeploymentRegistry;
import vn.com.fpt.boot.commons.utils.LocaleUtils;



@SpringBootApplication
@ComponentScan(basePackages = "vn.com.fpt.boot.beans")
public class ServiceApplication{

	@Autowired
	private DeploymentRegistry deploymentRegistry;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(LocaleUtils.getLocale(deploymentRegistry.get("default.language")));
		return slr;
	}

	 @Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	
	
}
