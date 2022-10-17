package nick.pack.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("nick.pack")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
	private final ApplicationContext context;
	
	@Autowired
	public SpringConfig(ApplicationContext context) {
		this.context = context;
	}
	
	
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(context);
		templateResolver.setPrefix("/WEB-INF/view/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}
	
	public void configureViewResolvers(ViewResolverRegistry registry) {
		ThymeleafViewResolver thymeleaf = new ThymeleafViewResolver();
		thymeleaf.setTemplateEngine(templateEngine());
		thymeleaf.setCharacterEncoding("UTF-8");
		registry.viewResolver(thymeleaf);
	}
	
	
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManager = new DriverManagerDataSource();
		
		driverManager.setDriverClassName("org.postgresql.Driver");
		driverManager.setUrl("jdbc:postgresql://localhost:5432/client_db");
		driverManager.setUsername("postgres");
		driverManager.setPassword("1234");
		return driverManager;
	}
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
}
