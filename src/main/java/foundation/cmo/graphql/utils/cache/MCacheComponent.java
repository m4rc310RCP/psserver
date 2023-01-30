package foundation.cmo.graphql.utils.cache;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import foundation.cmo.graphql.payload.Info;
import foundation.cmo.graphql.payload.Panel;
import foundation.cmo.graphql.services.panel.MPanelConst;

@Component
public class MCacheComponent implements MPanelConst{
	private static final Logger log = LoggerFactory.getLogger(MCacheComponent.class);
	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Cacheable(value = CACHE_ID$panels, key = "#stationId")
	public Panel getPanelDefault(String stationId) {
		Panel ret = new Panel();
		ret.setStationId(stationId);
		
		ret.setInfo(new Info());
		ret.getInfo().setPass("--");
		ret.getInfo().setAlert(false);;
		
		log.info(String.format("Painel \"%s\" registrado em cache [%s].",stationId, df.format(new Date())));
		
		return ret;
	}
	
	@CachePut(value = CACHE_ID$panels, key = "#stationId")
	public void updtadePanel(Panel panel) {
		log.info(String.format("%s - Update cache panel.", df.format(new Date())));
	}

	@CacheEvict(value = CACHE_ID$panels, allEntries = true)
	public void resetPanelsCache() {
		log.info(String.format("%s - Limpando o cache para todos os paineis.", df.format(new Date())));
	}
}
