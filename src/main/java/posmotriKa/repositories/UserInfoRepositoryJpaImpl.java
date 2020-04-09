package posmotriKa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import posmotriKa.models.UserInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserInfoRepositoryJpaImpl implements UserInfoRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = ClassNotFoundException.class)
    public Optional<UserInfo> update(UserInfo userInfo) {
        UserInfo userInfo1 = entityManager.merge(userInfo);
        return Optional.ofNullable(userInfo1);
    }


    @Override
    @Transactional
    public Optional<UserInfo> save(UserInfo userInfo) {
        entityManager.persist(userInfo);
        return Optional.ofNullable(userInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserInfo> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserInfo.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserInfo> findByName(String username) {
        UserInfo userInfo = entityManager.createQuery("select u from UserInfo u where u.username = :username", UserInfo.class).setParameter("username", username).getSingleResult();

        return Optional.ofNullable(userInfo);
    }

    @Override
    @Transactional
    public Optional<UserInfo> delete(Long id) {
        return Optional.empty();
    }
}
