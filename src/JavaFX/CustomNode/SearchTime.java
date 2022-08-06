/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import java.time.LocalTime;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dokha
 */
public class SearchTime extends SearchNode{
    private TimePicker tp1;
    private TimePicker tp2;
    private LocalTime exValue1;
    private LocalTime exValue2;
    public SearchTime() {
        tp1 = new TimePicker();
        tp2 = new TimePicker();
        getChildren().addAll(tp1,new Label("-"),tp2);
        setAlignment(Pos.CENTER_LEFT);
    }

    public TimePicker getTp1() {
        return tp1;
    }

    public void setTp1(TimePicker tp1) {
        this.tp1 = tp1;
    }

    public TimePicker getTp2() {
        return tp2;
    }

    public void setTp2(TimePicker tp2) {
        this.tp2 = tp2;
    }
    
    
    
    @Override
    public <T> Predicate get(CriteriaBuilder cb, Root<T> root, String field) {
        LocalTime n1 = tp1.getValue();
        LocalTime n2 = tp2.getValue();
        
        Path path = getPath(field, root);
        
        if(n1 == null && n2 == null)
            return cb.isNotNull(path);
        else if(n1 == null)
            return cb.lessThan(path,n2);
        else if(n2 == null)
            return cb.greaterThan(path,n1);
        else 
            return cb.between(path, n1, n2);
    }

    @Override
    public void clearField() {
        tp1.clear();
        tp2.clear();
    }

    @Override
    public void storedExValue() {
        exValue1 = tp1.getValue();
        exValue2 = tp2.getValue();
    }

    @Override
    public void setExValue() {
        tp1.setValue(exValue1);
        tp2.setValue(exValue1);
    }
    
}
