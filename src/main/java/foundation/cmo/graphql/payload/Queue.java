package foundation.cmo.graphql.payload;

import foundation.cmo.graphql.services.panel.MPanelConst;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class Queue implements MPanelConst {
	
	@GraphQLQuery(name = NAME$queue, description = DESC$queue)
	private String queue;
}
