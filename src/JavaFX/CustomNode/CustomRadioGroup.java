/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dokha
 */
public class CustomRadioGroup extends SearchNode{
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private int exValue;
    public CustomRadioGroup() {
        VBox vBox = new VBox();
        ToggleGroup toggleGroup = new ToggleGroup();
        r1 = new RadioButton();
        r2 = new RadioButton();
        r3 = new RadioButton();
        r1.setToggleGroup(toggleGroup);
        r2.setToggleGroup(toggleGroup);
        r3.setToggleGroup(toggleGroup);
        r3.setText("bất kỳ");
        r3.setSelected(true);
        vBox.getChildren().addAll(r1,r2,r3);
        getChildren().add(vBox);
        setAlignment(Pos.CENTER_LEFT);
    }

    public RadioButton getR1() {
        return r1;
    }

    public void setR1(RadioButton r1) {
        this.r1 = r1;
    }

    public RadioButton getR2() {
        return r2;
    }

    public void setR2(RadioButton r2) {
        this.r2 = r2;
    }

    public RadioButton getR3() {
        return r3;
    }

    public void setR3(RadioButton r3) {
        this.r3 = r3;
    }
    
    public int getValue(){
        if(r1.isSelected())
            return 0;
        if(r2.isSelected())
            return 1;
        if(r3.isSelected())
            return 2;
        return -1;
    }
    
    public void setValue(boolean value){
        r1.setSelected(value);
        r2.setSelected(!value);
    }
    
    public void setEnable(boolean value){
        r1.setDisable(!value);
        r1.setDisable(!value);
    }

    @Override
    public <T> Predicate get(CriteriaBuilder cb, Root<T> root, String field) {
        int i = getValue();
       
        Path path = getPath(field, root);
        
        if(i == 2)
            return cb.isNotNull(path);
        boolean bool = (i == 0) ? true:false;
        return cb.equal(root.get(field),bool);
    }

    @Override
    public void clearField() {
        r3.setSelected(true);
    }

    @Override
    public void storedExValue() {
        if(r1.isSelected())
            exValue = 0;
        else if (r2.isSelected())
            exValue = 1;
        else 
            exValue = 2;
    }

    @Override
    public void setExValue() {
        if(exValue == 0)
            r1.setSelected(true);
        else if(exValue == 1)
            r2.setSelected(true);
        else 
            r3.setSelected(true);
    }
}
