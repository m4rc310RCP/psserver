package foundation.cmo.graphql.utils.cache;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import foundation.cmo.graphql.payload.Panel;

@Component
public class MCacheComponent {
	private static final Logger log = LoggerFactory.getLogger(MCacheComponent.class);
	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Cacheable(value = "panels")
	public Panel getPanelDefault(String stationId) {
		Panel ret = new Panel();
		ret.setLocalName("Loja 01");
		ret.setStationId(stationId);
		
		log.info(String.format("Painel \"%s\" registrado em cache [%s].",stationId, df.format(new Date())));
		
		return ret;
	}

	@CacheEvict(value = "panels", allEntries = true)
	public void resetPanelsCache() {
		log.info(String.format("%s - Limpando o cache para todos os paineis.", df.format(new Date())));
	}
}
