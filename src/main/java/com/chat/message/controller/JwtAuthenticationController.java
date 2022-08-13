package com.chat.message.controller;

import com.chat.message.config.auth.JwtTokenUtil;
import com.chat.message.config.auth.JwtUserDetailsService;
import com.chat.message.exception.CustomException;
import com.chat.message.exception.ErrorCode;
import com.chat.message.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

@Controller
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @GetMapping("/rooms")
    public ModelAndView rooms(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/rooms");
        return modelAndView;
    }

    @RequestMapping(value = "/authenticate")
    public String createAuthenticationToken(@RequestParam("username") String username,
                                            @RequestParam("password") String password) {
        try{
            authenticate(username, password);
            final User user = userDetailsService
                    .loadUser(username);
            final String token = jwtTokenUtil.generateToken(user);
            user.setToken(token);

        }catch (Exception e ){
            throw new CustomException(ErrorCode.USERNAME_OR_PASSWORD_INVALID, ErrorCode.USERNAME_OR_PASSWORD_INVALID.getMessage());
        }
         return "rooms";
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}