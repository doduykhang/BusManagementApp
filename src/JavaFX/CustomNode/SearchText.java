/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dokha
 */
public class SearchText extends SearchNode{
    private TextField textField;
    private String exValue;
    
    public SearchText() {
        textField = new TextField();
        getChildren().add(textField);
        textField.setMaxWidth(150);
        setAlignment(Pos.CENTER_LEFT);
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }
    
    @Override
    public <T> Predicate get(CriteriaBuilder cb, Root<T> root, String field) {
        String text  = "%" + textField.getText() + "%";
        Path path = getPath(field, root);
        return cb.like(path, text);
    }

    @Override
    public void clearField() {
        textField.clear();
    }

    @Override
    public void storedExValue() {
        exValue = textField.getText();
    }

    @Override
    public void setExValue() {
        textField.setText(exValue);
    }
    
}
