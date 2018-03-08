package com.ice.validator;

import com.ice.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Description：处理注解检验的逻辑
 * 注意：
 *      1：必须实现ConstraintValidator<A,T>接口    A-为作用的注解   T-为要传入的参数
 *      2：重写initialize() 和  isValid() 两个方法。
 *      3: 类中可以@Autowried 注入其他服务来辅助校验逻辑
 * Cteated by wangpeng
 * 2018/3/8 17:33
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object>{

    @Autowired
    private HelloService helloService;

    /**
     * 初始化校验器
     */
    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }


    /**
     * 校验逻辑
     * @param o 为注解传来的值
     *
     * 当返回的结果为false时，注解上的message就会被获取到
     *
     */
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("pojo");
        System.out.println(o);

        return false;
    }


}
