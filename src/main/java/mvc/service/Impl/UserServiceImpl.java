package mvc.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Follower;
import mvc.entiity.User;
import mvc.entiity.UserInfo;
import mvc.repo.UserRepository;
import mvc.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public static User currentUser;
    @Override @Transactional
    public String signUpWithInfoAndFollowers(User user) {
        UserInfo userInfo = new UserInfo();
        Follower follower = new Follower();
        user.setUserInfo(userInfo);
        user.setFollower(follower);
        userInfo.setUser(user);
        follower.setUser(user);
        return userRepository.signUpWithInfoAndFollowers(user);
    }

    @Override
    public User signIn(User user) {
currentUser  = userRepository.signIn(user);
        return currentUser;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public String updateUserById(Long id, User newUser) {
        return userRepository.updateUserById(id,newUser);
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    public List<User> userProfile() {
        return userRepository.userProfile();
    }
}
