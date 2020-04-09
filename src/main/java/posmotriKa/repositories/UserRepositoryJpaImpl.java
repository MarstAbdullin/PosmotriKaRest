package posmotriKa.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import posmotriKa.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJpaImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<User> update(User user) {
        User user1 = entityManager
                .merge(user);
        return Optional.ofNullable(user1);
    }

    @Override
    @Transactional
    public Optional<User> save(User user) {
        entityManager
                .persist(user);
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager
                .find(User.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByName(String email) {
        User user = entityManager
                .createQuery("select user from User user where user.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public Optional<User> delete(Long id) {
        User user = findById(id).get();
        entityManager
                .remove(user);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("select u from User u", User.class)
                .getResultList();
    }
}
