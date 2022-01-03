package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            Query<Coach> findCoachesByExperience = session.createQuery("FROM Coach "
                    + "WHERE experience > :years", Coach.class);
            findCoachesByExperience.setParameter("years", years);
            return findCoachesByExperience.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all coaches with experience greater than: "
                    + years, e);
        }
    }
}
