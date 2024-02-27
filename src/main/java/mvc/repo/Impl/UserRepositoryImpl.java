package mvc.repo.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.User;
import mvc.repo.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public String signUpWithInfoAndFollowers(User user) {
        entityManager.persist(user);
        return "User signed up successfully!";
    }

    @Override
    public User signIn(User user) {
        try {
            for (User currentUser : userProfile()) {
                if (currentUser.getUser_name().equals(user.getUser_name()) && currentUser.getPassword().equals(user.getPassword())) {
                    return currentUser;
                }
            }
        } catch (Exception e) {
            System.out.println("filed");
        }
        return null;
    }

    @Override
    public User findUserById(Long id) {
        try {

            return entityManager.find(User.class, id);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public String updateUserById(Long id, User newUser) {
        try {
            User user = findUserById(id);
            user.setUser_name(newUser.getUser_name());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            user.setPhone_number(newUser.getPhone_number());
            entityManager.merge(user);
            return "updated successfully";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return "failed";
    }

    @Override
    public boolean deleteUserById(Long id) {
        try {
            entityManager.remove(entityManager.find(User.class, id));
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return false;
    }

    @Override
    public List<User> userProfile() {
        return entityManager.createQuery("select s  from User s ", User.class).getResultList();
    }
}
