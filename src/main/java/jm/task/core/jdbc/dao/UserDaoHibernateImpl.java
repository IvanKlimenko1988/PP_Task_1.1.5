package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util db = new Util();
    private Query query;
    private Session session;
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users"
            + "(id INT PRIMARY KEY AUTO_INCREMENT,"
            + "userName VARCHAR(20) NOT NULL,"
            + "lastName VARCHAR(20) NOT NULL,"
            + "age INT NOT NULL)";

    private final String DROP_TABLE = "DROP TABLE IF EXISTS users";

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        session = db.session(db.getSessionFactory());
        session.beginTransaction();
        query = session.createSQLQuery(CREATE_TABLE)
                .addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Database has been created!");
    }

    @Override
    public void dropUsersTable() {
        session = db.session(db.getSessionFactory());
        session.beginTransaction();
        query = session.createSQLQuery(DROP_TABLE)
                .addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Drop table users");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = db.session(db.getSessionFactory());
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        session = db.session(db.getSessionFactory());
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        System.out.println("User id " + id + " deleted");
    }

    @Override
    public List<User> getAllUsers() {
        session = db.session(db.getSessionFactory());
        session.beginTransaction();
        List<User> users = session.createQuery("FROM User")
                .getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = db.session(db.getSessionFactory());
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
        System.out.println("Table users clean");
    }
}
