/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008-2017  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nz.co.vincenzo.openmpis.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * The aspect containing advices for logging.
 *
 * @author Rey Vincent Babilonia
 * @since 2.0.0
 */
@Aspect
@Component
public class OpenMpisAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenMpisAspect.class);

    /**
     * Logs the start of every DAO method.
     *
     * @param joinPoint the {@link JoinPoint}
     */
    @Before("Pointcuts.allDAOMethods()")
    public void logBeforeDAOMethods(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        LOGGER.debug("Start of " + className + "#" + methodName + " with arguments " + Arrays.toString(arguments));
    }

    /**
     * Logs the exception, if any, of every DAO method.
     *
     * @param joinPoint the {@link JoinPoint}
     * @param throwable the {@link Throwable}
     */
    @AfterThrowing(value = "Pointcuts.allDAOMethods()", throwing = "throwable")
    public void logAfterThrowingDAOMethods(JoinPoint joinPoint, Throwable throwable) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.error(throwable + " thrown by " + className + "#" + methodName);
    }

    /**
     * Logs the result, if any, of every DAO method.
     *
     * @param joinPoint the {@link JoinPoint}
     * @param result    the returning result
     */
    @AfterReturning(value = "Pointcuts.allDAOMethods()", returning = "result")
    public void logAfterReturningDAOMethods(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.debug("End of " + className + "#" + methodName + " with result " + result);
    }

    /**
     * Logs the start of every service method.
     *
     * @param joinPoint the {@link JoinPoint}
     */
    @Before("Pointcuts.allServiceMethods()")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        LOGGER.debug("Start of " + className + "#" + methodName + " with arguments " + Arrays.toString(arguments));
    }

    /**
     * Logs the exception, if any, of every service method.
     *
     * @param joinPoint the {@link JoinPoint}
     * @param throwable the {@link Throwable}
     */
    @AfterThrowing(value = "Pointcuts.allServiceMethods()", throwing = "throwable")
    public void logAfterThrowingServiceMethods(JoinPoint joinPoint, Throwable throwable) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.error(throwable + " thrown by " + className + "#" + methodName);
    }

    /**
     * Logs the result, if any, of every service method.
     *
     * @param joinPoint the {@link JoinPoint}
     * @param result    the returning result
     */
    @AfterReturning(value = "Pointcuts.allServiceMethods()", returning = "result")
    public void logAfterReturningServiceMethods(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.debug("End of " + className + "#" + methodName + " with result " + result);
    }
}
