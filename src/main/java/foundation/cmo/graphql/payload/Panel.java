package foundation.cmo.graphql.payload;

import java.io.Serializable;

import foundation.cmo.graphql.services.panel.MPanelConst;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class Panel implements MPanelConst , Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@GraphQLQuery(name = NAME$panel_local, description = DESC$panel_local)
	private String localName;
	@GraphQLQuery(name = NAME$station_id, description = DESC$station_id)
	private String stationId;
	@GraphQLQuery(name = NAME$info_pass, description = DESC$info_pass)
	private String pass;
	@GraphQLQuery(name = NAME$unity_id, description = DESC$unity_id)
	private Long unityId;
	@GraphQLQuery(name = NAME$user_alias, description = DESC$user_alias)
	private String userAlias;
}
