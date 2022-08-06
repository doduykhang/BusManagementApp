/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode.Search;

import Entity.DonViQuanLy;
import JavaFX.CustomNode.SearchNode;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dokha
 */
public class SearchDonViBox extends SearchNode{
    private ComboBox comboBox;
    private int exIndex;
    
    public SearchDonViBox() {
        comboBox = new ComboBox();
        DonViQuanLy donViQuanLy = new DonViQuanLy();
        donViQuanLy.setTenDonVi("BGTVT");
        donViQuanLy.setMaDonVi(null);
        comboBox.getSelectionModel().select(1);
        comboBox.getItems().add(donViQuanLy);
        getChildren().add(comboBox);
        setAlignment(Pos.CENTER_LEFT);
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }
    
    @Override
    public <T> Predicate get(CriteriaBuilder cb, Root<T> root, String field) {
        Path path = getPath(field, root);
        System.out.println("getSelectionModel().getSelectedIndex() " + comboBox.getSelectionModel().getSelectedIndex());
        if(comboBox.getSelectionModel().getSelectedIndex() == 1){
            System.out.println("return cb.isNull(path);");
            return cb.isNull(path);
        }
            
        if(comboBox.getSelectionModel().getSelectedIndex() == -1)
            return cb.and();
        JavaFX.Entity e = (JavaFX.Entity) comboBox.getSelectionModel().getSelectedItem();
        
        return cb.equal(path, e);
    }

    @Override
    public void clearField() {
        comboBox.getSelectionModel().select(-1);
    }

    @Override
    public void storedExValue() {
        exIndex = comboBox.getSelectionModel().getSelectedIndex();
    }

    @Override
    public void setExValue() {
        comboBox.getSelectionModel().select(exIndex);
    }
    
}

