package foundation.cmo.graphql.services;

import org.reactivestreams.Publisher;

import foundation.cmo.graphql.utils.MMultiRegitry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class MService {
	protected final MMultiRegitry<String, FluxSink<Object>> registry = new MMultiRegitry<>();
	
	@SuppressWarnings("unchecked")
	protected <T> Publisher<T> publish(Class<T> type, Object key, T defaultValue) {
		String skey = String.format("%s", key);
		return (Publisher<T>) Flux.create(
				fs -> registry.add(skey, fs.onDispose(() -> registry.remove(skey, fs)).next(defaultValue)),
				FluxSink.OverflowStrategy.BUFFER);
	}
	
	protected boolean inPublish(Class<?> type, Object key) {
		return registry.contains(makeId(type, key));
	}

	protected void callPublish(Object key, Object value) throws Exception {
		String skey = String.format("%s", key);
		registry.get(skey).forEach(sub -> sub.next(value));
	}

	private String makeId(Class<?> type, Object key) {
		return String.format("%s-%s", type.getName(), key);
	}
}
