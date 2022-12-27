package com.woopaca.myweb.validator;

import com.woopaca.myweb.entity.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Board b = (Board) target;
        if (StringUtils.isEmpty(b.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요.");
            return;
        }
        if (StringUtils.length(b.getContent()) < 10 || StringUtils.length(b.getContent()) > 60000) {
            errors.rejectValue("content", "key", "10자 이상 60000자 이하로 입력해야 합니다.");
        }
    }
}
