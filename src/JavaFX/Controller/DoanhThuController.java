/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.ChiTietTuyen;
import Entity.DoanhThu;
import Entity.Tuyen;
import Hibernate.DataGetter;
import JavaFX.CustomNode.SearchDate;
import JavaFX.CustomNode.SearchPane;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author dokha
 */
public class DoanhThuController extends BasedFrameControllerr{

    private HashMap<Integer, XYChart.Data> doanhThuVeThuong = new HashMap<>();
    private HashMap<Integer, XYChart.Data> doanhThuVeSinhVien = new HashMap<>();
    private HashMap<Integer, XYChart.Data> doanThuVeTap = new HashMap<>();
    private BarChart<String,Number> bc;
    List<ChiTietTuyen> chiTietTuyens;
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();

    @Override
    public void sua(ActionEvent event) {
        super.sua(event); //To change body of generated methods, choose Tools | Templates.
        infoPane.getNode().get(0).setDisable(true);
        infoPane.getNode().get(1).setDisable(true);
    }
    
    
    
    @Override
    public boolean checkConstraint() {
        ComboBox cb1 = (ComboBox)infoPane.getNode().get(0);
        if(cb1.getItems().isEmpty()){
            showThongBao("Không thể thêm vì chưa có  tuyến");
            return false;
        }
        LocalDate ngay = (LocalDate)infoPane.getFieldVale(1);
        Tuyen tuyen = (Tuyen)infoPane.getFieldVale(0);
        LocalDate ngayBatDau = tuyen.getNgayBatDau();
        LocalDate ngayKetThuc = tuyen.getNgayKetThuc();
        ChiTietTuyen chiTietTuyen = tuyen.getChiTietTuyen();
        
        switch(mode){
            case 0:
                
                break;
            case 1:   
                if(!(!ngay.isBefore(ngayBatDau) && !ngay.isAfter(ngayKetThuc))){
                    showThongBao("phải trong thời gian hoạt động");
                    return false;
                }
                if(DataGetter.checkDaCoDoanhThuTrongNgay(ngay, chiTietTuyen)){
                    showThongBao("Tuyến đã có doanh thu trong ngày này");
                    return false;
                }
                if(!DataGetter.checkDoanhThuPhanCong(tuyen)){
                    showThongBao("Tuyến chưa có xe");
                    return false;
                }
                break;
            case 2:
                
                break;
            case 3:
                if(!(!ngay.isBefore(ngayBatDau) && !ngay.isAfter(ngayKetThuc))){
                    showThongBao("phải trong thời gian hoạt động");
                    return false;
                }
                if(DataGetter.checkDaCoDoanhThuTrongNgay(ngay, chiTietTuyen)){
                    showThongBao("Tuyến đã có doanh thu trong ngày này");
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void extraInital() {
        super.extraInital(); //To change body of generated methods, choose Tools | Templates.
        xoaButton.setDisable(true);
        tabPane.getTabs().remove(1);
        tablePane.getChildren().remove(table);
        TabPane tabPane = new TabPane();
        Tab tab = new Tab("table");
        tab.setContent(table);
        tabPane.getTabs().add(tab);
        tablePane.getChildren().add(0,tabPane);
        
        NumberAxis yAxis = new NumberAxis();
        CategoryAxis xAxis = new CategoryAxis();
        
        bc = new BarChart<String,Number>(xAxis, yAxis);
        bc.setAnimated(false);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Tuyến");  
        yAxis.setLabel("Value");
        
        series1.setName("Vé Thường");       
        series2.setName("Vé Sinh Viên");   
        series3.setName("Vé Tập");   
        
        bc.getData().addAll(series1,series2,series3);
        
        SearchDate searchDate = new SearchDate();
        SearchPane sp2 = new SearchPane();
        sp2.add(searchDate, 0, 0);
        sp2.getSearchNodes().add(searchDate);
        sp2.getFieldName().add("ngay:id");
        
        Button button = new Button("Tim");
        sp2.add(button, 1, 0);
        
        button.setOnAction((event) -> {
            updateGraph(DataGetter.listObject(DoanhThu.class, sp2));
        });
        updateGraph(DataGetter.listObject(DoanhThu.class, sp2));
        VBox vBox = new VBox(sp2,bc);
        Tab tab1 = new Tab("graph", vBox);
        tabPane.getTabs().add(tab1);
        VBox.setVgrow(tabPane, Priority.ALWAYS);
        tab1.setClosable(false);
        tab.setClosable(false);
        DatePicker dp = (DatePicker)infoPane.getNode().get(1);
        
        dp.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                
                setDisable(empty || date.compareTo(today) > 0 );
            }
        });
    }
    
    public void updateGraph(List<DoanhThu> list){
        
        HashMap<Integer, BigDecimal> doanhThuVeThuong = getDoanhThuVeThuong(list);
        HashMap<Integer, BigDecimal> doanhThuVeSinhVien = getDoanhThuVeSinhVien(list);
        HashMap<Integer, BigDecimal> doanhThuVetap = getDoanhThuVeTap(list);
        
        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();
        
        for(int i : doanhThuVeThuong.keySet()){
            int maSo = i;
            BigDecimal veThuong = doanhThuVeThuong.get(maSo);
            BigDecimal veSinhVien = doanhThuVeSinhVien.get(maSo);
            BigDecimal veTap = doanhThuVetap.get(maSo);
            series1.getData().add(new XYChart.Data("Tuyen " + maSo, veThuong));
            series2.getData().add(new XYChart.Data("Tuyen " + maSo, veSinhVien));
            series3.getData().add(new XYChart.Data("Tuyen " + maSo, veTap));
        }
    }
    
    public HashMap<Integer, BigDecimal> getDoanhThuVeSinhVien(List<DoanhThu> doanhThu){
        HashMap<Integer, BigDecimal> doanhThuCuaTuyen = new HashMap<Integer, BigDecimal>();
        for(DoanhThu dt : doanhThu){
            int maSo = dt.getTuyen().getChiTietTuyen().getMaSo();
            BigDecimal amount = dt.getTuyen().getLoaiTuyen().getGiaVeSinhVien().multiply(new BigDecimal(dt.getSoVeSinhVien()));
            if(doanhThuCuaTuyen.containsKey(maSo)){
                doanhThuCuaTuyen.put(maSo, doanhThuCuaTuyen.get(maSo).add(amount));
            }
            else
                doanhThuCuaTuyen.put(maSo, amount);
        }
        return doanhThuCuaTuyen;
    }
    
    public HashMap<Integer, BigDecimal> getDoanhThuVeThuong(List<DoanhThu> doanhThu){
        HashMap<Integer, BigDecimal> doanhThuCuaTuyen = new HashMap<Integer, BigDecimal>();
        for(DoanhThu dt : doanhThu){
            int maSo = dt.getTuyen().getChiTietTuyen().getMaSo();
            BigDecimal amount = dt.getTuyen().getLoaiTuyen().getGiaVeSinhVien().multiply(new BigDecimal(dt.getSoVeThuong()));
            if(doanhThuCuaTuyen.containsKey(maSo)){
                doanhThuCuaTuyen.put(maSo, doanhThuCuaTuyen.get(maSo).add(amount));
            }
            else
                doanhThuCuaTuyen.put(maSo, amount);
        }
        return doanhThuCuaTuyen;
    }
    
    public HashMap<Integer, BigDecimal> getDoanhThuVeTap(List<DoanhThu> doanhThu){
        HashMap<Integer, BigDecimal> doanhThuCuaTuyen = new HashMap<Integer, BigDecimal>();
        for(DoanhThu dt : doanhThu){
            int maSo = dt.getTuyen().getChiTietTuyen().getMaSo();
            BigDecimal amount = dt.getTuyen().getLoaiTuyen().getGiaVeSinhVien().multiply(new BigDecimal(dt.getSoVeTap()));
            if(doanhThuCuaTuyen.containsKey(maSo)){
                doanhThuCuaTuyen.put(maSo, doanhThuCuaTuyen.get(maSo).add(amount));
            }
            else
                doanhThuCuaTuyen.put(maSo, amount);
        }
        return doanhThuCuaTuyen;
    }
    
}
