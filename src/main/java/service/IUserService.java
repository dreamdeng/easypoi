package service;

import java.util.List;

import domain.entity.User;

public interface IUserService {

    List<User> getList();

    User save(User user);

    void save(List<User> list);

}
