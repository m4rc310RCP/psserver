package foundation.cmo.graphql.services;

import java.util.Date;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Scheduled;

import foundation.cmo.graphql.payload.Info;
import foundation.cmo.graphql.payload.Panel;
import foundation.cmo.graphql.utils.cache.MCacheComponent;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;

//@Service
//@GraphQLApi
public class ServiceTestStartup extends MService{
	
	@Autowired
	private MCacheComponent mcache;
	
	
	@GraphQLQuery(name = "teste_painel")
	public Panel testPainel() throws Exception {
		Panel p = new Panel();
		p.setStationId("M123");
		p.setUnityId(39L);
		
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
	
	
	@Scheduled(cron = "0 37 11 * * ?")
	public void resetCache() {
		mcache.resetPanelsCache();
	}
	
	@GraphQLMutation
	@CachePut("panels")
	public Panel setPanel(String panelName) {
		Panel p = new Panel();
		p.setStationId(panelName);
		return p;
	}
	
	@GraphQLSubscription(name = "publicar_painel")
	public Publisher<Panel> publishePanel(String panelName){
		Panel p = getDefaultPanel(panelName);
		return publish(Panel.class, panelName, p);
	}
	
	@GraphQLQuery
	public Panel getDefaultPanel(String panelName) {
		Panel p = mcache.getPanelDefault(panelName);
		
		return p;
	}
	
}
