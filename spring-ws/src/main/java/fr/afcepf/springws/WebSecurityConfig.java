package fr.afcepf.springws;

	import fr.afcepf.springws.util.JwtAuthenticationFilter;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
	import org.springframework.core.annotation.Order;
	import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


	@Configuration
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;

	    @Autowired
	    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
	    	String pwd1Crypted =  passwordEncoder.encode("pwd1");
			System.out.println("pwd1Crypted via bcrypt:"+pwd1Crypted);
		    auth.inMemoryAuthentication()
		  .withUser("user1").password(pwd1Crypted).roles("USER").and()
		  .withUser("admin1").password(passwordEncoder.encode("pwd1")).roles("ADMIN").and()
		  .withUser("user2").password(passwordEncoder.encode("pwd2")).roles("USER").and()
		  .withUser("admin2").password(passwordEncoder.encode("pwd2")).roles("ADMIN");
	    }

	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Override
	    protected void configure(final HttpSecurity http) throws Exception {
	    	//NB: if multiple config , http.authorizeRequests() means http.antMatcher("/**").authorizeRequests()....
	    	http.authorizeRequests()
			.antMatchers("/",
	                "/favicon.ico",
	                "/**/*.png",
	                "/**/*.gif",
	                "/**/*.svg",
	                "/**/*.jpg",
	                "/**/*.html",
	                "/**/*.css",
	                "/**/*.js").permitAll()
	    	.antMatchers("/devise-api/public/**").permitAll()
	    	.antMatchers("/devise-api/private/**").authenticated()
			//.antMatchers("/**").permitAll()
			//.and().formLogin().permitAll()
			 .and().cors()//enable CORS (avec @CrossOrigin sur class @RestController)
			.and().csrf().disable()
			// If the user is not authenticated, returns 401
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			// This is a stateless application, disable sessions
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			// Custom filter for authenticating users using tokens
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
					
	    }

	    @Autowired
	    private JwtAuthenticationFilter jwtAuthenticationFilter;
	    
	    @Autowired
	    private MyNoAuthenticationEntryPoint unauthorizedHandler;

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }


}
