package by.krainet.domashkevich_test_trainee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "CV-SERVICE",
                description = "CV-service-test-task", version = "0.0.1",
                contact = @Contact(
                        name = "Domaskevich Aleksei",
                        email = "adomashkevich@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
