package mvc.repo;

import mvc.entiity.User;

import java.util.List;

public interface UserRepository {
String signUpWithInfoAndFollowers (User user );

User signIn(User user);

User findUserById(Long id);
String updateUserById(Long id,User newUser);

boolean deleteUserById(Long id);

List<User> userProfile();


}
