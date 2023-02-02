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
import foundation.cmo.graphql.payload.Totem;
import foundation.cmo.graphql.services.panel.MPanelConst;

@Component
public class MCacheComponent implements MPanelConst{
	private static final Logger LOG = LoggerFactory.getLogger(MCacheComponent.class);
	
	@Autowired CacheManager cacheManager;
	
	@Cacheable(value = CACHE_ID$panels, key = "#stationId")
	public Panel getFromCache(String stationId) {
		
		LOG.info("Retorn from default value");
		
		Panel panel = new Panel();
		panel.setStationId(stationId);
		panel.setPass("--");
		panel.setLocalName("Loja de Peças");
		panel.setUserAlias(" ");
		return panel;
	}
	
	@CachePut(value = CACHE_ID$panels, key = "#stationId")
	public Panel updateCache(String stationId, Panel panel) {
		LOG.info("Update cache...");
		return panel;
	}
	
	@CacheEvict(value = CACHE_ID$panels, allEntries = true)
	public void resetPanelCache() {
		LOG.info("Clean cache for panels!");
	}

	
	@Cacheable(value = CACHE_ID$panels, key = "#stationId")
	public Totem getTotem(String stationId) {
		Totem totem = new Totem();
		totem.setStationId(stationId);
		return totem;
	}
	
}
