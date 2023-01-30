package foundation.cmo.graphql.payload;

import java.util.Date;

import foundation.cmo.graphql.annotations.MDate;
import foundation.cmo.graphql.services.panel.MPanelConst;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class Info implements MPanelConst {
	@GraphQLQuery(name = NAME$info_pass, description = DESC$info_pass)
	private String pass;
	
	@GraphQLQuery(name = NAME$info_caller, description = DESC$info_caller)
	private String caller;
	
	@MDate
	@GraphQLQuery(name = NAME$info_call_moment, description = DESC$info_call_moment)
	private Date callMoment;
	
	@GraphQLQuery(name = NAME$info_alert, description = DESC$info_alert)
	private boolean alert;
}
