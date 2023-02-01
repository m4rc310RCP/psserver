package foundation.cmo.graphql.utils.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import foundation.cmo.graphql.payload.Panel;
import foundation.cmo.graphql.services.panel.MPanelConst;

@Component
public class MCacheComponent implements MPanelConst{
	private static final Logger LOG = LoggerFactory.getLogger(MCacheComponent.class);
	
	@Autowired CacheManager cacheManager;
	
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
	
	@Cacheable(value = CACHE_ID$panels, key = "#stationId")
	public Panel getFromCache(String stationId) {
		
		LOG.info("Retorn from default value");
		
		Panel panel = new Panel();
		panel.setStationId(stationId);
		panel.setPass("-");
		panel.setLocalName("Loja de Peças");
		panel.setUserAlias(" ");
		return panel;
	}
	
	@CachePut(value = CACHE_ID$panels, key = "#stationId")
	public Panel updateCache(String stationId, Panel panel) {
		LOG.info("Update cache...");
//		Panel p = new Panel();
//		p.setLocalName(panel.getLocalName());
//		p.setStationId(stationId);
//		p.setPass(panel.getPass());
//		p.setUnityId(panel.getUnityId());
		return panel;
	}
	
	@CacheEvict(value = CACHE_ID$panels, allEntries = true)
	public void resetPanelCache() {
		LOG.info("Clean cache for panels!");
	}
	
}
