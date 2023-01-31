package foundation.cmo.graphql.services.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import foundation.cmo.graphql.payload.Panel;
import foundation.cmo.graphql.payload.Status;
import foundation.cmo.graphql.services.MService;
import foundation.cmo.graphql.utils.cache.MCacheComponent;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;

//@Service
//@GraphQLApi
public class MServicePanel extends MService implements MPanelConst {

	@Autowired
	MCacheComponent cache;
//	private M m;
	//==================================================//
	@GraphQLQuery(name = QUERY$status_server)
	public Status getStatusServer() {
		String message = getString(MESSAGE$_sucess, new Date());
		
		return Status.to(message, 0);
	}
	
	@GraphQLQuery(name = QUERY$connected_panels, description = DESC$query_connected_panels)
	public List<Panel> listPanels(@GraphQLArgument(name = ARG$unit_id, description = DESC$unity_id) Long unityId){
		List<Panel> list = new ArrayList<>();
		
		registry.getKeys(Panel.class).forEach(key->{
//			String stationId = key.replace("Panel-", "").trim();
//			list.add(cache.getPanelDefault(stationId));
		});
		
		return list;
	}
	
	@Cacheable(CACHE_ID$panels)
	@GraphQLQuery(name = NAME$unity_id, description = DESC$unity_id)
	public Long getUnitId(@GraphQLContext Panel panel) {
//		TODO: get unityId from external source.
		if (panel.getStationId() != null) {
			return 39L;
		}
		return 0L;
	}
	
	//==================================================//
	@GraphQLMutation(name = MUTATION$call_pass, description = DESC_MUTATION$call_pass)
	public Status callPass(@GraphQLArgument(name = NAME$panel, description = DESC$panel) Panel panel) {
		String message = getString(MESSAGE$_sucess, new Date());
		
		String ref = panel.getStationId();
		if (!inPublish(Panel.class, ref)) {
			message = getString(MESSAGE$no_connected_panel, ref);
			return Status.to(message, -99);
		}
		
		try {
			callPublish(ref, panel);
//			cache.updtadePanel(panel);
		} catch (Exception e) {
			return Status.to(e.getMessage(), -99);
		}
		
		return Status.to(message, 0);
	}
	
	
//	@GraphQLMutation(name = MUTATION$print_pass, description = DESC$mutation_print_pass)
//	public Status printPass() {
//		return Status.to(getString(MESSAGE$_sucess, new Date()), 0);
//	}
	//==================================================//
	
	@GraphQLSubscription(name = SUBSCRIPTION$listerner_panel)
	public Publisher<Panel> conectPanel(@GraphQLArgument(name = ARG$station_id, description = DESC$station_id) String stationId) {
//		Panel panel = cache.getPanelDefault(stationId);
//		return publish(Panel.class, stationId, panel);
		return null;
	}
	
	@GraphQLSubscription(name = SUBSCRIPTION$connected_panels)
	public List<Panel> getConnectedPanels(@GraphQLArgument(name = ARG$unit_id, description = DESC$unity_id) Long unityId){
		return null;
	}
	
	//==================================================//
	
}
