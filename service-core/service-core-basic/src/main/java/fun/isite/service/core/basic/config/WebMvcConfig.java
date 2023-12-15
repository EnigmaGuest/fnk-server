package fun.isite.service.core.basic.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import fun.isite.service.core.basic.annotation.AnonymousApi;
import fun.isite.service.core.basic.converter.DateConverterFactory;
import fun.isite.service.core.basic.converter.IntegerToEnumConverterFactory;
import fun.isite.service.core.basic.converter.StringToEnumConverterFactory;
import fun.isite.service.core.basic.interf.ApiInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author Enigma
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private ApiInterceptors apiInterceptors;

    @Autowired
    private String clientTag;

    public final static List<String> NOT_MATCHES = CollectionUtil.newArrayList(
            "/error",
            "/static/**",
            "/wx/portal/**",
            "/doc.html",
            "/api-doc",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-resources/**",
            "/v3/**",
            "/favicon.ico"
    );

    public static void dynamicAddInterceptorNotMatches(List<String> paths) {
        if (ArrayUtil.isNotEmpty(paths)) {
            NOT_MATCHES.addAll(paths);
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (apiInterceptors != null && CollectionUtil.isNotEmpty(apiInterceptors.notMatches())) {
            CollectionUtil.addAll(NOT_MATCHES, apiInterceptors.notMatches());
        }
        registry.addInterceptor(new SaInterceptor( handler -> {
            if (handler instanceof HandlerMethod) {
                Method method = ((HandlerMethod) handler).getMethod();
                if (method.getAnnotation(AnonymousApi.class) != null || method.getDeclaringClass().getAnnotation(AnonymousApi.class) != null) {
                    // 终止
                    SaRouter.stop();
                }
                SaRouter.match("/**").notMatch(NOT_MATCHES).check(r -> StpUtil.checkLogin())
                        .check(r -> SaStrategy.me.checkMethodAnnotation.accept(method));
            } else {
                SaRouter.match("/**").notMatch(NOT_MATCHES).check(r -> StpUtil.checkLogin());
            }
        })).addPathPatterns("/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new DateConverterFactory());
        registry.addConverterFactory(new StringToEnumConverterFactory());
        registry.addConverterFactory(new IntegerToEnumConverterFactory());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html", "doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/mp/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter jsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setWriterFeatures(JSONWriter.Feature.PrettyFormat);
        fastJsonConfig.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
        jsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        jsonHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        jsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(jsonHttpMessageConverter);
        // 解决swagger加载问题
//        converters.add(new ByteArrayHttpMessageConverter());
//        converters.add(new MappingJackson2HttpMessageConverter(new Jackson2ObjectMapperBuilder() {
//            @Override
//            public void configure(ObjectMapper objectMapper) {
//                SimpleModule module = new SimpleModule();
//                if (AuthClientConst.ADMIN.equals(clientTag)) {
//                    module.addSerializer(Long.class, ToStringSerializer.instance);
//                    module.addSerializer(Long.TYPE, ToStringSerializer.instance);
//                }
//                objectMapper.registerModule(module)
//                        .setSerializationInclusion(JsonInclude.Include.ALWAYS)
//                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                        .setLocale(Locale.CHINA)
//                        .setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT+8")))
//                        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//            }
//        }.build()));
    }

}
