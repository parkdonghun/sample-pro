package kr.co.hta.board.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Target(ElementType.TYPE) //인터
 * @Target(ElementType.CONSTRUCTOR) //생성
 * @Target(ElementType.FIELD) //변수
 * @Target(ElementType.METHOD) //필드
 */

@Target(ElementType.PARAMETER) //매개
@Retention(RetentionPolicy.RUNTIME)	// RetentionPolicy 해석되는시점,  RUNTIME 프로그램 실행될때 해석되게하겠다.
@Documented
public @interface LoginUser {

	
}
