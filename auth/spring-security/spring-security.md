
# config
@PreAuthorize
@PostAuthorize
@Secured
@EnableGlobalMethodSecurity
@EnableWebSecurity
org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

# authenticate
org.springframework.security.authentication.AuthenticationManager
org.springframework.security.authentication.AuthenticationProvider
org.springframework.security.authentication.ProviderManager
org.springframework.security.core.Authentication

org.springframework.security.web.access.ExceptionTranslationFilter
org.springframework.security.web.AuthenticationEntryPoint

@AuthenticationPrincipal

# authorize
org.springframework.security.access.AccessDecisionManager
org.springframework.security.access.AccessDecisionVoter
org.springframework.security.access.ConfigAttribute




org.springframework.security.web.FilterChainProxy



org.springframework.security.core.context.SecurityContext
org.springframework.security.core.context.SecurityContextHolder




org.springframework.security.crypto.password.PasswordEncoder
org.springframework.security.crypto.password.NoOpPasswordEncoder
org.springframework.security.crypto.password.StandardPasswordEncoder
org.springframework.security.crypto.password.DelegatingPasswordEncoder
org.springframework.security.crypto.factory.PasswordEncoderFactories


HttpSecurity
HttpSecurityBuilder
WebSecurity
SecurityBuilder




doFilter:52, AuthenticationFilter (com.alashoo.apiserver.app.ext.security)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:262, AbstractRequestLoggingFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:90, HttpTraceFilter (org.springframework.boot.actuate.web.trace.servlet)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilter:320, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
invoke:127, FilterSecurityInterceptor (org.springframework.security.web.access.intercept)
doFilter:91, FilterSecurityInterceptor (org.springframework.security.web.access.intercept)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilter:119, ExceptionTranslationFilter (org.springframework.security.web.access)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilter:137, SessionManagementFilter (org.springframework.security.web.session)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilter:111, AnonymousAuthenticationFilter (org.springframework.security.web.authentication)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilter:170, SecurityContextHolderAwareRequestFilter (org.springframework.security.web.servletapi)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilter:63, RequestCacheAwareFilter (org.springframework.security.web.savedrequest)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilter:116, LogoutFilter (org.springframework.security.web.authentication.logout)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilterInternal:66, HeaderWriterFilter (org.springframework.security.web.header)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilter:105, SecurityContextPersistenceFilter (org.springframework.security.web.context)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilterInternal:56, WebAsyncManagerIntegrationFilter (org.springframework.security.web.context.request.async)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
doFilter:334, FilterChainProxy$VirtualFilterChain (org.springframework.security.web)
doFilterInternal:215, FilterChainProxy (org.springframework.security.web)
doFilter:178, FilterChainProxy (org.springframework.security.web)
invokeDelegate:357, DelegatingFilterProxy (org.springframework.web.filter)
doFilter:270, DelegatingFilterProxy (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:99, RequestContextFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:92, FormContentFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:93, HiddenHttpMethodFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilter:130, ErrorPageFilter (org.springframework.boot.web.servlet.support)
access$000:66, ErrorPageFilter (org.springframework.boot.web.servlet.support)
doFilterInternal:105, ErrorPageFilter$1 (org.springframework.boot.web.servlet.support)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
doFilter:123, ErrorPageFilter (org.springframework.boot.web.servlet.support)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
filterAndRecordMetrics:154, WebMvcMetricsFilter (org.springframework.boot.actuate.metrics.web.servlet)
filterAndRecordMetrics:122, WebMvcMetricsFilter (org.springframework.boot.actuate.metrics.web.servlet)
doFilterInternal:107, WebMvcMetricsFilter (org.springframework.boot.actuate.metrics.web.servlet)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:200, CharacterEncodingFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
invoke:202, StandardWrapperValve (org.apache.catalina.core)
invoke:96, StandardContextValve (org.apache.catalina.core)
invoke:541, AuthenticatorBase (org.apache.catalina.authenticator)
invoke:139, StandardHostValve (org.apache.catalina.core)
invoke:92, ErrorReportValve (org.apache.catalina.valves)
invoke:690, AbstractAccessLogValve (org.apache.catalina.valves)
invoke:74, StandardEngineValve (org.apache.catalina.core)
service:343, CoyoteAdapter (org.apache.catalina.connector)
service:373, Http11Processor (org.apache.coyote.http11)
process:65, AbstractProcessorLight (org.apache.coyote)
process:868, AbstractProtocol$ConnectionHandler (org.apache.coyote)
doRun:1590, NioEndpoint$SocketProcessor (org.apache.tomcat.util.net)
run:49, SocketProcessorBase (org.apache.tomcat.util.net)
runWorker:1128, ThreadPoolExecutor (java.util.concurrent)
run:628, ThreadPoolExecutor$Worker (java.util.concurrent)
run:61, TaskThread$WrappingRunnable (org.apache.tomcat.util.threads)
run:834, Thread (java.lang)