package foundation.cmo.graphql.services;

import java.lang.reflect.Field;
import java.util.Locale;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import foundation.cmo.graphql.utils.MMultiRegitry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class MService {
	protected final MMultiRegitry<String, FluxSink<Object>> registry = new MMultiRegitry<>();
	
	@Autowired 
	private MessageSource messages;
	
	public MService() {
	}
	
	@SuppressWarnings("unchecked")
	protected <T> Publisher<T> publish(Class<T> type, Object key, T defaultValue) {
		String skey = makeId(type, key);
		return (Publisher<T>) Flux.create(
				fs -> registry.add(skey, fs.onDispose(() -> registry.remove(skey, fs)).next(defaultValue)),
				FluxSink.OverflowStrategy.BUFFER);
	}
	
	protected boolean inPublish(Class<?> type, Object key) {
		String skey = makeId(type, key);
		return registry.contains(skey);
	}

	protected void callPublish(Object key, Object value) throws Exception {
		String skey = makeId(value.getClass(), key);
		registry.get(skey).forEach(sub -> sub.next(value));
	}
	
	private String makeId(Class<?> type, Object key) {
		return String.format("%s-%s", type.getSimpleName(), key);
	}
	
	public String getString(Field code, Object... args) {
		System.out.println(code);
		return null;
	}
	public String getString(String code, Object... args) {
		return messages.getMessage(code, args, Locale.forLanguageTag("pt-BR"));
	}
}
