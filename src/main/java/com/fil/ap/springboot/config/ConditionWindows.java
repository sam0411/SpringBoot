package com.fil.ap.springboot.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionWindows implements Condition {

    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        
    	System.out.println(conditionContext.getEnvironment().getProperty("os.name"));
    	return conditionContext.getEnvironment().getProperty("os.name").contains("Windows");
    }
}