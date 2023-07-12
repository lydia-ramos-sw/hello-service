package com.smallworldfs.helloservice.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemIsLegalValidator.class)
public @interface ItemIsLegal {
    String message() default "Item can not be {0} when grant is {1}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
