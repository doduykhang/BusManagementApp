/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import Entity.ChiTietTuyen;
import Entity.PhanCong;
import Entity.PhanCongTable;
import Entity.Tuyen;
import Entity.Xe;
import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.CustomRadioGroup;
import JavaFX.CustomNode.InfoPane;
import JavaFX.CustomNode.SearchField;
import JavaFX.CustomNode.SearchNode;
import JavaFX.CustomNode.SearchNum;
import JavaFX.CustomNode.SearchPane;
import JavaFX.CustomNode.SearchText;
import JavaFX.CustomNode.SearchTime;
import JavaFX.CustomNode.TimePicker;
import JavaFX.Filter.Filters;
import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author dokha
 */
public class JavaFXUtil {
    public static TableView<Object> makeTable(Class<?> clazz){
        TableView<Object> tableView = new TableView<Object>();
        HBox.setHgrow(tableView, Priority.ALWAYS);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(TableViewColumn.class)) {
                TableViewColumn annotation = field.getAnnotation(TableViewColumn.class);
                TableColumn<Object, String> column = new TableColumn<>(annotation.columnName());
                column.setCellValueFactory(annotation.cellValueFactorys().test(field.getName()));
                tableView.getColumns().add(column);
            }
        }	
        return  tableView;
    }
    
    public static InfoPane makeRightBar(Class<?> clazz){
        InfoPane infoPane = new InfoPane();
        int r = 0;
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            
            if (field.isAnnotationPresent(InputField.class)) {
                InputField annotation = field.getAnnotation(InputField.class);
                
                Node newNode = annotation.nodes().getNode(field);
                infoPane.add(new Label(annotation.fieldName()), 0, r);
                infoPane.add(newNode, 1, r);
                
                infoPane.getFieldName().add(field.getName());
                infoPane.getNodes().add(annotation.nodes());
                infoPane.getNode().add(newNode);
                
                if(newNode instanceof CustomRadioGroup){
                    
                    ((CustomRadioGroup) newNode).getR1().setText(annotation.trueRadio());
                    ((CustomRadioGroup) newNode).getR2().setText(annotation.falseRadio());
                }
                
                //errorLabel===========
                Label errorLabel = new Label();
                errorLabel.setStyle("-fx-text-fill: red");
                if(annotation.filters() != Filters.None){
                    if(annotation.nodes() == Nodes.TEXTFILED){
                        TextField textField = (TextField)newNode;
                        textField.setTextFormatter(new TextFormatter<String>(annotation.filters().test(errorLabel, annotation.limit(), true)));
                    }
                    
                    if(annotation.nodes() == Nodes.TEXTAREA){
                        TextArea textArea = (TextArea)newNode;
                        textArea.setTextFormatter(new TextFormatter<String>(annotation.filters().test(errorLabel, annotation.limit(), true)));
                    }
                    
                }
                infoPane.getErrorLabels().add(errorLabel);
                errorLabel.setVisible(false);
                //Constraint
                infoPane.getConstraints().add(field.getAnnotation(Constraint.class));
//                if(field.isAnnotationPresent(Constraint.class)){
//                    System.out.println("has Constraint.class");
//                    
//                }
//                else
//                    infoPane.getConstraints().add();
                infoPane.add(errorLabel, 1, r + 1);

                r += 2;
            }
        }	
        
        return infoPane;
    }
    
    public static SearchPane makeSearchPane(Class<?> clazz){
        SearchPane searchPane = new SearchPane();
        int r = 0;
        int c = 0;
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            
            if (field.isAnnotationPresent(SearchField.class)) {
                SearchField annotation = field.getAnnotation(SearchField.class);
                SearchNode node = annotation.searchNodes().getSearchNode(field);
                searchPane.add(new Label(annotation.name()), c+0, r);
                searchPane.add(node, c+1, r);
                String name = field.getName() + ":" +annotation.embeded();
                searchPane.getFieldName().add(name);
                searchPane.getSearchNodes().add(node);
                
                
                
                Label errorLabel = new Label();
                if(field.isAnnotationPresent(InputField.class)){
                    InputField input = field.getAnnotation(InputField.class);
                    if(node instanceof CustomRadioGroup){
                        ((CustomRadioGroup) node).getR1().setText(input.trueRadio());
                        ((CustomRadioGroup) node).getR2().setText(input.falseRadio());
                    }
                    if(input.filters() != Filters.None){
                        if(node instanceof SearchNum){
                            ((SearchNum) node).getN1().setTextFormatter(new TextFormatter<String>(input.filters().test(errorLabel, input.limit(), false)));
                            ((SearchNum) node).getN2().setTextFormatter(new TextFormatter<String>(input.filters().test(errorLabel, input.limit(), false)));
                        }

                        if(node instanceof SearchTime){
                            ((SearchTime) node).getTp1().setFilter(errorLabel);
                            ((SearchTime) node).getTp2().setFilter(errorLabel);
                        }

                        errorLabel.setStyle("");
                    }
                }
                    
                searchPane.add(errorLabel, c + 1, r + 1);
                
                r += 2;
                if(r % 6 == 0){
                    r = 0;
                    c += 2;
                }
                    
            }
        }	
        
        return searchPane;
    }
    
    public static ArrayList<PhanCongTable> makeBieuDoGio(Tuyen tuyen){
        
        ArrayList<PhanCongTable> bieuDoGio =  new ArrayList<PhanCongTable>();
        LocalTime batDau = LocalTime.of(tuyen.getBieuDoGio().getThoiGianBatDau().getHour(), tuyen.getBieuDoGio().getThoiGianBatDau().getMinute());
        LocalTime ketThuc = LocalTime.of(tuyen.getBieuDoGio().getThoiGianKetThuc().getHour(), tuyen.getBieuDoGio().getThoiGianKetThuc().getMinute());
        short thoiGian = tuyen.getBieuDoGio().getGianCachChuyen();
        PhanCongTable phanCongTable;
        
        
        int soXe = getSoXe(tuyen)/2;
        int j = 0;
        
        while (batDau.compareTo(ketThuc) <= 0){
            phanCongTable = new PhanCongTable(batDau, null, null,j);
            bieuDoGio.add(phanCongTable);
            batDau = batDau.plusMinutes(thoiGian);
            j = (j < soXe-1) ? j+1:0;
        }
        int chuyen = 0;
        boolean chieu = true;
        Xe xe = null;
        for(PhanCong phanCong : tuyen.getXe()){
            chuyen = phanCong.getChuyen();
            chieu = phanCong.isChieu();
            xe = phanCong.getXe();
            for(int i = chuyen; i < bieuDoGio.size(); i += soXe){
                if(chieu){
                    bieuDoGio.get(i).setXeA(xe);
                    chieu = false;
                }
                else{
                    bieuDoGio.get(i).setXeB(xe);
                    chieu = true;
                }
            }
        }
        return bieuDoGio;
    }
    
    public static int getSoXe(Tuyen tuyen){
        double thoiGianChuyen = (double)tuyen.getBieuDoGio().getThoiGianChuyen();
        double gianCach = (double)tuyen.getBieuDoGio().getGianCachChuyen();
        return (int)Math.ceil(thoiGianChuyen/gianCach)*2;
    }
}
