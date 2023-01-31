package foundation.cmo.graphql.utils.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import foundation.cmo.graphql.payload.Panel;
import foundation.cmo.graphql.services.panel.MPanelConst;
import foundation.cmo.utils.i18n.M;

@Component
public class MCacheComponent implements MPanelConst{
	private static final Logger LOG = LoggerFactory.getLogger(MCacheComponent.class);
	
	@Autowired
	private M m;
	
//	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//	@Cacheable(value = CACHE_ID$panels, key = "#stationId")
//	public Panel getPanelDefault(String stationId) {
//		Panel ret = new Panel();
//		ret.setStationId(stationId);
//		
//		ret.setInfo(new Info());
//		ret.getInfo().setPass("--");
//		ret.getInfo().setAlert(false);;
//		
//		log.info(String.format("Painel \"%s\" registrado em cache [%s].",stationId, df.format(new Date())));
//		
//		return ret;
//	}
//	
//	@CachePut(value = CACHE_ID$panels, key = "#stationId")
//	public void updtadePanel(Panel panel) {
//		log.info(String.format("%s - Update cache panel.", df.format(new Date())));
//	}
//
//	public void resetPanelsCache() {
//		log.info(String.format("%s - Limpando o cache para todos os paineis.", df.format(new Date())));
//	}
	
	@Cacheable(value = CACHE_ID$panels)
	public Panel getFromCache(String stationId) {
		Panel panel = new Panel();
		panel.setStationId(stationId);
		panel.setPass("0");
		
		return panel;
	}
	
	@Cacheable(value = CACHE_ID$panels)
	public Panel updateCache(Panel panel) {
		LOG.info("Update cache...");
		return panel;
	}
	
	@CacheEvict(value = CACHE_ID$panels, allEntries = true)
	public void resetPanelCache() {
		LOG.info("Clean cache for panels!");
	}
	
}
