package pe.com.tech.portal.empleado.aspect;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import pe.com.tech.portal.empleado.error.HandlerException;
import pe.com.tech.portal.empleado.util.Util;

import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;


@Aspect
public class Logging {
    private static final Logger log = LogManager.getLogger(Logging.class);
    private static long initTime;
    private static final String UNSUCESSFUL = "La operación no se realizó satisfactoriamente.";


    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut(
            "within(pe.com.tech.portal.empleado.controller..*)"
                    + "|| within(pe.com.tech.portal.empleado.service..*)"
                    + "|| within(pe.com.tech.portal.empleado.security.auth.ajax.AjaxAuthenticationProvider)")
    public void loggingPointcut() {
        // Method is empty as this is just a Poincut, the implementations are in the advices.
    }

    @Pointcut(
            "within(pe.com.tech.portal.empleado.controller..*)"
                    + "|| within(pe.com.tech.portal.empleado.security.auth.ajax.AjaxAuthenticationProvider)")
    public void errorPointcut() {
        // Method is empty as this is just a Poincut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     */
    @AfterThrowing(pointcut = "errorPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}({}) with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), concatenateSignatureArgs(joinPoint.getArgs()), Util.traceException(e));
    }

    /**
     * Advice that logs when a method is entered and exited.
     */
    @Around("loggingPointcut()")
    public Object logAroundEndpoint(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            initTime = System.currentTimeMillis();
            String parametros = concatenateArgs(joinPoint);
            log.debug("Inicio: {}.{}({}) with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), concatenateSignatureArgs(joinPoint.getArgs()), parametros);
        }
        try {
            String resultado;
            Object result = joinPoint.proceed();
            try {
                resultado = Util.formatAsJson(result);
            } catch (JsonMappingException ex) {//It's only caught for FileSystemResource files
                log.debug("Exception: " + ex.getMessage());
                resultado = String.valueOf(joinPoint.proceed());
            }

            if (log.isDebugEnabled()) {
                log.debug("Fin: {}.{}({}) with result = {} \n and total time: {} ms", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), concatenateSignatureArgs(joinPoint.getArgs()), resultado, (System.currentTimeMillis() - initTime));
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw new HandlerException(e.getLocalizedMessage());
        } catch (HandlerException ex) {
            throw new HandlerException(ex.getMessage(), ex);
        } catch (Throwable thw) {
            throw new HandlerException(UNSUCESSFUL, thw);
        }
    }

    private String concatenateArgs(ProceedingJoinPoint joinPoint) throws Exception {
        String parametros;
        try {
            parametros = Util.formatAsJson(joinPoint.getArgs());
        } catch (JsonMappingException ex) {//It's only caught for excel type inputs
            log.debug("Exception: " + ex.getMessage());
            StringBuilder params = new StringBuilder();
            for (Object object : joinPoint.getArgs())
                params.append(object.toString()).append(" ");

            parametros = params.toString();
        }
        return parametros;
    }

    private static String concatenateSignatureArgs(Object[] args) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Object object : args) {
            Optional optional = Optional.ofNullable(object);
            if (optional.isPresent())
                stringJoiner.add(object.getClass().getSimpleName());
        }
        return stringJoiner.toString();
    }
}
