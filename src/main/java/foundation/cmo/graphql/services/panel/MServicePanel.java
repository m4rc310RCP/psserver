package foundation.cmo.graphql.services.panel;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import foundation.cmo.graphql.payload.Panel;
import foundation.cmo.graphql.payload.Status;
import foundation.cmo.graphql.services.MService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import reactor.core.publisher.FluxSink;

@Service
@GraphQLApi
public class MServicePanel extends MService implements MPanelConst {

//	@Autowired
//	private M m;
	//==================================================//
	@GraphQLQuery(name = QUERY$status_server)
	public Status getStatusServer() {
		String message = getString(MESSAGE$_sucess, new Date());
		
		return Status.to(message, 0);
	}
	
	@GraphQLQuery(name = QUERY$connected_panels)
	public List<Panel> listPanels(@GraphQLArgument(name = ARG$unit_id) Long unityId){
		
		Set<FluxSink<Object>> set = registry.get(Panel.class);
		set.forEach(sub -> {
			System.out.println(sub);
		});
		
		return null;
	}
	//==================================================//
	@GraphQLMutation(name = MUTATION$print_pass, description = "${mutation.dish.desc}")
	public Status printPass() {
		
		return Status.to(getString(MESSAGE$_sucess, new Date()), 0);
	}
	//==================================================//
	
	@GraphQLSubscription(name = SUBSCRIPTION$listerner_panel)
	public Publisher<Panel> conectPanel(@GraphQLArgument(name = PANEL) Panel panel) {
		return publish(Panel.class, panel.getStationId(), panel);
	}
	
	@GraphQLSubscription(name = SUBSCRIPTION$connected_panels)
	public List<Panel> getConnectedPanels(@GraphQLArgument(name = ARG$unit_id) Long unityId){
		
		
		
		
		return null;
	}
}
