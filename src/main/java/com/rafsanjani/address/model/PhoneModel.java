package com.rafsanjani.address.model;

import com.rafsanjani.address.HibernateUtil;
import com.rafsanjani.address.dao.PhoneDao;
import com.rafsanjani.address.entity.Phone;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class PhoneModel implements PhoneDao {

    private static Session session;

    @Override
    public ObservableList<Phone> getPhones() {

        ObservableList<Phone> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Phone> phones = session.createQuery("from Phone").list();
        session.beginTransaction().commit();
        phones.stream().forEach(list::add);

        return list;
    }

    @Override
    public Phone getPhone(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Phone phone = session.get(Phone.class, id);
        session.getTransaction().commit();

        return phone;
    }

    @Override
    public void savePhone(Phone phone) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(phone);
        session.getTransaction().commit();
    }

    @Override
    public void updatePhone(Phone phone) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Phone p = session.get(Phone.class, phone.getId());
        p.setOfficial(phone.getOfficial());
        p.setMobile(phone.getMobile());
        session.getTransaction().commit();
    }

    @Override
    public void deletePhone(Phone phone) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Phone p = session.get(Phone.class, phone.getId());
        session.delete(p);
        session.getTransaction().commit();
    }

}
