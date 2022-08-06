/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Hibernate.DataGetter;
import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.InfoPane;
import JavaFX.CustomNode.SearchPane;
import JavaFX.CustomNode.TypeOfConstraint;
import JavaFX.InputField;
import java.lang.reflect.Field;
import java.util.logging.Logger;
import JavaFX.JavaFXUtil;
import JavaFX.Nodes;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.persistence.criteria.Predicate;

/**
 * FXML Controller class
 *
 * @author dokha
 */
public abstract class BasedFrameControllerr implements Initializable {

    @FXML
    protected GridPane mainGrid;
    @FXML
    protected TextField searchField;
    @FXML
    protected VBox searchP;
    @FXML
    protected HBox firstBox;
    @FXML
    protected Button themButton;
    @FXML
    protected Button xoaButton;
    @FXML
    protected Button suaButton;
    @FXML
    protected HBox secondBox;
    @FXML
    protected Button okButton;
    @FXML
    protected Button huyButton;
    @FXML
    protected Label thongBaoLabel;
    @FXML
    protected VBox info;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected ScrollPane sPane;
    @FXML
    protected VBox tablePane;
    @FXML 
    protected Accordion accordion;
    @FXML
    protected Button previousPage;
    @FXML
    protected Button nextPage;
    @FXML
    protected ComboBox<Integer> pageBox; 
    
    protected TableView table;
    protected InfoPane infoPane;
    protected SearchPane searchPane;
    protected SearchPane searchPaneCurrent;
    protected Button clearButton;
    protected Button seachButton;
    protected ArrayList<InfoPane> infoPanes;
    protected ArrayList<String> fieldName;
    protected int mode = 0;
    protected Class clazzz;
    protected long pageSize = 10;
    protected int lastPageNumber = 0;
    protected int currentPage = 1;
    protected Predicate [] predicates = null;
    protected boolean newCondition = false;
    
    public InfoPane getInfoPane() {
        return infoPane;
    }

    public void setInfoPane(InfoPane infoPane) {
        this.infoPane = infoPane;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public VBox getTablePane() {
        return tablePane;
    }

    public void setTablePane(VBox tablePane) {
        this.tablePane = tablePane;
    }
    
    public TableView getTable() {
        return table;
        // TODO
    }    
    
    public void setTable(TableView table) {
        this.table = table;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initial(Class clazzz, String name){
        fieldName = new ArrayList<String>();
        infoPanes = new ArrayList<InfoPane>();
        tabPane.getTabs().get(0).setText(name);
        
        this.clazzz = clazzz;
        table = JavaFXUtil.makeTable(clazzz);
        
        
        infoPane = JavaFXUtil.makeRightBar(clazzz);
        info.getChildren().add(0,infoPane);
        
        tablePane.getChildren().add(0,table);
        
        disableFields();
        
        table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Object object = table.getSelectionModel().getSelectedItem();
                if(object != null){
                    select(object, infoPane);
                    for(int i = 0; i < fieldName.size(); i++){
                        try {
                            Field field = object.getClass().getDeclaredField(fieldName.get(i));
                            field.setAccessible(true);
                            Object value = field.get(object);
                            select(value, infoPanes.get(i));
                        } catch (NoSuchFieldException ex) {
                            Logger.getLogger(BasedFrameControllerr.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SecurityException ex) {
                            Logger.getLogger(BasedFrameControllerr.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(BasedFrameControllerr.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(BasedFrameControllerr.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
                
            }
        });
        
        //disableButton(false, true, true);
        //checkConstraint();
        
        
        for(Field field : clazzz.getDeclaredFields()){
            if(field.isAnnotationPresent(InputField.class)){
                InputField annotation = field.getAnnotation(InputField.class);
                if(annotation.nodes() == Nodes.COMBOBOX){
                    InfoPane infoPane = JavaFXUtil.makeRightBar(field.getType());
                    infoPane.disableFields();
                    Tab tab = new Tab(annotation.fieldName(), infoPane);
                    tabPane.getTabs().add(tab);
                    fieldName.add(field.getName());
                    infoPanes.add(infoPane);
                    
                }
            }
        }
        searchPane = JavaFXUtil.makeSearchPane(clazzz);
//        searchP.getChildren().add(searchPane);
        seachButton = new Button("Tìm");
        searchPane.add(seachButton,0,7);
        seachButton.setOnAction((event) -> {
            System.out.println("search");
            makePageBox();
            updateCondition();
        });
        
        clearButton = new Button("Clear");
        searchPane.add(clearButton,1,7);
        clearButton.setOnAction((event) -> {
            System.out.println("clear");
            searchPane.clearSearchNodes();
            makePageBox();
            updateCondition();
        });
        
        sPane.setContent(searchPane);
        
        accordion.expandedPaneProperty().addListener(new 
            ChangeListener<TitledPane>() {
                public void changed(ObservableValue<? extends TitledPane> ov,
                    TitledPane old_val, TitledPane new_val) {
                        
                        if(new_val == null){
                            mainGrid.getRowConstraints().get(0).setMaxHeight(50);
                            //vBox.setMaxHeight(50);
                            //mainGrid.getRowConstraints().get(0).setPrefHeight(150);
                            
                        }
                        else{
                            mainGrid.getRowConstraints().get(0).setMaxHeight(180);
                            //vBox.setMaxHeight(400);
                            //mainGrid.getRowConstraints().get(0).setPrefHeight(400);
                        } 
                            
              }

            
        });
        mainGrid.getRowConstraints().get(0).setMaxHeight(50);
        
        
        
        nextPage.setOnAction((event) -> {
            goToNextPage();
        });
        previousPage.setOnAction((event) -> {
            goToPreviousPage();
        });
        
//        List list = DataGetter.listObject(clazzz, searchPane,currentPage,(int)pageSize, predicates);
//        table.getItems().addAll(list);
        makePageBox();        
        updateCondition();
        
        pageBox.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null && oldItem != newItem && !newCondition) {
                currentPage = newItem;
                searchPane.setExValue();
                table.getItems().clear();
                table.getItems().addAll(DataGetter.listObject(clazzz, searchPane, currentPage, (int)pageSize));
            }
        });
    }
    
    public void makePageBox(){
        long rowCount = DataGetter.getRowCount(clazzz,searchPane);
        lastPageNumber = (int) (Math.ceil(rowCount / pageSize));
        lastPageNumber += (rowCount % pageSize == 0) ? 0:1;
        pageBox.getItems().clear();
        for(int i = 0; i < lastPageNumber; i++)
            pageBox.getItems().add(i+1);
    }
    
    public void updateCondition(){
        currentPage = 1;
        newCondition = true;
        pageBox.getSelectionModel().select(currentPage-1);
        table.getItems().clear();
        table.getItems().addAll(DataGetter.listObject(clazzz, searchPane, currentPage, (int)pageSize));
        searchPane.storeExValue();
        newCondition = false;
    }
    
    public void enableFields(){
        infoPane.enableFields();
    }
    
    public void disableFields(){
        infoPane.disableFields();
    }
    
    public void clearFields(){
        infoPane.clearFields();
    }
    
    public void showFirstBox(){
        firstBox.setVisible(true);
        secondBox.setVisible(false);
    }
    
    public void showSecondBox(){
        firstBox.setVisible(false);
        secondBox.setVisible(true);
    }
    
    public boolean checkErrors(){
        for(Label l : infoPane.getErrorLabels())
            if(l.isVisible())
                return false;
        return true;
    }
    
    public void showThongBao(String s){
        thongBaoLabel.setText(s);
        thongBaoLabel.setVisible(true);
    }
    
    public void hideThongBao(){
        thongBaoLabel.setVisible(false);
    }
    
    public void disableButton(boolean them, boolean xoa, boolean sua){
        themButton.setDisable(them);
        xoaButton.setDisable(xoa);
        suaButton.setDisable(sua);
    }
    
    public Object add(Class clazz, InfoPane infoPane){
        Object object;
        try {
            object = clazz.newInstance();
            JavaFX.Entity e = (JavaFX.Entity)object;
            for(int i = 0; i < infoPane.getNode().size(); i++){
                e.setValue(infoPane.getFieldName().get(i),
                        infoPane.getFieldVale(i));
            }
            Integer id = DataGetter.addObject(e);
            e.setId(id);
            return e;
        } catch (InstantiationException ex) {
            Logger.getLogger(BasedFrameControllerr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BasedFrameControllerr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object update(InfoPane infoPane){
        Object object = table.getSelectionModel().getSelectedItem();
        JavaFX.Entity e = (JavaFX.Entity)object;
        for(int i = 0; i < infoPane.getNode().size(); i++){
            e.setValue(infoPane.getFieldName().get(i),
                    infoPane.getFieldVale(i));
        }
        e = (JavaFX.Entity)DataGetter.mergeObject(e);
        //DataGetter.updateObject(e);
        return object;
    }
    
    public void delete(Object object, InfoPane infoPane){
        object = DataGetter.mergeObject(object);
        DataGetter.deleteObject(object);
    }
    
    @FXML
    public void them(ActionEvent event) {
        enableFields();
        showSecondBox();
        clearFields();
        mode = 1;
    }

    @FXML
    public void xoa(ActionEvent event) {
        showSecondBox();
        mode = 2;
    }

    @FXML
    public void sua(ActionEvent event) {
        enableFields();
        showSecondBox();
        mode = 3;
    }

    @FXML
    private void thucHienHanhDong(ActionEvent event) {
        
        if(!checkErrors())
            return;
        
        if(!checkConstraintn())
            return;
        
        if(!checkConstraint())
            return;
        
        if(mode == 1){
            Object object = add(clazzz, infoPane);
            if(object != null){
                
                if(table.getItems().size() < 10)
                    table.getItems().add(object);
                else
                    makePageBox();
            }
            afterAdd(object);
        }
            
        else if(mode == 3){
            Object object = update(infoPane);
            if(object != null)
                table.getItems().set(table.getSelectionModel().getSelectedIndex(),object);
            afterUpdate(object);
        }
        else {
            Object object = table.getSelectionModel().getSelectedItem();
            delete(object, infoPane);
            if(table.getItems().size() > 0)
                table.getItems().remove(object);
            else{
                goToPreviousPage();
                if(pageBox.getItems().size() > 1)
                    pageBox.getItems().remove(pageBox.getItems().size() - 1);
            }
                
            
            afterDelete(object);
        }
        
        hideThongBao();
        mode = 0;
        disableFields();
        showFirstBox();
    }

    @FXML
    private void huyHanhDong(ActionEvent event) {
        disableFields();
        showFirstBox();
        Object object = table.getSelectionModel().getSelectedItem();
        select(object, infoPane);
        hideThongBao();
        mode = 0;
    }
    
    public void select(Object object, InfoPane infoPane){
        if(object == null){
            infoPane.clearFields();
            return;
        }
//        if(checkConstraint()){
//            hideThongBao();
//            disableButton(false, false, false);
//        }
        JavaFX.Entity e = (JavaFX.Entity) object;
        for(int i = 0; i < infoPane.getFieldName().size(); i++){
            Object value = e.getValue(infoPane.getFieldName().get(i));
            infoPane.setFieldVale(i, value);
        }
    }
    
    public void goToPreviousPage(){
        if(currentPage > 1){
            currentPage--;
            pageBox.getSelectionModel().select(currentPage-1);
            searchPane.setExValue();
            table.getItems().clear();
            table.getItems().addAll(DataGetter.listObject(clazzz, searchPane, currentPage, (int)pageSize));
        }
            
    }
    
    public void goToNextPage(){
        if(currentPage < lastPageNumber){
            currentPage++;
            pageBox.getSelectionModel().select(currentPage-1);
            searchPane.setExValue();
            table.getItems().clear();
            table.getItems().addAll(DataGetter.listObject(clazzz, searchPane, currentPage, (int)pageSize));
        }
    }
    
    public boolean checkConstraintn(){
        System.out.println("JavaFX.Controller.BasedFrameControllerr.checkConstraintn()");
        boolean result = true;
        switch(mode){
            case 0:
                break;
            case 1:  
                
                for(int i = 0; i < infoPane.getConstraints().size(); i++){
                    Constraint constraint = infoPane.getConstraints().get(i);
                    if(constraint.typeOfConstraint() == TypeOfConstraint.UNIQUE){
                        
                        Object value = infoPane.getFieldVale(i);
                        String field = infoPane.getFieldName().get(i);
                        
                        if(!DataGetter.checkUnique(clazzz, field, value)){
                           Label label = infoPane.getErrorLabels().get(i);
                           label.setText(constraint.message());
                           label.setVisible(true);
                           result = false;
                        }
                        
                    }
                }
                break;
            case 2:
                
                break;
            case 3:
                for(int i = 0; i < infoPane.getConstraints().size(); i++){
                    Constraint constraint = infoPane.getConstraints().get(i);
                    if(constraint.typeOfConstraint() == TypeOfConstraint.UNIQUE){
                        
                        Object value = infoPane.getFieldVale(i);
                        JavaFX.Entity e = (JavaFX.Entity)table.getSelectionModel().getSelectedItem();
                        String field = infoPane.getFieldName().get(i);
                        
                        if(!DataGetter.checkUnique(clazzz, constraint.idField(), e.getId(), field, value)){
                            Label label = infoPane.getErrorLabels().get(i);
                            label.setText(constraint.message());
                            label.setVisible(true);
                            result = false;
                        }
                        
                    }
                }
                break;
            default:
                break;
        }
        return result;
    }
    
    public abstract boolean checkConstraint();
    
    //method to overide
    public void extraInital(){};
    public void afterAdd(Object object){};
    public void afterUpdate(Object object){};
    public void afterDelete(Object object){};
}
