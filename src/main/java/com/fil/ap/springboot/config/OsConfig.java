package com.fil.ap.springboot.config;

import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class OsConfig {

    private String os;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Conditional(ConditionLinux.class)
    private void renderLinux() {

        os = "Linux";
    }

    @Conditional(ConditionWindows.class)
    private void renderWindows() {

        os = "Windows";
    }

    class ConditionLinux implements Condition {
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

            System.out.println(conditionContext.getEnvironment().getProperty("os.name"));
            return conditionContext.getEnvironment().getProperty("os.name").contains("Linux");
        }
    }

    class ConditionWindows implements Condition {

        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

            System.out.println(conditionContext.getEnvironment().getProperty("os.name"));
            return conditionContext.getEnvironment().getProperty("os.name").contains("Windows");
        }
    }
}