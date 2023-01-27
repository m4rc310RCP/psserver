package foundation.cmo.graphql.mappers;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.util.Set;

import foundation.cmo.graphql.annotations.MCase;
import foundation.cmo.graphql.annotations.MCase.EmumTypeCase;
import foundation.cmo.graphql.utils.MCoercingUtils;
import foundation.cmo.graphql.utils.MGraphQLScalarTypeUtils;
import graphql.schema.Coercing;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLScalarType;
import io.leangen.graphql.generator.mapping.TypeMapper;
import io.leangen.graphql.generator.mapping.TypeMappingEnvironment;

public class MCaseTypeMapper implements TypeMapper {

	private GraphQLScalarType graphQLScalarType;

	@Override
	public GraphQLOutputType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip,
			TypeMappingEnvironment env) {
		return graphQLScalarType;
	}

	@Override
	public GraphQLInputType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip,
			TypeMappingEnvironment env) {
		return graphQLScalarType;
	}

	@Override
	public boolean supports(AnnotatedElement element, AnnotatedType type) {
		boolean isSupports = element.isAnnotationPresent(MCase.class);
		if (isSupports) {
			EmumTypeCase mcase = element.getAnnotation(MCase.class).value();
			
			Coercing<String, String> coercing = MCoercingUtils.get(String.class, string -> string, string -> string);
			
			if (mcase == EmumTypeCase.UPPERCASE) {
				coercing = MCoercingUtils.get(String.class, string -> string.toUpperCase(), string -> string.toUpperCase());
			}else if (mcase == EmumTypeCase.LOWERCASE) {
				coercing = MCoercingUtils.get(String.class, string -> string.toLowerCase(), string -> string.toLowerCase());
			}
			
			String key = String.format("MString_%s", mcase.name());
			
			this.graphQLScalarType = MGraphQLScalarTypeUtils.get(key, coercing);
		}
		return isSupports;
	}

}
