package kr.co.hta.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hta.board.dao.UserDao;
import kr.co.hta.board.exception.SimpleBoardException;
import kr.co.hta.board.vo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;
	
	@Override
	public void addNewUser(User user) {
		List<User> users = userdao.searchUsers(user.getId());
		if (!users.isEmpty()) {
			throw new SimpleBoardException("입력한 아이디가 이미 사용중입니다.");
		}
		userdao.addUser(user);
	}

	@Override
	public User getUserDetail(String userId) {
		return userdao.getUserById(userId);
	}

	
}
