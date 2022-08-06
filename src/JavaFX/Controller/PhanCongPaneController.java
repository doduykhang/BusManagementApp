/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.DonViQuanLy;
import Entity.PhanCong;
import Entity.PhanCongTable;
import Entity.Tuyen;
import Entity.Xe;
import Hibernate.DataGetter;
import JavaFX.CustomNode.SearchComboBox;
import JavaFX.CustomNode.SearchPane;
import JavaFX.JavaFXUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * FXML Controller class
 *
 * @author dokha
 */
public class PhanCongPaneController implements Initializable {

    @FXML
    private TableView<Tuyen> tuyenTable;
    @FXML
    private TableColumn<Tuyen, String> tuyenColumn;
    @FXML
    private TableView<PhanCongTable> bieuDoGioTable;
    @FXML
    private TableColumn<PhanCongTable, String> gioColumn;
    @FXML
    private TableColumn<PhanCongTable, String> xeAColumn;
    @FXML
    private TableColumn<PhanCongTable, String> xeBColumn;
    @FXML
    private TableView<Xe> xeTable;
    @FXML
    private TableColumn<Xe, String> maXeColumn;
    @FXML
    private Button button;
    @FXML
    private Label thongBao;
    
    private int chuyen;
    private boolean chieu;
    private Xe xeA;
    private Xe xeB;
    private boolean editable = true;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        tuyenColumn.setCellValueFactory(new PropertyValueFactory<>("maTuyen"));
        tuyenColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tuyen, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tuyen, String> param) {
                
                return new SimpleStringProperty((String)param.getValue().getDisplayValue());
            }
        });
        maXeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        gioColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGian"));
        xeAColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PhanCongTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PhanCongTable, String> param) {
                if(param.getValue().getXeA() != null){
                    return new SimpleStringProperty(param.getValue().getXeA().getBienSoXe());
                }
                    
                return new SimpleStringProperty("");
            }
        });
        xeBColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PhanCongTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PhanCongTable, String> param) {
                if(param.getValue().getXeB() != null)
                    return new SimpleStringProperty(param.getValue().getXeB().getBienSoXe());
                return new SimpleStringProperty("");
            }
        });
        
        tuyenTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(tuyenTable.getSelectionModel().getSelectedIndex() == -1)
                    return;
                if(DataGetter.checkForeignKey("DoanhThu", "tuyen", tuyenTable.getSelectionModel().getSelectedItem())){
                     editable = false;
                     thongBao.setVisible(false);
                     thongBao.setText("Không thể điểu chỉnh vì đã có trong doanh thu");
                }
                   
                else{
                    editable = true;
                    thongBao.setVisible(false);
                }
                    
                
                
                
                updatePhanCong();
            }
        });
        
        xeTable.setOnDragDetected(dragDetectFromXe());
        xeTable.setOnDragOver(dragOver());
        xeTable.setOnDragDropped(dragDropToXe());
        xeAColumn.setCellFactory((param) -> {
            return setTableCell(param); //To change body of generated lambdas, choose Tools | Templates.
        });
        xeBColumn.setCellFactory((param) -> {
            return setTableCell(param); //To change body of generated lambdas, choose Tools | Templates.
        });
        bieuDoGioTable.getSelectionModel().setCellSelectionEnabled(true);
    }    

    public void getData(DonViQuanLy donViQuanLy){
        tuyenTable.getItems().addAll(DataGetter.listObject("Tuyen"));
        xeTable.getItems().addAll(DataGetter.listObject("Xe"));
    }
    
    @FXML
    private void chonTuyen(MouseEvent event) {
    }

    @FXML
    private void test(MouseEvent event) {
    }
    
    public EventHandler <MouseEvent> dragDetect(TableCell<PhanCongTable, String> cell){
        return new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                boolean chieu = cell.getTableColumn().equals(xeAColumn);
                PhanCongTable phanCong = (PhanCongTable)cell.getTableRow().getItem();
                xeA = (chieu) ? phanCong.getXeA():phanCong.getXeB();
                if(xeA != null){
                    Dragboard db = cell.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString("test");
                    db.setContent(content);
                }
                event.consume();
            }
        };
    }
    
    public EventHandler <MouseEvent> dragDetectFromXe(){
        return new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                xeA = xeTable.getSelectionModel().getSelectedItem();
                
                Dragboard db = xeTable.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString("test");
                db.setContent(content);
                
                
                event.consume();
            }
        };
    }
    
    public EventHandler <DragEvent> dragDropToPhanCong(TableCell<PhanCongTable, String> cell){
        return new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                if(editable){
                    Dragboard db = event.getDragboard();
                    boolean success = true;
                    boolean chieu = cell.getTableColumn().equals(xeAColumn);
                    PhanCongTable phanCong = (PhanCongTable)cell.getTableRow().getItem();
                    Tuyen tuyen = tuyenTable.getSelectionModel().getSelectedItem();

                    System.out.println("dragDropToPhanCong");
                    xeB = (chieu) ? phanCong.getXeA():phanCong.getXeB();
                    if(xeB != null){
                        if(event.getGestureSource() != xeTable)
                            tuyen.swapXe(xeA, xeB);
                    }
                    else{
                        if(!xeA.checkOverlapTime(tuyen) && event.getGestureSource() == xeTable){
                            thongBao.setText("Trùng thời gian với tuyến khác");
                            thongBao.setVisible(true);
                            return;
                        }
                        int soXe = JavaFXUtil.getSoXe(tuyen);
                        int x = cell.getTableRow().getIndex()/(soXe/2);
                        chieu = (x % 2 == 0) ? chieu : !chieu;
                        tuyen.addXe(xeA, (short)phanCong.getChuyen(), chieu);
                    } 

                    updatePhanCong();

                    event.setDropCompleted(success);
                    event.consume();
                }
                
            }
        };
    }
    
    public EventHandler <DragEvent> dragDropToXe(){
        return new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("dragDropToXe");
                if(event.getGestureSource() != xeTable){
                    Dragboard db = event.getDragboard();
                    boolean success = true;
                    Tuyen tuyen = tuyenTable.getSelectionModel().getSelectedItem();
                    tuyen.removeXe(xeA);
                    updatePhanCong();
                    event.setDropCompleted(success);
                    event.consume();
                }
            }
        };
    }
    
    public EventHandler <DragEvent> dragOver(){
        return new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                event.consume();
            }
        };
    }
    
    public EventHandler <DragEvent> dragDone(TableCell<PhanCongTable, String> cell){
        return new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                event.consume();
            }
        };
    }
    
    public TableCell<PhanCongTable, String> setTableCell(TableColumn<PhanCongTable, String> param){
        TableCell<PhanCongTable, String> cell = new TableCell<PhanCongTable, String>() {
            
            
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty) ;
                if (empty) {
                    setText(null);
                } else {
//                    PhanCongTable phanCong = (PhanCongTable)getTableRow().getItem();
//                    boolean chieu = getTableColumn().equals(xeAColumn);
//                    try{
//                        Xe x = (chieu) ? phanCong.getXeA() : phanCong.getXeB();
//                        if(x != null){
//                            if(selectedXe != null && x.equals(selectedXe))
//                                setStyle("-fx-text-fill : red");
//                            else 
//                                setStyle("-fx-text-fill : black");
//                        }
//                    }catch(NullPointerException e){
//                        setStyle("-fx-text-fill : black");
//                    }
//                    finally{
                        setText(item.toString());
//                    }
                }
            }
        };
        cell.setOnMouseClicked((event) -> {
//            PhanCongTable phanCong = (PhanCongTable)cell.getTableRow().getItem();
//            boolean chieu = cell.getTableColumn().equals(xeAColumn);
//            //selectedXe = (chieu) ? phanCong.getXeA() : phanCong.getXeB();
//            bieuDoGioTable.refresh();
            thongBao.setVisible(true);
        });
        
        
        cell.setOnDragDetected(dragDetect(cell));
        cell.setOnDragOver(dragOver());
        cell.setOnDragDropped(dragDropToPhanCong(cell));
        cell.setOnDragDone(dragDone(cell));
        return cell ;
    }
    
    public void updatePhanCong(){
        bieuDoGioTable.getItems().clear();
        bieuDoGioTable.getItems().addAll(JavaFXUtil.makeBieuDoGio(tuyenTable.getSelectionModel().getSelectedItem()));
        bieuDoGioTable.refresh();
    }

    
}
