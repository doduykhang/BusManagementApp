/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.ChiTietTuyen;
import Entity.LoTrinh;
import Entity.Tram;
import Hibernate.DataGetter;
import JavaFX.CustomNode.InfoPane;
import JavaFX.JavaFXUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

/**
 *
 * @author dokha
 */
public class ChiTietTuyenController extends BasedFrameControllerr{

    private GridPane tramPane;
    private TramController tramController;
    private TableView loTrinhTable;
    private ChiTietTuyen selectedTuyen;
    private ArrayList<Line> lines; 

    @Override
    public void xoa(ActionEvent event) {
        ChiTietTuyen chiTietTuyen = (ChiTietTuyen)table.getSelectionModel().getSelectedItem(); 
        System.out.println("khoang cach " + chiTietTuyen.getTotalDistance());
        if(DataGetter.checkForeignKey("Tuyen", "chiTietTuyen", chiTietTuyen)){
            showThongBao("Không thể xóa vì đang có tuyến với chi tiết tuyến này");
            return;
        }
        super.xoa(event); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public boolean checkConstraint() {
        
        switch(mode){
            case 0:
                break;
            case 1:    
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void extraInital() {
        lines = new ArrayList<>();
        
        
        //makeTramPane();
        //showTuyenPane();
        makeTramPane();
        Button button = new Button("Chỉnh sửa lô trình");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedTuyen = (ChiTietTuyen)table.getSelectionModel().getSelectedItem();
                if(selectedTuyen != null){
                    loTrinhTable.getItems().clear();
                    List<LoTrinh> loTrinhs = selectedTuyen.getTram();
                    for(LoTrinh loTrinh : loTrinhs)
                        loTrinhTable.getItems().add(loTrinh.getTram());
                    drawLines();
                    showTramPane();
                }
                    
               
            }
        });
        
        TableView table = JavaFXUtil.makeTable(Tram.class);
        table.itemsProperty().bind(loTrinhTable.itemsProperty());
        
        VBox vBox = new VBox(table,button);
        Tab tab = new Tab("Lộ Trình", vBox);
        
        tabPane.getTabs().add(tab);
    }

    @Override
    public void select(Object object, InfoPane infoPane) {
        super.select(object, infoPane); //To change body of generated methods, choose Tools | Templates.
        selectedTuyen = (ChiTietTuyen)table.getSelectionModel().getSelectedItem();
        if(selectedTuyen != null){
            loTrinhTable.getItems().clear();
            List<LoTrinh> loTrinhs = selectedTuyen.getTram();
            for(LoTrinh loTrinh : loTrinhs)
                loTrinhTable.getItems().add(loTrinh.getTram());
        }
    }
    
    
    
    public void drawLines(){
        for(Line line : lines){
            //tramPane.getChildren().remove(line);
            tramController.getMapPane().getChildren().remove(line);
        } 
        if(loTrinhTable.getItems().size() < 2)
            return;
        
            
        lines.clear();
        
        Line line;
        for(int i = 0;i < loTrinhTable.getItems().size()-1; i++){
           Tram tramA = (Tram)loTrinhTable.getItems().get(i);
           Tram tramB = (Tram)loTrinhTable.getItems().get(i+1);
           double x1 = tramA.getX() + 40;
           double y1 = tramA.getY() + 60;
           double x2 = tramB.getX() + 40;
           double y2 = tramB.getY() + 60;
          
           line = new Line(x1, y1, x2, y2);
           line.setStrokeWidth(3);
           tramController.getMapPane().getChildren().add(line);
           lines.add(line);
        }
        
    }
    
    public void makeTramPane(){
        FXMLLoader loader;
        loader = new FXMLLoader(ChiTietTuyenController.class.getResource("/JavaFX/FXML/TramFrame.fxml"));   
        tramController = new TramController(){
            public void addLoTrinh(Tram tram){
                ChiTietTuyen tuyen = selectedTuyen;
                for(LoTrinh loTrinh : tuyen.getTram()){
                    if(loTrinh.getTram().equals(tram))
                        return;
                }
                double x = tram.getX();
                double y = tram.getY();
                if(!tuyen.canUseTram(tram)){
                    showThongBao("Trạm cách quá xa");
                    return;
                }
                   
                loTrinhTable.getItems().add(tram);
                short stt = (short)(loTrinhTable.getItems().size() - 1);
                tuyen.addTram(tram, stt);
                //LoTrinh loTrinh = new LoTrinh(tram, tuyen, stt);
                //DataGetter.addObject(loTrinh);
                drawLines();
            }
            
            public void removeLoTrinh(Tram tram){
                ChiTietTuyen tuyen = selectedTuyen;
                boolean end = false;
                int n = loTrinhTable.getItems().size();
                int j = n;
                
                for(int i = 0; i < n; i++){
                    Tram t = (Tram)loTrinhTable.getItems().get(i);
                    if(t.equals(tram)){
                        j = i;
                        break;
                    }
                }
                    
                
                for(int i = n - 1; i >= j; i--){
                    Tram sub = (Tram)loTrinhTable.getItems().get(i);
                    tuyen.removeTram(sub);
                    loTrinhTable.getItems().remove(i);
                }
                tram.removeTuyen(tuyen);
                drawLines();
            }
            
        };
        
        loader.setController(tramController);

        try {
            tramPane = loader.load();
            tramPane.setMaxWidth(Double.MAX_VALUE);
            tramPane.setMaxHeight(Double.MAX_VALUE);
            HBox.setHgrow(tramPane, Priority.ALWAYS);
            tramController.initial(Tram.class, "Tram");
            tramController.extraInital();
            tramController.showLoTrinhOption();
            HBox mainFrame = (HBox)mainGrid.getParent();
            mainFrame.getChildren().add(tramPane);
            Button button = new Button("test");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    showTuyenPane();
                }
            });
            tramController.getInfoPane().add(button, 0, 12);
            
            loTrinhTable = JavaFXUtil.makeTable(Tram.class);
            tramController.getTablePane().getChildren().add(loTrinhTable);
            
            
            tramPane.setVisible(false);
            tramPane.setManaged(false);
        } catch (IOException ex) {
            Logger.getLogger(ChiTietTuyenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showTuyenPane(){
        mainGrid.setVisible(true);
        mainGrid.setManaged(true);
        tramPane.setVisible(false);
        tramPane.setManaged(false);
    }
    
    private void showTramPane(){
        mainGrid.setVisible(false);
        mainGrid.setManaged(false);
        tramPane.setVisible(true);
        tramPane.setManaged(true);
    }
    
    public void test(){
        
    }
}
