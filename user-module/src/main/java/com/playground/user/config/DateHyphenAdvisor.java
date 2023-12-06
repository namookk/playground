package com.playground.user.config;

import com.playground.user.payload.annotation.DateHyphen;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @패키지명 : com.hanssem.hfcmwebapi.common.aop
 * @파일명 : DateHyphenAdvisor
 * @파일생성자 :2022Z098
 * @파일생성일 : 2023-02-21
 * @프로그램설명 : =========================================================================== 날짜 작성자 비고
 * 2023-02-21         2022Z098             최초 생성
 */

@Aspect
@RequiredArgsConstructor
@Component
public class DateHyphenAdvisor {

    @Before("execution(* com.playground.user.controller.*Controller.*(..))")
    public void processDateHyphenAnnotationBefore(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Parameter[] params = method.getParameters();
            for (int start = 0, end = params.length; start < end; start++) {
                Parameter param = params[start];
                try {
                    DateHyphen dateHyphen = param.getAnnotation(DateHyphen.class);
                    PathVariable pathvaliable = param.getAnnotation(PathVariable.class);
                    ModelAttribute modelAttribute = param.getAnnotation(ModelAttribute.class);
                    RequestBody requestBody = param.getAnnotation(RequestBody.class);

                    if (isString(args[start])) {
                        /*
                        String value = (String)args[start];
                        value = value == null ? "" : value;

                        if(value.length() == 10 && dateHyphen!= null && pathvaliable != null) {
                            args[start] = value.replaceAll("-", "");
                        }
                         */
                    } else if (modelAttribute != null || requestBody != null) {
                        for (Object argument : args) {
                            check(argument, "IN", "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isList(Object obj) {
        String className = obj.getClass().getSimpleName();
        if (className.equals("ArrayList") || className.equals("List")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isString(Object obj) {
        String className = obj.getClass().getSimpleName();
        if (className.equals("String")) {
            return true;
        } else {
            return false;
        }
    }

    private void check(Object obj, String type, String sep) throws IllegalAccessException {
        if (obj != null) {
            if (isList(obj)) {
                List list = (List) obj;

                for (Object data : list) {
                    check(data, type, "");
                }
            } else if (isString(obj)) {
                String val = (String) obj;

                if ("IN".equals(type)) {
                    if (val.length() == 10) {
                        val = val.replaceAll(sep, "");
                        obj = val;
                    }
                }
            } else {

                Field[] fields = obj.getClass().getDeclaredFields();

                for (Field field : fields) {
                    DateHyphen dateHyphen = field.getAnnotation(DateHyphen.class);

                    if (field.getType().isAssignableFrom(String.class)) {
                        if (dateHyphen != null) {
                            Object value = invokeGetter(obj, field.getName());

                            if (value != null) {
                                String val = (String) value;

                                if ("IN".equals(type)) {
                                    if (val.length() == 10) {
                                        val = val.replaceAll("-", "");
                                        invokeSetter(obj, field.getName(), val);
                                    }
                                }
                            }
                        }
                    } else if (obj.getClass().getSimpleName().startsWith("Request")
                        || obj.getClass().getSimpleName().startsWith("Response") || obj.getClass()
                        .getSimpleName().endsWith("Entity")) {
                        Object value = invokeGetter(obj, field.getName());

                        if (value != null) {
                            check(value, type, "-");
                        }
                    }
                }
            }
        }
    }

    public void invokeSetter(Object obj, String propertyName, Object variableValue) {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor(propertyName, obj.getClass());
            Method setter = pd.getWriteMethod();
            setter.invoke(obj, variableValue);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
                 IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public Object invokeGetter(Object obj, String variableName) {
        Object ret = null;
        PropertyDescriptor pd;

        try {
            pd = new PropertyDescriptor(variableName, obj.getClass());
            Method getter = pd.getReadMethod();
            ret = getter.invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
                 IntrospectionException e) {
            e.printStackTrace();
        }

        return ret;
    }
}

