package Repository;

import Models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeRepository implements IEmployeeRepository {

    private final SessionFactory sessionFactory;

    public EmployeeRepository(String configFileName) {

        Configuration configuration = new Configuration();
        configuration.configure(configFileName);
        this.sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public void save(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            session.close();
        }
    }

    @Override
    public Employee findById(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = null;

        try {
            employee = (Employee) session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employee;
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            session.close();
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}