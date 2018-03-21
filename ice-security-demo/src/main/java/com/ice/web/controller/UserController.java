package com.ice.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ice.dto.User;
import com.ice.security.core.properties.SecurityProperties;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/8 10:46
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ProviderSignInUtils providerSignInUtils;

   /* @Autowired
    private AppSignUpUtils appSignUpUtils;*/


    @Autowired
    private SecurityProperties securityProperties;


    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {
        //不管是注册用户还是绑定用户都会拿到用户的唯一标识
        String userId = user.getUsername();

        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
        //appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }

    /*@GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        //return SecurityContextHolder.getContext().getAuthentication();
        return user;
    }*/


    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user, HttpServletRequest request) throws Exception {
        /*String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");

        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                    .parseClaimsJws(token).getBody();

        String company = (String) claims.get("company");

        System.out.println(company);*/
        return user;
    }




    @PostMapping
    @JsonView(User.UserDiteleView.class)
    @ApiOperation(value= "用户查询服务")//用于swagger方法说明
    public User createUser(@RequestBody @Valid User user, BindingResult error) {
        if (error.hasErrors()) {
            error.getAllErrors().forEach(err -> System.out.println(err.getDefaultMessage()));
        }

        return user;
    }


    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(User uer) {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }


    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserSimpleView.class)
    public User getUserInfo(@ApiParam(value = "用户id") @PathVariable Integer id) {

        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("123456");
        user.setAge(25);
        if (id == 1) {
            return user;
        }else {
            return null;
        }
    }

    @PutMapping("/{id:\\d+}")
    @JsonView(User.UserDiteleView.class)
    public User updateUser(@RequestBody @Valid User user, BindingResult error) {
        if (error.hasErrors()) {
            error.getAllErrors().forEach(err -> {
                FieldError fieldError = (FieldError) err;
                String message = fieldError.getField() + "：" + err.getDefaultMessage();
                System.out.println(message);
            });
        }

        return user;
    }


    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable Integer id) {
        System.out.println(id);
    }
}
