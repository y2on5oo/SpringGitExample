package com.example.myapp.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.myapp.member.model.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	private static final SecretKey key = Jwts.SIG.HS256.key().build();

	private static final String AUTH_HEADER = "X-AUTH-TOKEN";
	private long tokenValidTime = 30 * 60 * 1000L;

	@Autowired
	UserDetailsService userDetailsService;

	public String generateToken(Member member) {
		long now = System.currentTimeMillis();
		Claims claims = Jwts.claims().subject(member.getUserid()).setIssuer(member.getName()).issuedAt(new Date(now))
				.expiration(new Date(now + tokenValidTime)).add("roles", member.getRole()).build();
		return Jwts.builder().claims(claims).signWith(key).compact();
	}

	public String resolveToken(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}

	private Claims parseClaims(String token) {
		log.info(token);
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public String getUserId(String token) {
		return parseClaims(token).getSubject();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUserId(token));
		log.info(userDetails.getUsername());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public boolean validateToken(String token) {
		try {
			Claims claims = parseClaims(token);
			return !claims.getExpiration().before(new Date());
		} catch (Exception e) {
			log.error("TOKEN VALIDATION FAILED: {}", e.getMessage());
			return false;
		}
	}

}
