package com.zjnbit.bbs.api.framework.aop;

import com.zjnbit.bbs.api.framework.annotation.DictName;
import com.zjnbit.bbs.api.framework.annotation.DictTranslate;
import com.zjnbit.bbs.api.service.BaseDictService;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
public class DictTransferAspect {
    @Autowired
    BaseDictService baseDictService;

    @SneakyThrows
    @AfterReturning(value = "@annotation(com.zjnbit.bbs.api.framework.annotation.DictTranslate)", returning = "result")
    public void doAfterReturning(JoinPoint point, Object result) {
        if (null == result) {
            return;
        }
        MethodSignature joinPointObject = (MethodSignature) point.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(DictTranslate.class);
        if (flag) {
            Field[] fields = result.getClass().getDeclaredFields();
            for (Field field : fields) {
                DictName annotation = field.getAnnotation(DictName.class);
                if (null != annotation) {
                    ReflectionUtils.makeAccessible(field);
                    String value = baseDictService.getValue(annotation.name(), (String) field.get(result));
                    field.set(result, value);
                    System.out.println(value);
                }
            }
        }
    }
}
