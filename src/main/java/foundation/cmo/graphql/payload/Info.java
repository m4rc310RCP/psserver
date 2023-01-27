package foundation.cmo.graphql.payload;

import java.util.Date;

import foundation.cmo.graphql.annotations.MDate;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class Info {
	@GraphQLQuery(name = "nr_senha", description = "Número da senha chamada.")
	private String pass;
	@GraphQLQuery(name = "nm_apelido", description = "Apelido/Nome do funcionário que chamou a senha.")
	private String caller;
	@MDate
	@GraphQLQuery(name = "dt_chamada", description = "Momento em que a senha foi chamada. (dd/MM/yyyy HH:mm:ss)")
	private Date callMoment;
	@GraphQLQuery(name = "in_alerta", description = "Sinaliza se o alarme deve ser chamado ou não.")
	private boolean alert;
}
