/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dokha
 */
public class SeachBoolean extends SearchNode{
    private CheckBox checkBox;
    
    private boolean exValue;
    public SeachBoolean() {
        super();
        this.checkBox = new CheckBox();
        getChildren().add(checkBox);
        setAlignment(Pos.CENTER_LEFT);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public <T> Predicate get(CriteriaBuilder cb, Root<T> root, String field) {
        Path path = getPath(field, root);
        return cb.equal(path,checkBox.isSelected());
    }

    @Override
    public void clearField() {
        checkBox.setSelected(false);
    }

    @Override
    public void storedExValue() {
        exValue = checkBox.isSelected();
    }

    @Override
    public void setExValue() {
        checkBox.setSelected(exValue);
    }
    
}
