package com.example.euphoriagifts2.util;


import com.example.euphoriagifts2.annotations.ValidUrl;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;

public class PictureValidator implements ConstraintValidator<ValidUrl, MultipartFile> {

    @Override
    public void initialize(ValidUrl constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return validateFile(file);
    }

       private boolean validateFile(MultipartFile file)  {
            return (file.getSize() > 0);
        }

}
