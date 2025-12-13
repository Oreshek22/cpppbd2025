package app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class DeprecatedStackTraceAspect {

    private static final Logger log = LoggerFactory.getLogger(DeprecatedStackTraceAspect.class);

    @Before("@annotation(java.lang.Deprecated)")
    public void logStackTrace(JoinPoint jp) {
        String method = jp.getSignature().toShortString();

        String stack = Arrays.stream(Thread.currentThread().getStackTrace())
                .skip(2) // чуть “чистим” верх
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));

        log.warn("DEPRECATED CALLED: {}\nStacktrace:\n{}", method, stack);
    }
}
