package foundation.cmo.graphql.mappers;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import foundation.cmo.graphql.annotations.MDate;
import foundation.cmo.graphql.utils.MCoercingUtils;
import graphql.schema.Coercing;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLScalarType;
import io.leangen.graphql.generator.mapping.TypeMapper;
import io.leangen.graphql.generator.mapping.TypeMappingEnvironment;

@Component
public class MDateTypeMapper implements TypeMapper {

	private Map<String, GraphQLScalarType> mapGraphQLScalarType = new HashMap<>();

	@Override
	public GraphQLOutputType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip,
			TypeMappingEnvironment env) {
		
		return getGraphQLScalarType(env);
	}

	@Override
	public GraphQLInputType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip,
			TypeMappingEnvironment env) {
		return getGraphQLScalarType(env);
	}

	@Override
	public boolean supports(AnnotatedElement element, AnnotatedType type) {
		return element.isAnnotationPresent(MDate.class);
	}

	private GraphQLScalarType getGraphQLScalarType(TypeMappingEnvironment env) {

		final String mformat = env.rootElement.getAnnotation(MDate.class).value();

		String ref = String.format("MDate_%s", hashString(mformat));

		if (mapGraphQLScalarType.containsKey(ref)) {
			return mapGraphQLScalarType.get(ref);
		}
		

		DateFormat df = new SimpleDateFormat(mformat);
		Coercing<Date, String> coercing = MCoercingUtils.get(Date.class, string -> df.parse(string), date -> df.format(date));

		GraphQLScalarType dateGraphQLScalarType = GraphQLScalarType.newScalar()
				.name(ref)
				.coercing(coercing)
				.build();

		mapGraphQLScalarType.put(ref, dateGraphQLScalarType);
		
		return mapGraphQLScalarType.get(ref);
	}

	public String hashString(String s) {
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < s.length(); i++) {
//			int a = Character.getNumericValue(s.charAt(i));
//			if (a >= 0) {
//				sb.append(a);
//			}
//		}
//		return sb.toString();

		int hash = 7;
		for (int i = 0; i < s.length(); i++) {
			hash = hash * 31 + s.charAt(i);
		}
		
		hash = Math.abs(hash);
		
		return String.format("%010d", hash);
	}

}
