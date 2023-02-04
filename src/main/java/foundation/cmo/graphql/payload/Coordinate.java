package foundation.cmo.graphql.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import foundation.cmo.graphql.services.api.MConst;
import lombok.Data;

@Data
public class Coordinate implements MConst{
	@JsonAlias("name")
	private String name;
	@JsonIgnore
	@JsonAlias("local_names")
	private String localNames;
	@JsonAlias("lat")
	private String lat;
	@JsonAlias("lon")
	private String lon;
	@JsonAlias("country")
	private String country;
	@JsonAlias("state")
	private String state;
}
