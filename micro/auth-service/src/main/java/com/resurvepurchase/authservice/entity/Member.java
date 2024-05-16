package com.resurvepurchase.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
	private String email; // 이메일
	private String password; // 비밀번호
}