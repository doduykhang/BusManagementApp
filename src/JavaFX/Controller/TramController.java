/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.ChiTietTuyen;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Entity.Tram;
import Hibernate.DataGetter;
import JavaFX.CustomNode.TramButton;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
/**
 *
 * @author dokha
 */
public class TramController extends BasedFrameControllerr{
    private ScrollPane scrollPane;
    private double oX;
    private double oY;
    private double deltaX;
    private double deltaY;
    private AnchorPane mapPane;
    private RadioButton addMode;
    private RadioButton scrollMode;
    private RadioButton addLoTrinh;
    private RadioButton removeLoTrinh;
    private Image img;
    private boolean drag;
    
    public boolean coTheDatTram(int x, int y){
        Color color = img.getPixelReader().getColor(x, y);
        int red = (int)(color.getRed()*255);
        int blue = (int)(color.getBlue()*255);
        int green = (int)(color.getGreen()*255);
        double distanceToWhite = Math.sqrt(Math.pow(255-red, 2) + Math.pow(255-blue, 2) + Math.pow(255-green, 2));
        double distanceToPink = Math.sqrt(Math.pow(213-red, 2) + Math.pow(154-blue, 2) + Math.pow(136-green, 2));
        if(distanceToWhite > 50 && distanceToPink > 70)
            return false;
        return true;
    }
    
    public AnchorPane getMapPane() {
        return mapPane;
    }

    public void setMapPane(AnchorPane mapPane) {
        this.mapPane = mapPane;
    }

    @Override
    public void xoa(ActionEvent event) {
        Tram tram = (Tram)table.getSelectionModel().getSelectedItem(); 
        if(DataGetter.checkForeignKey("LoTrinh", "tram", tram)){
            showThongBao("Không thể xóa vì đang có tuyến với trạm này");
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
        TextField textField = (TextField) infoPane.getNode().get(2);
        textField.setVisible(false);
        textField = (TextField) infoPane.getNode().get(3);
        textField.setVisible(false);
        themButton.setVisible(false);
        setUpMap();
        setUpRadioButton();
        
    }
    
    @Override
    public void afterAdd(Object object){
        Tram tram = (Tram)object;
        themTramMap(tram);
    }
    
    @Override
    public void afterDelete(Object object){
        Tram tram = (Tram)object;
        for(Node node : mapPane.getChildren()){
            if(node.getClass().getSimpleName().equals("TramButton")){
                TramButton tramButton = (TramButton) node;
                if(tramButton.getTram().getMaTram() == tram.getMaTram()){
                    mapPane.getChildren().remove(tramButton);
                    return;
                }
            }
        }
    }
    
    public void setUpRadioButton(){
        scrollMode = new RadioButton();
        addMode = new RadioButton("thêm trạm");
        addLoTrinh = new RadioButton("thêm lộ trình");
        removeLoTrinh = new RadioButton("xóa lộ trình");
        
        ToggleGroup toggleGroup = new ToggleGroup();
        scrollMode.setToggleGroup(toggleGroup);
        addMode.setToggleGroup(toggleGroup);
        addLoTrinh.setToggleGroup(toggleGroup);
        removeLoTrinh.setToggleGroup(toggleGroup);
        
        Image image = new Image(getClass().getResourceAsStream("/JavaFX/Icon/4arrow.png"));
        scrollMode.setGraphic(new ImageView(image));
        image = new Image(getClass().getResourceAsStream("/JavaFX/Icon/pin16.png"));
        addMode.setGraphic(new ImageView(image));
        image = new Image(getClass().getResourceAsStream("/JavaFX/Icon/xMark.png"));
        removeLoTrinh.setGraphic(new ImageView(image));
        image = new Image(getClass().getResourceAsStream("/JavaFX/Icon/line.png"));
        addLoTrinh.setGraphic(new ImageView(image));
        
        scrollMode.setSelected(true);
        
        addLoTrinh.setVisible(false);
        removeLoTrinh.setVisible(false);
        HBox box = new HBox(scrollMode,addMode,addLoTrinh,removeLoTrinh);
        mainGrid.add(box, 0, 0);
    }
    
    public void showLoTrinhOption(){
        addLoTrinh.setVisible(true);
        removeLoTrinh.setVisible(true);
    }
    
    public void setUpMap(){
        mapPane = new AnchorPane();
        scrollPane = new ScrollPane();
        InputStream input = getClass().getResourceAsStream("/JavaFX/Icon/map.jpg");
        img = new Image(input);
        ImageView iv = new ImageView(img);
        iv.setLayoutX(0);
        iv.setLayoutY(0);
        iv.setFitHeight(img.getHeight());
        iv.setFitWidth(img.getWidth());
        scrollPane.setContent(mapPane);
        
        mainGrid.add(scrollPane, 0, 1);
        mapPane.getChildren().add(iv);
        for(int i = 0; i < table.getItems().size(); i++){
            themTramMap((Tram)table.getItems().get(i));
        }
        iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(!coTheDatTram((int)event.getX() + 40, (int)event.getY() + 60)){
                    showThongBao("khong the them tram tai day");
                    return;
                }
//                if(addMode.isSelected()){
                    mode = 1;
                    clearFields();
                    enableFields();
                    showSecondBox();
                    TextField textField = (TextField) infoPane.getNode().get(2);
                    textField.setText("" + (event.getX() - 40));
                    textField = (TextField) infoPane.getNode().get(3);
                    textField.setText("" + (event.getY() - 60));
               //}
                
                
                event.consume();
            }
       });
        
        
        //mainGrid.add(scrollPane, 0, 0, 1, 2);
    }
    
    public void themTramMap(Tram tram){
        TramButton button = new TramButton(tram);
        //Image imageOk = new Image(getClass().getResourceAsStream("/Icon/pin.png"));
        //button.setGraphic(new ImageView(imageOk));
        
        mapPane.getChildren().add(button);
        
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                table.getSelectionModel().select(button.getTram());
                select(button.getTram(),infoPane);
                
                if(addMode.isSelected()){
                    mode = 2;
                    showSecondBox();
                }else if(addLoTrinh.isSelected()){
                    addLoTrinh(button.getTram());
                }else if(removeLoTrinh.isSelected()){
                    removeLoTrinh(button.getTram());
                }
            }
        });
        
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                oX = button.getLayoutX();
                oY = button.getLayoutY();
                System.out.println("button.getTram().getTuyen().size() " + button.getTram().getTuyen().size());
                if(button.getTram().getTuyen().size() == 0){
                    deltaX = button.getLayoutX() - mouseEvent.getSceneX();
                    deltaY = button.getLayoutY() - mouseEvent.getSceneY();
                }
                button.setCursor(Cursor.MOVE);
            }
        });
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Tram tram = button.getTram();
                if(tram.getTuyen().size() == 0 && coTheDatTram((int)tram.getX() + 40, (int)tram.getY() + 60)){
                    DataGetter.mergeObject(tram);
                }
                else{
                    button.setLayoutX(oX);
                    button.setLayoutY(oY);
                    tram.setX(oX);
                    tram.setY(oY);
                }
                
            }
        });
        button.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(tram.getTuyen().size() == 0){
                    button.setLayoutX(mouseEvent.getSceneX() + deltaX);
                    button.setLayoutY(mouseEvent.getSceneY() + deltaY);
                    tram.setX(mouseEvent.getSceneX() + deltaX);
                    tram.setY(mouseEvent.getSceneY() + deltaY);
                }
            }
        });
    }
    
    public void addLoTrinh(Tram tram){
        
    }
    
    public void removeLoTrinh(Tram tram){
        
    }
}
