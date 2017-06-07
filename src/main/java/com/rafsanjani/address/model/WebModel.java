package com.rafsanjani.address.model;

import com.rafsanjani.address.HibernateUtil;
import com.rafsanjani.address.dao.WebDao;
import com.rafsanjani.address.entity.Web;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class WebModel implements WebDao {

    private static Session session;

    @Override
    public ObservableList<Web> getWebs() {

        ObservableList<Web> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Web> webs = session.createQuery("from Web").list();
        session.beginTransaction().commit();
        webs.stream().forEach(list::add);

        return list;
    }

    @Override
    public Web getWeb(int id) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Web web = session.get(Web.class, id);
        session.getTransaction().commit();

        return web;
    }

    @Override
    public void saveWeb(Web web) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(web);
        session.getTransaction().commit();
    }

    @Override
    public void updateWeb(Web web) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Web w = session.get(Web.class, web.getId());
        w.setEmail(web.getEmail());
        w.setWebsite(web.getWebsite());
        w.setLinkedin(web.getLinkedin());
        w.setFacebook(web.getFacebook());
        session.getTransaction().commit();
    }

    @Override
    public void deleteWeb(Web web) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Web w = session.get(Web.class, web.getId());
        session.delete(w);
        session.getTransaction().commit();
    }

}
