package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {
	// 게시판 리스트
	UserDTO getUserInfo(UserDTO tDTO);

	int UserSignUp(UserDTO tDTO);

	UserDTO idCheck(String userId);

	UserDTO emailCheck(String userEmail);

	int insertAuthNum(UserDTO uDTO);

	UserDTO authNumCheck(UserDTO uDTO);

	UserDTO Userinquire(UserDTO uDTO);

	int deleteUser(UserDTO uDTO);

	UserDTO getUserCorrection(UserDTO uDTO);

	int setUserCorrection(UserDTO uDTO);

	int pwdChange(UserDTO uDTO);

	int updateInterest(UserDTO uDTO);

	UserDTO findId(UserDTO uDTO);

}
