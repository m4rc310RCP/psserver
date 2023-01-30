package foundation.cmo.graphql.payload;

import foundation.cmo.graphql.services.panel.MPanelConst;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "to")
public class Status implements MPanelConst{
	@GraphQLQuery(name = NAME$message, description = DESC$message)
	private String message;
	
	@GraphQLQuery(name = NAME$status, description = DESC$status)
	private int status;
}
