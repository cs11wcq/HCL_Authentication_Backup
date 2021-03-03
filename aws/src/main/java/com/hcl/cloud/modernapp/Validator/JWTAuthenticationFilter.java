

package com.hcl.cloud.modernapp.Validator;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hcl.cloud.modernapp.model.UserModel;
import org.springframework.jdbc.datasource.lookup.MapDataSourceLookup;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

//src: https://www.freecodecamp.org/news/how-to-setup-jwt-authorization-and-authentication-in-spring/
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    /**
     Authentication Process
     The filter requires that you set the authenticationManager property. An AuthenticationManager is required to process the authentication request tokens created by implementing classes.
     This filter will intercept a request and attempt to perform authentication from that request if the request matches the setRequiresAuthenticationRequestMatcher(RequestMatcher).
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("");
    }

    //Performs actual authentication.
    //Authentication is performed by the attemptAuthentication method, which must be implemented by subclasses.
    //src: https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/authentication/AbstractAuthenticationProcessingFilter.html#attemptAuthentication-javax.servlet.http.HttpServletRequest-javax.servlet.http.HttpServletResponse-
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            //The attemptAuthentication function runs when the user tries to log in
            //to our application. It reads the credentials, creates a user POJO from them (json to java),
            //and then checks the credentials to authenticate.
            //req.getInputStream() returns a ServletInputStream object containing the body of the request
            ServletInputStream stream = req.getInputStream();
            //src: https://stackoverflow.com/questions/18794427/convert-inputstream-into-json
            UserModel user = new ObjectMapper().readValue(stream, UserModel.class);

            //  return authenticationManager.authenticate(
            //         new UsernamePasswordAuthenticationToken(
            //                 user.getUsername(),
            //                 user.getPassword(),
            //                 new ArrayList<>())
            // );
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
            // Authentication auth = authenticationManager.authenticate(token);
            // return auth;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
