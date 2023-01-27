package foundation.cmo.graphql.payload;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class Panel {
	@GraphQLQuery(name = "ds_local", description = "Descrição do Local do Painel. Ex. Loja de Peças")
	private String localName;
	@GraphQLQuery(name = "cd_estacao", description = "Código do Dispositivo (Estação)")
	private String stationId;
	@GraphQLQuery(name = "cd_unidade", description = "Código da Unidade. Ex. 24")
	private int unityId;
	
	@GraphQLQuery(name = "info_chamada", description = "Informações sobre a chamada ao painel.")	
	private Info info;
	
}
