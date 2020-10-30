package poly.service;

import poly.dto.UserDTO;

public interface IMailService {

	int doSendMail(UserDTO uDTO);

	int loginCheck(UserDTO tDTO);

}