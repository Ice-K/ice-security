package com.ice.security.core.validate.code.impl;

import com.ice.security.core.validate.code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * Description：抽象的图片验证码处理器
 * Cteated by wangpeng
 * 2018/3/13 9:32
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {


    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     * 将实现的类名作为key
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGennerators;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;


    /**
     * @see
     * com.ice.security.core.validate.code.ValidateCodeProcessor#create(
     * org.springframework.web.context.request.ServletWebRequest)
     * @param request 请求
     * @throws Exception
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }


    /**
     * 生成验证码
     * @param request 请求
     * @return
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGennerators.get(generatorName);

        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器 <" + generatorName + "> 不存在");
        }

        return (C) validateCodeGenerator.generator(request);
    }

    /**
     * 保存校验码
     * @param request 请求
     * @param validateCode 校验码
     */
    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(),validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
    }



    /**
     * 发送校验码，由子类实现
     * @param request 请求
     * @param validateCode 校验码
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;


    /**
     * 根据请求的url获取验证码的类型
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 校验验证码
     * @param request
     */
    @Override
    @SuppressWarnings("unchecked")
    public void validate(ServletWebRequest request) {
        ValidateCodeType codeType = getValidateCodeType(request);

        C codeInSession = (C) validateCodeRepository.get(request, codeType);

        String codeInRequest;
        try{
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException srbe) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpried()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException("验证码过期");
        }

        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码错误");
        }

        validateCodeRepository.remove(request, codeType);

    }


}
