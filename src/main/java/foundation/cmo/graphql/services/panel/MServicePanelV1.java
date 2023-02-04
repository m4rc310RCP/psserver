package foundation.cmo.graphql.services.panel;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import foundation.cmo.graphql.payload.Panel;
import foundation.cmo.graphql.payload.Status;
import foundation.cmo.graphql.payload.Totem;
import foundation.cmo.graphql.services.MService;
import foundation.cmo.graphql.utils.cache.MCacheComponent;
import foundation.cmo.utils.i18n.M;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;

//@Service
//@GraphQLApi
public class MServicePanelV1 extends MService implements MPanelConst {

	private static final Logger LOG = LoggerFactory.getLogger(MServicePanelV1.class);

	@Autowired
	MCacheComponent cache;

	@Autowired
	M m;

//	@Cacheable(value = CACHE_ID$panels)
	@GraphQLQuery(name = QUERY$panel_default, description = DESC_QUERY$panel_default, deprecationReason = "No used.")
	public Panel getDefaultPanel(
			@GraphQLArgument(name = NAME$station_id, description = DESC$station_id) String stationId) {

		LOG.info("Load from static value");

		Panel panel = new Panel();
		panel.setPass("---");
		panel.setStationId(stationId);
		

		return panel;
	}

	@GraphQLSubscription(name = SUBSCRIPTION$totem_load, description = DESC_SUBSCRIPTION$totem_load)
	public Publisher<Totem> loadTotem(
			@GraphQLArgument(name = NAME$station_id, description = DESC$station_id)
			String stationId){
		
		Totem totemFromCache = cache.getTotem(stationId);
		return publish(Totem.class, stationId, totemFromCache);
	}
	
//	@Cacheable(value = CACHE_ID$totems, key = "#title")
	@GraphQLQuery(name = NAME$title, description = DESC$title)
	public String titleFrom(@GraphQLContext Totem totem) {
		return "Olá, seja bem vindo. Escolha uma opção para continuar.";
	}
	
//	@Cacheable(value = CACHE_ID$totems, key = "#unityId")
	@GraphQLQuery(name = NAME$unity_id, description = DESC$unity_id)
	public Long unityFrom(@GraphQLContext Totem totem) {
		return 39L;
	}
	
//	======================================================================
	
	@GraphQLSubscription(name = SUBSCRIPTION$listerner_panel, description = DESC_SUBSCRIPTION$listerner_panel)
	public Publisher<Panel> registerPanel(
			@GraphQLArgument(name = NAME$station_id, description = DESC$station_id) String stationId) {

		Panel fromCache = cache.getFromCache(stationId);

		return publish(Panel.class, stationId, fromCache);
	}

	@GraphQLMutation(name = MUTATION$call_pass, description = DESC_MUTATION$call_pass)
	public Status callPassV1(
			@GraphQLArgument(name = NAME$panel, description = DESC$panel) Panel panel) {
		
		try {
			
			String stationId = panel.getStationId();
			
			if (!inPublish(Panel.class, stationId)) {
				return Status.to(m.getString(MESSAGE$no_connected_panel, stationId), -99);
			}
			
			callPublish(stationId, panel);
			cache.updateCache(stationId, panel);
			
			return Status.to(m.getString(MESSAGE$call_pass_sucess), 0);
		} catch (Exception e) {
			return Status.to(e.getMessage(), -99);
		}
	}

	public Status callPass(@GraphQLArgument(name = NAME$station_id, description = DESC$station_id) String stationId,
			@GraphQLArgument(name = NAME$info_pass, description = DESC$info_pass) String pass) {

		if (!inPublish(Panel.class, stationId)) {
			return Status.to(m.getString(MESSAGE$no_connected_panel, stationId), -99);
		}

		Panel panel = new Panel();
		panel.setStationId(stationId);
		panel.setPass(pass);

		try {

			callPublish(stationId, panel);

			cache.updateCache(panel.getStationId(), panel);

			return Status.to(m.getString(MESSAGE$call_pass_sucess), 0);
		} catch (Exception e) {
			return Status.to(e.getMessage(), -99);
		}
	}

	@Scheduled(cron = "0 39 19 ? * *")
	public void scheduleCleaCache() {
		cache.resetPanelCache();
	}

}
