package posmotriKa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import posmotriKa.models.User;
import posmotriKa.models.Video;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class VideoRepositoryJpaImpl implements VideoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String SQL_SELECT_BY_NAME =
            "select * from posmotrika.video where videoName = ?";
    private static final String SQL_INSERT =
            "insert into posmotrika.video (videoName) values (?)";
    private final String SQL_SELECT_BY_ID =
            "select * from posmotrika.video where id = ?";
    private final String SQL_UPDATE =
            "update posmotrika.video set videoName = ? where id = ?";
    private final String SQL_DELETE =
            "delete posmotrika.video where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Video> categoryRowMapper = (row, rowNumber) ->
            Video.builder()
                    .id(row.getLong("id"))
                    .videoName(row.getString("videoName"))
                    .build();

    @Transactional
    @Override
    public Optional<Video> update(Video video) {
        try {
            Video video1 = entityManager.merge(video);
            return Optional.ofNullable(video1);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<Video> save(Video video) {
        try {
            entityManager.persist(video);
            return Optional.ofNullable(video);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<Video> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(Video.class, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<Video> findByName(String videoName) {
        try {
            Video video = entityManager.createQuery("select v from Video v where v.videoName = :videoName", Video.class).setParameter("videoName", videoName).getSingleResult();
            return Optional.ofNullable(video);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<Video> delete(Long id) {
        try {
            Video video = findById(id).get();
            entityManager
                    .remove(video);
            return Optional.ofNullable(video);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
