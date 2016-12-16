package ru.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.pft.mantis.model.UserData;
import ru.pft.mantis.model.UsersMantis;

import java.util.List;
import java.util.TimeZone;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public UsersMantis users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("from UserData").list();
        session.getTransaction().commit();
        session.close();
        return new UsersMantis(result);
    }
}
