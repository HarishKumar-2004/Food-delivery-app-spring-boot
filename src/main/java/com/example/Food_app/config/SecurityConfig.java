package com.example.Food_app.config;

import com.example.Food_app.services.SecuredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecuredUserService securedUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securedUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/viewCustomer").hasAuthority("CUSTOMER_SELF_INFO")
                .antMatchers(HttpMethod.GET, "/viewCustomer-by-id/**").hasAuthority("CUSTOMER_INFO_BY_ADMIN")
                .antMatchers(HttpMethod.POST,"/admin/**").hasAuthority("CREATE_ADMIN_AUTHORITY")
                .antMatchers(HttpMethod.POST, "/customer/**").permitAll()

                .antMatchers(HttpMethod.POST,"/addItem").hasAuthority("ADMIN_ITEM_AUTHORITY")
                .antMatchers(HttpMethod.PUT,"/updateItem").hasAuthority("ADMIN_ITEM_AUTHORITY")
                .antMatchers(HttpMethod.DELETE,"/removeItem/**").hasAuthority("ADMIN_ITEM_AUTHORITY")
                .antMatchers(HttpMethod.GET,"/viewItem/**").permitAll()
                .antMatchers(HttpMethod.GET,"/viewAllItems").permitAll()

                .antMatchers(HttpMethod.GET,"/viewCustomerOrder/**").hasAuthority("ADMIN_ORDER_AUTHORITY")
                .antMatchers(HttpMethod.GET,"/saveOrder").hasAuthority("CUSTOMER_ORDER_AUTHORITY")
                .antMatchers(HttpMethod.PUT,"/updateOrder").hasAuthority("CUSTOMER_ORDER_AUTHORITY")
                .antMatchers(HttpMethod.POST,"/viewOrder/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/removeOrder/**").hasAuthority("CUSTOMER_ORDER_AUTHORITY")

                .antMatchers(HttpMethod.POST,"/addRestaurant").hasAuthority("ADMIN_RESTAURANT_AUTHORITY")
                .antMatchers(HttpMethod.DELETE,"/removeRestaurant/**").hasAuthority("ADMIN_RESTAURANT_AUTHORITY")
                .antMatchers(HttpMethod.POST,"/updateRestaurant").hasAuthority("ADMIN_RESTAURANT_AUTHORITY")
                .antMatchers(HttpMethod.GET,"/viewRestaurant/**").permitAll()

                .antMatchers(HttpMethod.GET,"/viewTotal/**").hasAuthority("ADMIN_BILL_AUTHORITY")
                .antMatchers(HttpMethod.GET,"/viewBill/**").permitAll()
                .and()
                .formLogin();
    }

}
