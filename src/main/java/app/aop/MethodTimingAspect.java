package app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

@Aspect
@Component
public class MethodTimingAspect {

    private static final Logger log = LoggerFactory.getLogger(MethodTimingAspect.class);

    // ПАКЕТ ДЛЯ ЗАМЕРА: сервисы (можно поменять на app.dao..* если нужно)
    @Around("execution(* app.service..*(..))")
    public Object measure(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        try {
            return pjp.proceed();
        } finally {
            long spent = System.nanoTime() - start;
            String key = pjp.getSignature().toLongString();
            totalNanos.computeIfAbsent(key, k -> new LongAdder()).add(spent);
        }
    }

    private final Map<String, LongAdder> totalNanos = new ConcurrentHashMap<>();

    @EventListener(ContextClosedEvent.class)
    public void printReport() {
        log.info("==== METHOD TIME REPORT (total, desc) ====");
        totalNanos.entrySet().stream()
                .sorted(Map.Entry.<String, LongAdder>comparingByValue(
                        Comparator.comparingLong(LongAdder::sum)).reversed())
                .forEach(e -> log.info("{} -> {} ms",
                        e.getKey(), String.format("%.3f", e.getValue().sum() / 1_000_000.0)));
        log.info("==== END REPORT ====");
    }

}
