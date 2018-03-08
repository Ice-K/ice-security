package com.ice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description：自定义的注解约束（constraint）
 *
 * @Target 说明注解要作用的地方，FIELD-字段上   METHOD-方法上   PACKAGE-包上  PARAMETER-参数上  TYPE-类上
 *
 * @Retention 注解作用的时机   RUNTIME-运行时起作用
 *
 * @Constraint 声明处理逻辑的类
 *
 * 注意：校验注解必须有三个属性 一下三个属性：
 * String message();
 * Class<?>[] groups() default {};
 * Class<? extends Payload>[] payload() default {};
 *
 * Cteated by wangpeng
 * 2018/3/8 17:30
 */
@Target({ElementType.FIELD,ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
