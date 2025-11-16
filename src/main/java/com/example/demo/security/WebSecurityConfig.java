package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
               .requestMatchers("/login", "/resources/**","/css/**").permitAll() //全ユーザがアクセスできるページ
                .anyRequest().authenticated()  // 上記以外のURLはログインが必要（ロールを問わない）
            )
            .formLogin((form) -> form
                .loginPage("/login")              // ログインページのURL
                .loginProcessingUrl("/login")     // ログインフォームの送信先URL
                .defaultSuccessUrl("/adminuser?loggedIn")  // ログイン成功時のリダイレクト先URL
                .failureUrl("/login?error")       // ログイン失敗時のリダイレクト先URL
                .permitAll()
            )
            .logout((logout) -> logout
            	.logoutUrl("/logout") // ログアウトURL
                .logoutSuccessUrl("/logout?loggedOut") // ログアウト成功後のリダイレクト先
                .deleteCookies("JSESSIONID") // セッションクッキーの削除
                .invalidateHttpSession(true) // セッションの無効化
                .addLogoutHandler(new SecurityContextLogoutHandler())  // セキュリティコンテキストをクリア
                .permitAll()
                
            );
       
        return http.build();
    }
    

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

}
