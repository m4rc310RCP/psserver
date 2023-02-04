package foundation.cmo.configurations;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MMessageConfig {

//	@Bean
//	M loadMessages() {
//		M m = new M();
//		Class<?> type = m.getClass();
//		List<Field> fields = new ArrayList<>();
//		fields.addAll(Arrays.asList(type.getDeclaredFields()));
//
//		fields.forEach(field -> {
//			field.setAccessible(true);
//			String name = field.getName().replaceAll("([A-Z])", ".$1").toLowerCase();
//			try {				
//				field.set(m, name);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		
//		return m;
//	}
}
