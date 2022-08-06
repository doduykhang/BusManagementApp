/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Controller.DangNhapPaneController;
import Entity.BieuDoGio;
import Entity.ChiTietTuyen;
import Entity.DoanhThu;
import Entity.DonViQuanLy;
import Entity.LoaiTuyen;
import Entity.LoaiXe;
import Entity.NhaSanXuat;
import Entity.NhanVien;
import Entity.Tram;
import Entity.Tuyen;
import Entity.Xe;
import bus2.Bus2;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author dokha
 */
public class HomeController implements Initializable {

    @FXML
    private HBox mainFrame;
    @FXML
    private VBox sideBar;
    @FXML
    private VBox sideBarL;
    @FXML
    private Button dangXuatButton;
    
    private DangNhapPaneController dangNhapPaneController;
    private VBox dangNhapPane;
    private NhanVien nhanVien = null;
    
    private ArrayList<Button> buttons = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    public void removePane(){
        for(int i = mainFrame.getChildren().size() - 1; i >= 1; i--){
            mainFrame.getChildren().remove(i);
        }
    }
    
    public void makeColor(Button button){
        for(Button b : buttons){
            if(b.equals(button)){
                button.setStyle("-fx-background-color : #86cecb;"
                            + "-fx-border-color: #137a7f;"
                            + "-fx-font: 15 ariel;"
                            + "-fx-text-fill: white");
            }
                
            else {
                b.setStyle("-fx-background-color : #137a7f;"
                            + "-fx-border-color : #137a7f;"
                            + "-fx-font: 15 ariel;"
                            + "-fx-text-fill: white");
            }
                
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dangXuatButton.setOnAction((event) -> {
             sideBarL.setVisible(false);
        sideBarL.setManaged(false);
            makeDangNhapPane();
        });
        sideBarL.setVisible(false);
        sideBarL.setManaged(false);
        setUpAdmin();
        setUpNhanVien();
        makeDangNhapPane();
    }    
    
    public void makeDangNhapPane(){
        removePane();
        sideBar.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(Bus2.class.getResource("/JavaFX/FXML/DangNhapPane.fxml"));
        try {
            dangNhapPaneController = new DangNhapPaneController(){
                @Override
                public NhanVien danhNhap() {
                    nhanVien = super.danhNhap(); //To change body of generated methods, choose Tools | Templates.
                    if(nhanVien == null){
                        getErrorLabel().setText("tai khoan hoac mat khau sai");
                        getErrorLabel().setVisible(true);
                        return nhanVien;
                    }
                    if(nhanVien.getDonViQuanLy() == null){
                        removePane();
                        setUpAdmin();
                        sideBarL.setVisible(true);
                        sideBarL.setManaged(true);
                    }
                    else{
                        removePane();
                        setUpNhanVien();
                        sideBarL.setVisible(true);
                        sideBarL.setManaged(true);
                    }
                    
                    return nhanVien;
                }
                
            };
            loader.setController(dangNhapPaneController);
            dangNhapPane = loader.load();
            dangNhapPane.setMaxWidth(Double.MAX_VALUE);
            dangNhapPane.setMaxHeight(Double.MAX_VALUE);
            HBox.setHgrow(dangNhapPane, Priority.ALWAYS);
            VBox.setVgrow(dangNhapPane, Priority.ALWAYS);
            mainFrame.getChildren().add(dangNhapPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUpNhanVien(){
        Button button2 = new Button("Doanh Thu");
        button2.setOnAction((event) ->{
            FXMLLoader loader = new FXMLLoader(Bus2.class.getResource("/JavaFX/FXML/BasicFrame.fxml"));
            GridPane pane;
            try {
                DoanhThuNhanVien doanhThuNhanVien = new DoanhThuNhanVien();
                loader.setController(doanhThuNhanVien);
                pane = loader.load();
                pane.setMaxWidth(Double.MAX_VALUE);
                pane.setMaxHeight(Double.MAX_VALUE);
                HBox.setHgrow(pane, Priority.ALWAYS);
                //((PhanCongPaneController)loader.getController()).getData(nhanVien.getDonViQuanLy());
                doanhThuNhanVien.setDonVi(nhanVien);
                doanhThuNhanVien.initial(DoanhThu.class, "Doanh Thu");
                doanhThuNhanVien.extraInital();
                removePane();
                mainFrame.getChildren().add(pane);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        button2.setStyle("-fx-background-color : #86cecb;"
                            + "-fx-border-color: #137a7f;"
                            + "-fx-font: 15 ariel;"
                            + "-fx-text-fill: white");
        button2.setMaxWidth(150);
        button2.setMaxHeight(50);
        HBox.setHgrow(button2, Priority.ALWAYS);
        VBox.setVgrow(button2, Priority.ALWAYS);
        sideBar.getChildren().add(button2);
    }
    
    public void setUpAdmin(){
        Class [] clazz = { 
                        Xe.class,
                        LoaiXe.class,
                        NhaSanXuat.class,
                        DonViQuanLy.class,
                        LoaiTuyen.class,
                        ChiTietTuyen.class,
                        BieuDoGio.class,
                        Tuyen.class,
                        NhanVien.class,
                        Tram.class,
                        DoanhThu.class
                        };
        String [] names = {
                            "Xe",
                            "Loại Xe",
                            "Nhà Sản Xuất",
                            "Đơn Vị Quản Lý",
                            "Loại Tuyến",
                            "Chi Tiết Tuyến",
                            "Biểu Đồ Giờ",
                            "Tuyến",
                            "Nhân Viên",
                            "Trạm",
                            "Doanh Thu"
                            };
        BasedFrameControllerr [] controllerrs = {
                                                new XeController(),
                                                new LoaiXeController(),
                                                new NhaSanXuatController(),
                                                new DonViQuanLyController(),
                                                new LoaiTuyenController(),
                                                new ChiTietTuyenController(),
                                                new BieuDoGioConTroller(),
                                                new TuyenController(),
                                                new NhanVienController(),
                                                new TramController(),
                                                new DoanhThuController()
                                                };
        int i;
        for(i = 0; i < clazz.length; i++){
            Button button = new Button(names[i]);
             button.setStyle("-fx-background-color : #137a7f;"
                            + "-fx-border-color: #137a7f;"
                            + "-fx-font: 15 ariel;"
                            + "-fx-text-fill: white");
            button.setMaxWidth(150);
            button.setMaxHeight(100);
            HBox.setHgrow(button, Priority.ALWAYS);
            VBox.setVgrow(button, Priority.ALWAYS);
            Class c = clazz[i];
            String name = names[i];
            BasedFrameControllerr controllerr = controllerrs[i];
            
            button.setOnAction((event) -> {
                makeColor(button);
                FXMLLoader loader;
                if(c.getSimpleName().equals("Tram"))
                    loader = new FXMLLoader(Bus2.class.getResource("/JavaFX/FXML/TramFrame.fxml"));
                else 
                    loader = new FXMLLoader(Bus2.class.getResource("/JavaFX/FXML/BasicFrame.fxml"));
                try {
                    //BasicFrameController controller = new BasicFrameController();
                    // Set it in the FXMLLoader
                    loader.setController(controllerr);
                    GridPane pane = loader.load();
                    pane.setMaxWidth(Double.MAX_VALUE);
                    pane.setMaxHeight(Double.MAX_VALUE);
                    HBox.setHgrow(pane, Priority.ALWAYS);
                    removePane();
                    mainFrame.getChildren().add(pane);
                    controllerr.initial(c,name);
                    controllerr.extraInital();
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sideBar.getChildren().add(button);
            buttons.add(button);
        }
        
        //Phan Cong
        Button button = new Button("Phân công");
        button.setStyle("-fx-background-color : #137a7f;"
                            + "-fx-border-color: #137a7f;"
                            + "-fx-font: 15 ariel;"
                            + "-fx-text-fill: white");
            button.setMaxWidth(150);
            button.setMaxHeight(100);
            HBox.setHgrow(button, Priority.ALWAYS);
            VBox.setVgrow(button, Priority.ALWAYS);
        button.setOnAction((event) ->{
            
            makeColor(button);
            FXMLLoader loader = new FXMLLoader(Bus2.class.getResource("/JavaFX/FXML/PhanCongPane.fxml"));
            GridPane pane;
            try {
                pane = loader.load();
                
                ((PhanCongPaneController)loader.getController()).getData(null);
                pane.setMaxWidth(Double.MAX_VALUE);
                pane.setMaxHeight(Double.MAX_VALUE);
                HBox.setHgrow(pane, Priority.ALWAYS);
                removePane();
                mainFrame.getChildren().add(pane);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        buttons.add(button);
        sideBar.getChildren().add(button);
    }
    
}
