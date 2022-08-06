/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus2;
import Entity.ChiTietTuyen;
import Entity.DoanhThu;
import Hibernate.DataGetter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class BarChartSample extends Application {
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
 
    public HashMap<Integer, BigDecimal> getDoanhThuVeSinhVien(List<DoanhThu> doanhThu){
        HashMap<Integer, BigDecimal> doanhThuCuaTuyen = new HashMap<Integer, BigDecimal>();
        for(DoanhThu dt : doanhThu){
            int maSo = dt.getTuyen().getChiTietTuyen().getMaSo();
            BigDecimal amount = dt.getTuyen().getLoaiTuyen().getGiaVeSinhVien().multiply(new BigDecimal(dt.getSoVeSinhVien()));
            System.out.println("maSo : " + maSo);
            System.out.println("amount : " + amount);
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
            System.out.println("maSo : " + maSo);
            System.out.println("amount : " + amount);
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
            System.out.println("maSo : " + maSo);
            System.out.println("amount : " + amount);
            if(doanhThuCuaTuyen.containsKey(maSo)){
                doanhThuCuaTuyen.put(maSo, doanhThuCuaTuyen.get(maSo).add(amount));
            }
            else
                doanhThuCuaTuyen.put(maSo, amount);
        }
        return doanhThuCuaTuyen;
    }
    
    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Country");       
        yAxis.setLabel("Value");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");       
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));
        series1.getData().add(new XYChart.Data(france, 10000));
        series1.getData().add(new XYChart.Data(italy, 35407.15));
        series1.getData().add(new XYChart.Data(usa, 12000));      
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(austria, 57401.85));
        series2.getData().add(new XYChart.Data(brazil, 41941.19));
        series2.getData().add(new XYChart.Data(france, 45263.37));
        series2.getData().add(new XYChart.Data(italy, 117320.16));
        series2.getData().add(new XYChart.Data(usa, 14845.27));  
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(austria, 45000.65));
        series3.getData().add(new XYChart.Data(brazil, 44835.76));
        series3.getData().add(new XYChart.Data(france, 18722.18));
        series3.getData().add(new XYChart.Data(italy, 17557.31));
        series3.getData().add(new XYChart.Data(usa, 92633.68));  
        
        Button button = new Button("tang");
        TextField c = new TextField();
        TextField v = new TextField();
        VBox vbox = new VBox(button,c,v,bc);
        button.setOnAction((event) -> {
            series3.getData().add(new XYChart.Data(c.getText(), Integer.parseInt(v.getText())));  
        });
        Scene scene  = new Scene(vbox,800,600);
        bc.getData().addAll(series1,series2,series3);
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
