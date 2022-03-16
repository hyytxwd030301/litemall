package com.hyy.admin.annotation;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermissionDesc {
    String[] menu();

    //操作
    String operate();
}
