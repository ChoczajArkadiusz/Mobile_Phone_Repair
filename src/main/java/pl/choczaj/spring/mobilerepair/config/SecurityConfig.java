package pl.choczaj.spring.mobilerepair.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT email, password, enabled FROM users WHERE email = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.email, ur.role FROM users_roles ur JOIN users u ON u.id = ur.user_id WHERE u.email = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll();

        http
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                .and()
                .authorizeRequests()

                .anyRequest().permitAll()


//                .antMatchers("/employees-form/register").anonymous()
//                .antMatchers("/login").anonymous()
//                .antMatchers("/").permitAll()
//                .antMatchers("/employees/**").hasRole("EMPLOYEE")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/");


//        http
//                .authorizeRequests()
//                    .anyRequest().authenticated()
//                .and()
//                .formLogin().and()
//                .httpBasic();
    }


}
