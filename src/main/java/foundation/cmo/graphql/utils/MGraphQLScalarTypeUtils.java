package foundation.cmo.graphql.utils;

import java.util.HashMap;
import java.util.Map;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

public class MGraphQLScalarTypeUtils {

	private static final Map<String, GraphQLScalarType> maps = new HashMap<>();

	public static GraphQLScalarType get(String key, Coercing<?, ?> coercing) {
		return get(key, key, coercing);
	}
	
	public static GraphQLScalarType get(String key, String description, Coercing<?, ?> coercing) {
		if (maps.containsKey(key)) {
			return maps.get(key);
		}

		GraphQLScalarType ret = GraphQLScalarType.newScalar().name(key).description(description).coercing(coercing).build();

		maps.put(key, ret);
		return ret;
	}
}
