package com.example.myapp.member;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.myapp.member.model.Member;

@Component
public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Member member = (Member) target;

		String pw1 = member.getPassword();
		String pw2 = member.getPassword2();

		if (pw1 != null && pw1.equals(pw2)) {
			// pass
		} else {
			errors.rejectValue("password2", "PASSWORD_NOT_EQUALS", "비밀번호가 일치하지 않습니다.");
		}

	}

}
