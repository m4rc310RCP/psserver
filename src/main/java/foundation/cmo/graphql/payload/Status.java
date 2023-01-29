package foundation.cmo.graphql.payload;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "to")
public class Status {
	@GraphQLQuery(name = "ds_message", description = "Mensagem de status. Retorna a um erro ou informe de sucesso.")
	private String message;
	
	@GraphQLQuery(name = "nr_status", description = "Retorna um nomero de status.")
	private int status;
}
