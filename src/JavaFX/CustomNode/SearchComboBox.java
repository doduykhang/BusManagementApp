/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import JavaFX.Entity;
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
public class SearchComboBox extends SearchNode{
    private ComboBox comboBox;
    private int exIndex;
    JavaFX.Entity e;
    public SearchComboBox() {
        comboBox = new ComboBox();
        //comboBox.setEditable(true);
        e = new Entity() {
            @Override
            public Object getId() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object getValue(String fieldName) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object getDisplayValue() {
                return "Bất kỳ";
            }

            @Override
            public void setId(Object value) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setValue(String fieldName, Object value) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        comboBox.getItems().add(e);
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
        if(comboBox.getSelectionModel().getSelectedIndex() == 0)
            return cb.and();
        if(comboBox.getSelectionModel().getSelectedIndex() == -1)
            return cb.isNotNull(path);
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
