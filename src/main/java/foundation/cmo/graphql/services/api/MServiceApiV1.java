package foundation.cmo.graphql.services.api;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import foundation.cmo.graphql.payload.Coordinate;
import foundation.cmo.graphql.payload.Status;
import foundation.cmo.graphql.payload.Totem;
import foundation.cmo.graphql.services.MService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class MServiceApiV1 extends MService implements MConst {

//	private final String API_URL = "http://api.openweathermap.org/geo/1.0";
	private final String API_KEY = "f78e35a5edf2478e0569d6abe8a7dcdb";

	@GraphQLQuery(name = QUERY_LIST_TOTEMS, description = QUERY_LIST_TOTEMS_DESC)
	public List<Totem> listTotems(){
		List<Totem> list = new ArrayList<>();
		
		Totem t = new Totem();
		t.setTitle("Loja de Peças");
		list.add(t);
		
		t = new Totem();
		t.setTitle("Atendimento Normal");
		list.add(t);
		
		t = new Totem();
		t.setTitle("Farmácia Veterinária");
		list.add(t);
		
		return list;
	}
	
	@GraphQLMutation(name = "IMPRIMIR_SENHA")
	public Status printPass(
			@GraphQLArgument(name = "cd_estacao") String stationId,
			@GraphQLArgument(name = "nr_senha") String passNumber) {
		return Status.to("OK", 0);
	}
	
	
	public String test() throws Exception {

//		Locale loc = Locale.forLanguageTag("pt-BR");
//		System.err.println(loc.getISO3Country());

//		String[] countryCodes = Locale.getISOCountries();
//		for (String cc : countryCodes) {
//			System.out.println(cc);
//		}

		String city = UriUtils.encode("Campo Mourão", StandardCharsets.UTF_8.toString());
//		String uri = String.format("%s/direct?q=%s,%s,%s&limit=10&appid=%s", API_URL, city, "PR", "BR", API_KEY);
		String uri = String.format("http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid=%s",API_KEY);
		
		URL url = new URL(uri);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("accept", "application/json");
		
		InputStream responseStream = connection.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		List<Coordinate> list = mapper.readValue(responseStream, new TypeReference<List<Coordinate>>(){});
		
		list.forEach(coor->{
			System.out.println(coor.getName());
		});
		
		

		return uri;
	}
}
