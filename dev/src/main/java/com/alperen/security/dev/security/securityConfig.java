package com.alperen.security.dev.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Security Filter Chain uygulamak için gerekli
@EnableMethodSecurity //Method security sağlar
public class securityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService users(){
        UserDetails user1= User.builder() //Interfacede hazır entityler var
                .username("alp")//private olan sadece auth ile girebilir
                .password(passwordEncoder().encode("pass")) //basic auth ile giriş yapılır
                .roles("USER")
                .build();
        UserDetails admin= User.builder()
                .username("eren")//private olan sadece auth ile girebilir
                .password(passwordEncoder().encode("pass")) //basic auth ile giriş yapılır
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1,admin);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security)throws Exception{
        security
                .headers(x->x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))//Bu satır, çerçeve seçeneklerini devre dışı bırakır. Bu, çeşitli tarayıcı güvenlik politikalarının bir parçasıdır ve çerçeveleri gömülü içeriklere karşı koruma amacı taşır.
                .csrf(AbstractHttpConfigurer::disable)//CSRF saldırılarına karşı koruma sağlamak için genellikle kullanılır
                .formLogin(AbstractHttpConfigurer::disable)//Bu satır, form tabanlı kimlik doğrulama mekanizmasını devre dışı bırakır. Eğer bu satır devre dışı bırakılırsa, özel bir kimlik doğrulama mekanizması kullanmanız gerekebilir.
                .authorizeHttpRequests(x->x.requestMatchers("/public/**", "/auth/**").permitAll())// Bu satır, belirli URL desenlerine sahip istekleri herkesin erişebileceğini belirtir. Örneğin, "/public/" ve "/auth/" desenlerine sahip isteklere herkesin erişim izni vardır.
                .authorizeHttpRequests(x->x.requestMatchers("private/user/**").hasRole("USER"))//controller yerine burda hasRole ile sorgu yaptık
                .authorizeHttpRequests(x->x.requestMatchers("private/admin/**").hasRole("ADMIN"))//controller yerine burda hasRole ile sorgu yaptık
                .authorizeHttpRequests(x->x.anyRequest().authenticated())// Bu satır, yukarıdaki satırda belirtilmeyen herhangi bir isteği kimlik doğrulaması gerektiren bir istek olarak işaretler. Yani, "/public/" ve "/auth/" dışındaki tüm isteklerin yetkilendirilmiş bir kullanıcıdan gelmesi beklenir.
                .httpBasic(Customizer.withDefaults()); // This satır, HTTP Basic Authentication'ı yapılandırır. Bu, kullanıcı adı ve parola ile basit bir kimlik doğrulama yöntemidir.
        return security.build();


    }
}
