/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

/**
 *
 * @author dokha
 */ 
import Entity.BieuDoGio;
import Entity.ChiTietTuyen;
import Entity.DoanhThu;
import Entity.DonViQuanLy;
import Entity.LoTrinh;
import Entity.LoaiTuyen;
import Entity.LoaiXe;
import Entity.NhaSanXuat;
import Entity.NhanVien;
import Entity.PhanCong;
import Entity.TaiKhoan;
import Entity.TinhTrang;
import Entity.Tram;
import Entity.Tuyen;
import Entity.Xe;
import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {

            Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("hibernate.cfg.xml"));
            
            configuration.addAnnotatedClass(LoaiXe.class);
            configuration.addAnnotatedClass(NhaSanXuat.class);
            configuration.addAnnotatedClass(Xe.class);
            configuration.addAnnotatedClass(TinhTrang.class);
            configuration.addAnnotatedClass(DonViQuanLy.class);
            configuration.addAnnotatedClass(LoaiTuyen.class);
            configuration.addAnnotatedClass(ChiTietTuyen.class);
            configuration.addAnnotatedClass(BieuDoGio.class);
            configuration.addAnnotatedClass(Tuyen.class);
            configuration.addAnnotatedClass(TaiKhoan.class);
            configuration.addAnnotatedClass(NhanVien.class);
            configuration.addAnnotatedClass(Tram.class);
            configuration.addAnnotatedClass(LoTrinh.class);
            configuration.addAnnotatedClass(DoanhThu.class);
            configuration.addAnnotatedClass(PhanCong.class);
            StandardServiceRegistryBuilder builder 
                = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            StandardServiceRegistry serviceRegistry = builder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return sessionFactory;
    }
}
