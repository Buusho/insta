package mvc.service;

import mvc.entiity.User;

import java.util.List;

public interface UserService {
    String signUpWithInfoAndFollowers (User user );

    User signIn(User user);

    User findUserById(Long id);
    String updateUserById(Long id,User newUser);

    boolean deleteUserById(Long id);

    List<User> userProfile();
}
