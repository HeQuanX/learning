package cn.crabapples.spring.common.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * TODO service切面拦截
 *
 * @author Mr.He
 * 2020/1/30 18:07
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Aspect
@Configuration
@Order(50)
public class AopConfigure {
    private static final Logger logger = LoggerFactory.getLogger(AopConfigure.class);
    private static final String CONTROLLER_AOP = "execution(* cn.crabapples.spring.system.controller.*.*(..))";

    @Pointcut(CONTROLLER_AOP)
    public void controllerAop() {
    }

    @Around("controllerAop()")
    public Object controllerAop(ProceedingJoinPoint join) throws Throwable {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//        assert servletRequestAttributes != null;
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        Enumeration<String> enumeration = request.getHeaderNames();
//        while (enumeration.hasMoreElements()) {
//            String name = enumeration.nextElement();
//            String value = request.getHeader(name);
//            System.err.println(name+ ":" + value);
//        }
//        System.err.println(enumeration);
        MethodSignature methodSignature = (MethodSignature) join.getSignature();
        Method method = methodSignature.getMethod();
        logger.info("切面拦截成功,拦截方法:[{}],参数:[{}]", method.getName(), join.getArgs());
        Object result = join.proceed();
        logger.info("切面拦截结束,返回值:[{}]", result);
        return result;
    }

}
