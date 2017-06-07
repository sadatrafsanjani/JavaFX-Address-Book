package com.rafsanjani.address.model;

import com.rafsanjani.address.HibernateUtil;
import com.rafsanjani.address.dao.GeneralDao;
import com.rafsanjani.address.entity.General;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class GeneralModel implements GeneralDao {
    
    private static Session session;

    @Override
    public ObservableList<General> getGenerals() {
        ObservableList<General> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<General> generals = session.createQuery("from General").list();
        session.beginTransaction().commit();
        generals.stream().forEach(list::add);

        return list;
    }

    @Override
    public General getGeneral(int id) {
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        General general = session.get(General.class, id);
        session.getTransaction().commit();

        return general;
    }

    @Override
    public void saveGeneral(General general) {
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(general);
        session.getTransaction().commit();
    }

    @Override
    public void updateGeneral(General general) {
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        General g = session.get(General.class, general.getId());
        g.setPhone(general.getPhone());
        g.setWeb(general.getWeb());
        g.setTitle(general.getTitle());
        g.setFirstName(general.getFirstName());
        g.setLastName(general.getLastName());
        g.setOrganization(general.getOrganization());
        g.setAddress(general.getAddress());
        g.setPicture(general.getPicture());
        session.getTransaction().commit();
    }

    @Override
    public void deleteGeneral(General general) {
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        General g = session.get(General.class, general.getId());
        session.delete(g);
        session.getTransaction().commit();
    }
    
}
