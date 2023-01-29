package foundation.cmo.graphql.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import foundation.cmo.graphql.mappers.MCaseTypeMapper;
import foundation.cmo.graphql.mappers.MDateTypeMapper;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.generator.mapping.TypeMapper;

@Configuration
public class GraphQLConfig {

	@Bean
	ExtensionProvider<GeneratorConfiguration, TypeMapper> pageableInputField() {
		return (config, defaults) -> defaults.prepend(new MDateTypeMapper()).prepend(new MCaseTypeMapper());
	}

	
	
	
}
