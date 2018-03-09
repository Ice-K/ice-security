package com.ice.commons;

import com.ice.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/9 9:04
 */
@ControllerAdvice
public class BaseController {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//抛出状态码为500的异常
    public Map<String, Object> handlerControllerException(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        if (e instanceof MyException) {
            result.put("code",((MyException) e).getCode());
        }
        return result;

    }
}
