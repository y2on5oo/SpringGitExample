package com.example.myapp.member.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import com.example.myapp.member.service.MemberService;

@Component
public class MemberUserDetailsService implements UserDetailsService {

    private final LocaleChangeInterceptor localeChangeInterceptor;
	
	@Autowired
	MemberService memberService;

    MemberUserDetailsService(LocaleChangeInterceptor localeChangeInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Member memberInfo = memberService.selectMember(username);
		if (memberInfo == null) {
			throw new UsernameNotFoundException(username+" 사용자를 찾을 수 없음");
		}
		
		String [] roles = {"ROLE_USER", "ROLE_ADMIN"};
		List <GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
		return new MemberUserDetails(memberInfo.getUserid(), memberInfo.getPassword(), authorities, memberInfo.getEmail());
	}

}
