package foundation.cmo.graphql.payload;

import foundation.cmo.graphql.services.panel.MPanelConst;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class Panel implements MPanelConst {
	@GraphQLQuery(name = NAME$panel_local, description = DESC$panel_local)
	private String localName;
	@GraphQLQuery(name = NAME$station_id, description = DESC$station_id)
	private String stationId;
	@GraphQLQuery(name = NAME$info_pass, description = DESC$info_pass)
	private String pass;
	@GraphQLQuery(name = NAME$unity_id, description = DESC$unity_id)
	private Long unityId;
}
