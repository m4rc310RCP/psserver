package foundation.cmo.graphql.services;

import java.util.Date;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import foundation.cmo.graphql.payload.Info;
import foundation.cmo.graphql.payload.Panel;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class ServiceTestStartup extends MService{
	
	
	@GraphQLQuery(name = "teste_painel")
	public Panel testPainel() throws Exception {
		Panel p = new Panel();
		p.setStationId("M123");
		p.setUnityId(39);
		
		Info i = new Info();
		i.setAlert(true);
		i.setCaller("Marcelo");
		i.setPass("12");
		i.setCallMoment(new Date());
		
		p.setInfo(i);
		
		callPublish(p.getStationId(), p);
		
		return p;
	}
	
	@GraphQLQuery(name = "ds_local")
	public String getLocalName(@GraphQLContext Panel panel) {
		return "Local";
	}
	
	
	@GraphQLMutation(name = "imprimir_senha")
	public int callPass() {
		return 1;
	}
	
	@GraphQLSubscription(name = "publicar_painel")
	public Publisher<Panel> publishePanel(String panelName){
		Panel p = new Panel();
		return publish(Panel.class, panelName, p);
	}
}
