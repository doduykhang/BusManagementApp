/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import Entity.BieuDoGio;
import Entity.ChiTietTuyen;
import Entity.DoanhThu;
import Entity.DonViQuanLy;
import Entity.LoaiTuyen;
import Entity.LoaiXe;
import Entity.NhaSanXuat;
import Entity.NhanVien;
import Entity.PhanCong;
import Entity.TaiKhoan;
import Entity.Tuyen;
import Entity.Xe;
import JavaFX.CustomNode.SearchNode;
import JavaFX.CustomNode.SearchPane;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;
import javax.persistence.Entity;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;


/**
 *
 * @author dokha
 */
public class DataGetter {
    private static SessionFactory factory;
    
    public static <T> List<T> listObject(String className){
        factory = HibernateUtil.getSessionFactory();
        List<T> list = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query selectQuery = session.createQuery("FROM " + className);
            list = selectQuery.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return list;
    }
    
    public static <T> List<T> listObject(String className, int lastPageNumber, int pageSize){
        factory = HibernateUtil.getSessionFactory();
        List<T> list = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query selectQuery = session.createQuery("FROM " + className);
            
            selectQuery.setFirstResult((lastPageNumber - 1) * pageSize);
            selectQuery.setMaxResults(pageSize);
            list = selectQuery.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return list;
    }
    
    public static <T> List<T> listObject(Class<T> clazz, SearchPane pane){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<T> cr = cb.createQuery(clazz);
        Root<T> root = cr.from(clazz);
        Predicate[] predicates = new Predicate[pane.getSearchNodes().size()];
        System.out.println("getSearchNodes().size() " + pane.getSearchNodes().size());
        
        for(int i = 0; i < pane.getSearchNodes().size(); i++)
            predicates[i] = pane.getSearchNodes().get(i).get(cb, root, pane.getName(i));
        for(int i = 0; i < predicates.length; i++)
            if(predicates[i] == null)
                System.out.println("predicate is null");
        

        cr.select(root).where(predicates);
        Query<T> query = session.createQuery(cr);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List<T> results = query.getResultList();
        return results;
    }
    
    public static <T> List<T> listObject(Class<T> clazz, SearchPane pane, int lastPageNumber, int pageSize){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<T> cr = cb.createQuery(clazz);
        Root<T> root = cr.from(clazz);
        Predicate [] predicate = new Predicate[pane.getSearchNodes().size()];
        
        for(int i = 0; i < pane.getSearchNodes().size(); i++)
            predicate[i] = pane.getSearchNodes().get(i).get(cb, root, pane.getName(i));
       
        
        cr.select(root).where(predicate);
        Query<T> query = session.createQuery(cr);
        query.setFirstResult((lastPageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<T> results = query.getResultList();
        return results;
    }
    
    public static <T> Long getRowCount(Class<T> clazz, SearchPane pane){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Long resultsCount = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cr = cb.createQuery(Long.class);
            Root<T> root = cr.from(clazz);
            Predicate[] predicates = new Predicate[pane.getSearchNodes().size()];
        
            for(int i = 0; i < pane.getSearchNodes().size(); i++)
                predicates[i] = pane.getSearchNodes().get(i).get(cb, root, pane.getName(i));

            cr.select(cb.count(root)).where(predicates);
            TypedQuery<Long> query = session.createQuery(cr);
            resultsCount = query.getSingleResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return resultsCount;
    }
    
    public static <T> Long getRowCount(Class<T> clazz){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Long resultsCount = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cr = cb.createQuery(Long.class);
            Root<T> root = cr.from(clazz);
            cr.select(cb.count(root));
            TypedQuery<Long> query = session.createQuery(cr);
            resultsCount = query.getSingleResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return resultsCount;
    }
    
    public static Integer addObject(Object object){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        
        try {
            tx = session.beginTransaction();
            Object ob = session.save(object);
            if(ob instanceof Integer)
                id = (Integer) ob; 
            tx.commit();
        }
        catch (HibernateException e) {
            
            System.out.println("HibernateException");
            id = null;
            e.printStackTrace(); 
        } 
        finally {
            if(tx.getStatus() == TransactionStatus.ROLLED_BACK)
                id = null;
            session.close(); 
            return id;
        }
        
    }
    
    public static void updateObject(Object object){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(object);
            tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
    }
    
    public static void deleteObject(Object object){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(object); 
            tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
    }
    
    public static Object mergeObject(Object object){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Object ob = null;
        try {
            tx = session.beginTransaction();
            ob = session.merge(object); 
            tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
           return ob;
        }
    }
    
    public static boolean checkForeignKey(String table, String column, Object id){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.
        createQuery("select 1 from "+table+" where "+column+" = :key");
        query.setParameter("key", id);
        return (query.uniqueResult() != null);
    }
    
    public static NhanVien dangNhap(String tenDangNhap, String matKhau){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<TaiKhoan> cr = cb.createQuery(TaiKhoan.class);
        Root<TaiKhoan> root = cr.from(TaiKhoan.class);
        
        Predicate p1 = cb.equal(root.get("tenDangNhap"), tenDangNhap);
        Predicate p2 = cb.equal(root.get("matKhau"), matKhau);
        cr.select(root).where(cb.and(p1,p2));
        Query<TaiKhoan> query = session.createQuery(cr);
        TaiKhoan taiKhoan;
        try{
            taiKhoan = query.getSingleResult();
            return taiKhoan.getNhanVien();
        }
        catch(NoResultException e){
            return null;
        }
      
    }

    public static <T> boolean checkUnique(Class<T> clazz, String idField, Object id, String field, Object value){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<T> cr = cb.createQuery(clazz);
        Root<T> root = cr.from(clazz);
        
        Predicate p1 = cb.equal(root.get(field), value);
        if(id == null)
            cr.select(root).where(p1);
        else {
            Predicate p2 = cb.notEqual(root.get(idField), id);
            cr.select(root).where(cb.and(p1,p2));
        }
            
        Query<T> query = session.createQuery(cr);
        Object object;
        try{
            object = query.getSingleResult();
            return false;
        }
        catch(NoResultException e){
            return true;
        }
    }
    
    public static <T> boolean checkUnique(Class<T> clazz, String field, Object value){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cr = cb.createQuery(clazz);
        Root<T> root = cr.from(clazz);
        Predicate p1 = cb.equal(root.get(field), value);
        cr.select(root).where(p1);
        Query<T> query = session.createQuery(cr);
        Object object;
        try{
            object = query.getSingleResult();
            return false;
        }
        catch(NoResultException e){
            return true;
        }
    }
    
    public static boolean checkTuyenDangHoatDong(ChiTietTuyen chiTietTuyen, Integer id){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<Tuyen> cr = cb.createQuery(Tuyen.class);
        Root<Tuyen> root = cr.from(Tuyen.class);
        
        Predicate p1 = cb.equal(root.get("chiTietTuyen"), chiTietTuyen);
        Predicate p2 = cb.isTrue(root.get("trangThaiHoatDong"));
        if(id != null){
            Predicate p0 = cb.notEqual(root.get("maTuyen"), id);
            cr.select(root).where(cb.and(p0,p1,p2));
            System.out.println("id not null");
        }
        else{
             cr.select(root).where(cb.and(p1,p2));
        }   
        
        Query<Tuyen> query = session.createQuery(cr);
        Object object;
        try{
            object = query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    public static boolean checkTuyenDaCo(ChiTietTuyen chiTietTuyen, LocalDate ngayHoatDong, Integer id){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<Tuyen> cr = cb.createQuery(Tuyen.class);
        Root<Tuyen> root = cr.from(Tuyen.class);
        
        Predicate p1 = cb.equal(root.get("chiTietTuyen"), chiTietTuyen);
        Predicate p2 = cb.equal(root.get("ngayHoatDong"), ngayHoatDong);
        if(id != null){
            Predicate p5 = cb.notEqual(root.get("maTuyen"), id);
            cr.select(root).where(cb.and(p1,p2,p5));
        }
        else{
             cr.select(root).where(cb.and(p1,p2));
        }   
        Query<Tuyen> query = session.createQuery(cr);
        Object object;
        try{
            object = query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    public static boolean checkGiaLoaiTuyen(String [] giaVe, Integer id){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<LoaiTuyen> cr = cb.createQuery(LoaiTuyen.class);
        Root<LoaiTuyen> root = cr.from(LoaiTuyen.class);
        
        BigDecimal bigDecimal1 = BigDecimal.valueOf(Double.parseDouble(giaVe[0]));
        BigDecimal bigDecimal2 = BigDecimal.valueOf(Double.parseDouble(giaVe[1]));
        BigDecimal bigDecimal3 = BigDecimal.valueOf(Double.parseDouble(giaVe[2]));
        
        Predicate p1 = cb.equal(root.get("giaVeThuong"), bigDecimal1);
        Predicate p2 = cb.equal(root.get("giaVeSinhVien"), bigDecimal2);
        Predicate p3 = cb.equal(root.get("giaVeTap"), bigDecimal3);
        if(id != null){
            Predicate p5 = cb.notEqual(root.get("maLoaiTuyen"), id);
            cr.select(root).where(cb.and(p1,p2,p5));
        }
        else{
             cr.select(root).where(cb.and(p1,p2));
        }   
        Query<LoaiTuyen> query = session.createQuery(cr);
        Object object;
        try{
            object = query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    public static boolean checkDaCoDoanhThuTrongNgay(LocalDate ngay, ChiTietTuyen chiTietTuyen){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<DoanhThu> cr = cb.createQuery(DoanhThu.class);
        Root<DoanhThu> root = cr.from(DoanhThu.class);
        
        Predicate p1 = cb.equal(root.get("id").get("ngay"), ngay);
        Predicate p2 = cb.equal(root.get("tuyen").get("chiTietTuyen"), chiTietTuyen);
        cr.select(root).where(cb.and(p1,p2));
        Query<DoanhThu> query = session.createQuery(cr);
        Object object;
        try{
            object = query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    public static List<Tuyen> listTuyen(DonViQuanLy donViQuanLy){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<Tuyen> cr = cb.createQuery(Tuyen.class);
        Root<Tuyen> root = cr.from(Tuyen.class);
        
        Predicate p1 = cb.isTrue(root.get("trangThaiHoatDong"));
        Predicate p2 = cb.equal(root.get("donViQuanLy"),donViQuanLy);
        if(donViQuanLy == null){
            cr.select(root).where(p1);
        }
        else 
            cr.select(root).where(p1,p2);
        
        Query<Tuyen> query = session.createQuery(cr);
        List<Tuyen> results = query.getResultList();
        return results;
    }
    
    public static boolean checkTuyenTrungLap(ChiTietTuyen chiTietTuyen, LocalDate thoiGianBatDau, LocalDate thoiGianKetThuc, Integer id){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<Tuyen> cr = cb.createQuery(Tuyen.class);
        Root<Tuyen> root = cr.from(Tuyen.class);
        
        Predicate p1 = cb.equal(root.get("chiTietTuyen"), chiTietTuyen);
        Predicate p2 = cb.greaterThanOrEqualTo(root.get("ngayKetThuc"), thoiGianBatDau);
        Predicate p3 = cb.lessThanOrEqualTo(root.get("ngayBatDau"), thoiGianKetThuc);
        
        
        if(id != null){
            Predicate p5 = cb.notEqual(root.get("maTuyen"), id);
            cr.select(root).where(cb.and(p1,p2,p3,p5));
        }
        else{
             cr.select(root).where(cb.and(p1,p2,p3));
        }   
        Query<Tuyen> query = session.createQuery(cr);
        Object object;
        try{
            object = query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    public static boolean checkLoaiTuyenDoanhThu(LoaiTuyen loaiTuyen){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.
        createQuery("select 1 "
                + "from DoanhThu dt inner join dt.tuyen t "
                + "where t.loaiTuyen = :key ");
        query.setParameter("key", loaiTuyen);
        return (query.uniqueResult() != null);
    }
    
    public static boolean checkBieuDoGioDoanhThu(BieuDoGio bieuDoGio){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.
        createQuery("select 1 "
                + "from DoanhThu dt inner join dt.tuyen t "
                + "where t.bieuDoGio = :key ");
        query.setParameter("key", bieuDoGio);
        return (query.uniqueResult() != null);
    }
    
    public static boolean checkBieuDoPhanCong(BieuDoGio bieuDoGio){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.
        createQuery("select 1 "
                + "from PhanCong pc inner join pc.tuyen t "
                + "where t.bieuDoGio = :key ");
        query.setParameter("key", bieuDoGio);
        return (query.uniqueResult() != null);
    }
    
    public static boolean checkDoanhThuPhanCong(Tuyen tuyen){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.
        createQuery("select 1 "
                + "from PhanCong pc "
                + "where pc.tuyen = :key ");
        query.setParameter("key", tuyen);
        return (query.uniqueResult() != null);
    }
    
    public static boolean checkLoaiXePhanCong(LoaiXe loaiXe){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.
        createQuery("select 1 "
                + "from PhanCong pc inner join pc.xe x "
                + "where x.loaiXe = :key ");
        query.setParameter("key", loaiXe);
        return (query.uniqueResult() != null);
    }
    
    public static boolean checkBieuDoUnique(LocalTime sTime, LocalTime eTime, short gianCach, short thoiGianChuyen, Integer id){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<BieuDoGio> cr = cb.createQuery(BieuDoGio.class);
        Root<BieuDoGio> root = cr.from(BieuDoGio.class);
        
        Predicate p1 = cb.equal(root.get("thoiGianBatDau"), sTime);
        Predicate p2 = cb.greaterThanOrEqualTo(root.get("thoiGianKetThuc"), eTime);
        Predicate p3 = cb.lessThanOrEqualTo(root.get("thoiGianChuyen"), thoiGianChuyen);
        Predicate p4 = cb.lessThanOrEqualTo(root.get("gianCachChuyen"), gianCach);
        
        if(id != null){
            Predicate p5 = cb.notEqual(root.get("maBieuDo"), id);
            cr.select(root).where(cb.and(p1,p2,p3,p5));
        }
        else{
             cr.select(root).where(cb.and(p1,p2,p3));
        }   
        Query<BieuDoGio> query = session.createQuery(cr);
        Object object;
        try{
            object = query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }

    
    
    public static void swapXe(Xe xeA, Xe xeB, Tuyen tuyen){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("exec swapXe :xeA, :xeB, :tuyen");
            query.setParameter("xeA", xeA.getMaXe());
            query.setParameter("xeB", xeB.getMaXe());
            query.setParameter("tuyen", tuyen.getMaTuyen());
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
         } finally {
            session.close(); 
         }
    }
    
    public static List<Tuyen> listTuyenCuaDonVi(DonViQuanLy donViQuanLy){
        factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        

        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaQuery<Tuyen> cr = cb.createQuery(Tuyen.class);
        Root<Tuyen> root = cr.from(Tuyen.class);
        
       
        
        cr.select(root).where(cb.equal(root.get("donViQuanLy"), donViQuanLy));
        Query<Tuyen> query = session.createQuery(cr);
        
        List<Tuyen> results = query.getResultList();
        return results;
    }
}
