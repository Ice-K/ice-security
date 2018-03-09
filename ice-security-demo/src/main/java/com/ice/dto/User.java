package com.ice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.ice.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/8 10:29
 */
public class User implements Serializable{

    public interface UserSimpleView {}
    public interface UserDiteleView extends UserSimpleView {}

    private static final long serialVersionUID = -8760691790128097097L;
    @ApiModelProperty()
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @MyConstraint(message = "这是一个测试")
    private String password;
    private Integer age;
    @Past(message = "生日不能为未来的时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonView(UserSimpleView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDiteleView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
