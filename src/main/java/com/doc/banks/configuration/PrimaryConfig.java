package com.doc.banks.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.collect.Lists;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.doc.banks" })
@EnableWebMvc
@PropertySources({ @PropertySource("classpath:application.properties") })
@Import({ MongoConfiguration.class })
@EnableSwagger2
public class PrimaryConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private Environment env;

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("**/*.css", "**/*.js", "**/*.map", "*.html", "/resources/**").addResourceLocations(
        "classpath:META-INF/resources/", "/resources/");
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.favorPathExtension(true).useJaf(false).ignoreAcceptHeader(true).mediaType("html", MediaType.TEXT_HTML)
        .mediaType("json", MediaType.APPLICATION_JSON).defaultContentType(MediaType.APPLICATION_JSON);
  }

  @Bean
  public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
    List<ViewResolver> resolvers = new ArrayList<>();

    InternalResourceViewResolver r1 = new InternalResourceViewResolver();
    r1.setPrefix("/WEB-INF/pages/");
    r1.setSuffix(".jsp");
    r1.setViewClass(JstlView.class);
    resolvers.add(r1);

    JsonViewResolver r2 = new JsonViewResolver();
    resolvers.add(r2);

    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    resolver.setViewResolvers(resolvers);
    resolver.setContentNegotiationManager(manager);

    return resolver;
  }

  @Bean
  public Docket docket() {
    Parameter parameterClientUserId =
        new ParameterBuilder().name("client_user_id").description("Client user identifier")
            .modelRef(new ModelRef("string")).parameterType("header").required(true).build();

    Parameter parameterAuthorization =
        new ParameterBuilder().name("Authorization").description("Authentication of the API User")
            .modelRef(new ModelRef("string")).parameterType("header").required(true).build();

    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build()
        .globalOperationParameters(Lists.newArrayList(parameterClientUserId, parameterAuthorization));
  }

  /**
   * View resolver for returning JSON in a view-based system. Always returns a {@link MappingJacksonJsonView}.
   */
  public class JsonViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
      MappingJackson2JsonView view = new MappingJackson2JsonView();
      view.setPrettyPrint(true);
      return view;
    }
  }

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasename(env.getRequiredProperty("message.source.basename"));
    source.setUseCodeAsDefaultMessage(true);
    return source;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public CommonsMultipartResolver common() {
    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    commonsMultipartResolver.setMaxUploadSize(16000000);
    return commonsMultipartResolver;
  }

}