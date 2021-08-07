package com.igorivkin.website.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.authentication.dao.DaoAuthenticationProvider

import com.igorivkin.website.config.security.UserDetailsServiceImpl

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.config.annotation.web.builders.HttpSecurity


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig: WebSecurityConfigurerAdapter() {

    private val rememberMeToken = "rememberToken"

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun getUserDetailsService(): UserDetailsService? {
        return UserDetailsServiceImpl()
    }

    @Bean
    fun getAuthenticationProvider(): DaoAuthenticationProvider? {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(getUserDetailsService())
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMINISTRATOR")
            .and()
            .authorizeRequests().antMatchers("/account/login", "/resource/**").permitAll()
            .and()
            .formLogin().loginPage("/account/login").usernameParameter("username").passwordParameter("password").permitAll()
            .loginProcessingUrl("/account/doLogin")
            .successForwardUrl("/account/postLogin")
            .failureUrl("/account/loginFailed")
            .and()
            .logout().deleteCookies("JSESSIONID", "remember-me").logoutUrl("/account/doLogout")
            .logoutSuccessUrl("/account/logout").permitAll()
            .and()
            .rememberMe().key(rememberMeToken)
            .userDetailsService(getUserDetailsService())
            .and()
            .csrf().disable()
    }

}