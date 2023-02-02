package foundation.cmo.graphql.payload;

import foundation.cmo.graphql.services.panel.MPanelConst;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class Totem implements MPanelConst {
	
	@GraphQLQuery(name = NAME$station_id, description = DESC$station_id)
	private String stationId;
	
	@GraphQLQuery(name = NAME$unity_id, description = DESC$unity_id)
	private Long unityId;
	
	@GraphQLQuery(name = NAME$title, description = DESC$title)
	private String title;
}
