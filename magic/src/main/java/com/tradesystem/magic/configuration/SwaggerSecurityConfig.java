//package com.tradesystem.magic.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.OAuthBuilder;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.service.SecurityScheme;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
//
//import static java.util.Collections.singletonList;
//
//@Configuration
//public class SwaggerSecurityConfig {
//
//    @Bean
//    public SecurityConfiguration swaggerSecurityConfiguration() {
//        return SecurityConfigurationBuilder.builder().clientId("frontend").clientSecret("secret").build();
//    }
//
//    public static SecurityScheme oauth() {
//        return new OAuthBuilder().name("oauth")
//                .grantTypes(singletonList(new ResourceOwnerPasswordCredentialsGrant("/auth-server/oauth/token")))
//                .build();
//    }
//
//    public static SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(singletonList(securityReference())).build();
//    }
//
//    private static SecurityReference securityReference() {
//        return SecurityReference.builder()
//                .reference("oauth")
//                .scopes(new AuthorizationScope[]{new AuthorizationScope("read", "read")})
//                .build();
//    }
//}