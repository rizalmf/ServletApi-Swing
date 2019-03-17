package services.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import services.models.Anggota;

import java.util.List;

/**
 * @PKANE_NS
 */
public class Dao {
    private Session session;

    public List<Anggota> getAnggotas() throws HibernateException{
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Anggota a order by a.id asc");
        return query.list();
    }
    public Anggota getAnggota(long id)throws HibernateException{
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Anggota a where a.id=:id");
        query.setParameter("id", id);
        return (Anggota) query.uniqueResult();
    }
    public List<Anggota> getAnggotaByKepo(String nama)throws HibernateException{
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Anggota a where a.nama like :nama order by a.nim asc, a.nama asc");
        query.setParameter("nama", "%"+nama+"%");
        return query.list();
    }
    public void save(Object o)throws HibernateException{
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(Object o)throws HibernateException{
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }
}