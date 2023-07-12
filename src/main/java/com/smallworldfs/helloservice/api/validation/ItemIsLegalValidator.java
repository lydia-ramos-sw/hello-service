package com.smallworldfs.helloservice.api.validation;

import com.smallworldfs.helloservice.api.dto.ItemRequestDto;
import java.text.MessageFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ItemIsLegalValidator implements ConstraintValidator<ItemIsLegal, ItemRequestDto> {
    @Override
    public boolean isValid(ItemRequestDto itemRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        if (itemRequestDto.getItem() != null
                && itemRequestDto.getItem() == 39
                && !itemRequestDto.getGrantLevel().equals("A")) {
            addConstraintViolation(constraintValidatorContext, itemRequestDto.getItem(),
                    itemRequestDto.getGrantLevel());
            return false;
        }
        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, Integer item, String grant) {
        String message = MessageFormat.format(context.getDefaultConstraintMessageTemplate(),
                item,
                grant);

        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("item")
                .addConstraintViolation();
    }
}
