package com.example.euphoriagifts2.annotations;

import com.example.euphoriagifts2.util.PictureValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PictureValidator.class)
@Documented
public @interface ValidUrl {

    String message() default "Picture is required";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
