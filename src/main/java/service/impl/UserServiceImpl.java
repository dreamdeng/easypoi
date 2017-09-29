package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.UserRepository;
import domain.entity.User;
import service.IUserService;



@Service
public class UserServiceImpl implements IUserService {

	public List<User> getList() {
		return mUserRepository.findAll();
	}

	public User save(User user) {
		 return mUserRepository.save(user);
	}

	public void save(List<User> list) {
		mUserRepository.save(list);
	}

    @Autowired
    private UserRepository mUserRepository;
}
