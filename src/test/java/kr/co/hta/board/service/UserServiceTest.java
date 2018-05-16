package kr.co.hta.board.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.co.hta.board.exception.SimpleBoardException;
import kr.co.hta.board.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)	// 로케이션에 설정정보를 읽어가서 스프링컨테이너를 하나 만든다. 그 다음에 오토와이어도 읽어서 주입까지 해준다.
@ContextConfiguration(locations="classpath:/META-INF/spring/test-root-context.xml")
@Transactional	// DB에 상관없이 실행 후 삭제된다. 그래서 없는데도 이상없이 실행됨.
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	public void testConfig() {
		assertThat(userService, notNullValue());	// userService null이 아닌지 체크해줘
	}
	
	// 일부러 예외가 발생하는걸 테스트
	@Test(expected=SimpleBoardException.class)
	public void testDuplicateUserAdd() {
		User user = new User();
		user.setId("hong");
		user.setPwd("zxcv1234");
		user.setName("홍길동");
		
		userService.addNewUser(user);
	}
	
	@Test
	public void testAddNewUser() {
		User user = new User();
		user.setId("moon");
		user.setPwd("zxcv1234");
		user.setName("문재인");
		
		userService.addNewUser(user);
		
		User savedUser = userService.getUserDetail(user.getId());
		assertThat(savedUser, notNullValue());
	}
}
















