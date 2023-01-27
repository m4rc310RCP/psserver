package foundation.cmo.graphql.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@GraphQLDirective(name = "MCase")
//@GraphQLDirectiveDefinition(wiring = DateFormatting.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
//@DirectiveLocations({Introspection.DirectiveLocation.FIELD_DEFINITION, Introspection.DirectiveLocation.ARGUMENT_DEFINITION, Introspection.DirectiveLocation.INPUT_FIELD_DEFINITION})
public @interface MDate {
	String value() default "dd/MM/yyyy HH:mm:ss";
}
