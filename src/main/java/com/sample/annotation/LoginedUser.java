package com.sample.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Target: 어노테이션 적용대상, ElementType.PARAMETER은 메소드나 생성자의 매개변수에 부착할 수 있는 어노테이션이다.
 * @Retention: 어노테이션 유지 정책, RetentionPolicy.RUNTIME은 이 어노테이션이 프로그램 실행 시점까지 유지됨을 나타낸다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginedUser {

}
