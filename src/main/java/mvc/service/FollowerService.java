package mvc.service;

import mvc.entiity.Follower;
import mvc.entiity.User;
import mvc.entiity.UserInfo;

import java.util.List;

public interface FollowerService {
    List<User> search(User userName, UserInfo fullName);

    User subscribe (User profile);
    Follower getAllSubscribersByUserId(Long userId);
    Follower getAllSubscriptionsByUserId(Long userId);
}
