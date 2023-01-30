package foundation.cmo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import foundation.cmo.utils.i18n.M;
import io.leangen.graphql.metadata.messages.MessageBundle;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class App {

//	@Autowired
//	MessageSource messages;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

//	@Cacheable(value = "messages")

	@Bean()
	@Primary
	M loadMessages() {
		M m = new M();
		Class<?> type = m.getClass();
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		fields.forEach(field -> {
			if (field.getType().isAssignableFrom(String.class)) {
				field.setAccessible(true);

				String name = field.getName().replaceAll("([A-Z])", ".$1").toLowerCase();
				name = getString(name);
				try {
					field.set(m, name);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return m;
	}

	@Bean
	MessageBundle messageBundle() {
		return key -> getString(key);
	}

	public String getString(String code, Object... args) {
		try {
			return messageSource().getMessage(code, args, Locale.forLanguageTag("pt-BR"));
		} catch (Exception e) {
			return code;
		}
	}

	@Bean
	LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.forLanguageTag("pt-BR"));
		return sessionLocaleResolver;
	}

	@Bean("messageSource")
	MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages/message");
//		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
