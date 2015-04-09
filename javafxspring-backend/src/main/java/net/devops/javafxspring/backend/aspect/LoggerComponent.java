package net.devops.javafxspring.backend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerComponent {

    @Around("execution(* *(..)) && @annotation(net.devops.javafxspring.backend.annotation.Loggable)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        Object result = point.proceed();
        Signature signature = point.getSignature();
        Class declaringType = signature.getDeclaringType();
        long elapsedMilliseconds = System.currentTimeMillis() - start;
        long elapsedNanoseconds = System.nanoTime() - nanoTime;
        long elapsedMicroseconds = elapsedNanoseconds / 1000;
        LoggerFactory.getLogger(declaringType).info(
                "{}({}) in {}ms {}us {}ns",
                signature.getName(),
                point.getArgs(),
                elapsedMilliseconds,
                elapsedMicroseconds,
                elapsedNanoseconds
        );
        return result;
    }

}
