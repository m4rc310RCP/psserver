package foundation.cmo.configurations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import foundation.cmo.utils.i18n.M;

@Configuration
public class MMessageConfig {

//	@Bean
	M loadMessages() {
		M m = new M();
		Class<?> type = m.getClass();
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		fields.forEach(field -> {
			field.setAccessible(true);
			String name = field.getName().replaceAll("([A-Z])", ".$1").toLowerCase();
			try {				
				field.set(m, name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return m;
	}
}
