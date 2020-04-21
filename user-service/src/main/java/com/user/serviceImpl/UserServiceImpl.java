package com.user.serviceImpl;

import com.user.dao.UserRepository;
import com.user.model.User;
import com.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Override
    public User findUserById(User user) {
        Optional<User> one = userRepository.findById(user.getUserId());
        User res = null;
        try {
            res = one.get();
        }catch (Exception e){
            System.out.println("not exist");
        }
        return res;
    }

    @Override
    public User addNewUser(User user) {
        User save = userRepository.save(user);
        if(save != null)
            return save;
        return null;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteById(user.getUserId());
    }
}
