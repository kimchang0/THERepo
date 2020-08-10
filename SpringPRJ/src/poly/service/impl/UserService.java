package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

@Service("UserService")
public class UserService implements IUserService{
	
	@Resource(name="UserMapper")
	private IUserMapper userMapper;

	@Override
	public UserDTO getUserInfo(UserDTO tDTO) {
		return userMapper.getUserInfo(tDTO);
	}

	@Override
	public int UserSignUp(UserDTO tDTO) {
		return userMapper.UserSignUp(tDTO);
	}


	@Override
	public UserDTO idCheck(String userId) {
		return userMapper.idCheck(userId);
	}

	@Override
	public UserDTO emailCheck(String userEmail) {
		// TODO Auto-generated method stub
		return userMapper.emailCheck(userEmail);
	}
	
}
