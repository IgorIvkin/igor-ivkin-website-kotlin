package com.igorivkin.website.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.authentication.dao.DaoAuthenticationProvider

import com.igorivkin.website.security.UserDetailsServiceImpl

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import java.lang.Exception


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig: WebSecurityConfigurerAdapter() {

    private val REMEMBER_ME_KEY = "rememberToken"

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

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMINISTRATOR")
            .and()
            .authorizeRequests().antMatchers("/account/login", "/resource/**").permitAll()
            .and()
            .formLogin().loginPage("/account/login").usernameParameter("username").passwordParameter("password")
            .permitAll()
            .loginProcessingUrl("/account/doLogin")
            .successForwardUrl("/account/postLogin")
            .failureUrl("/account/loginFailed")
            .and()
            .logout().deleteCookies("JSESSIONID", "remember-me").logoutUrl("/account/doLogout")
            .logoutSuccessUrl("/account/logout").permitAll()
            .and()
            .rememberMe().key(REMEMBER_ME_KEY).userDetailsService(getUserDetailsService())
            .and()
            .csrf().disable()
    }

}