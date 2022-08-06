/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import java.time.LocalDate;
import java.util.Date;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dokha
 */
public class SearchDate extends SearchNode{
    private DatePicker dp1;
    private DatePicker dp2;
    private LocalDate exValue1;
    private LocalDate exValue2;
    
    public SearchDate() {
        dp1 = new DatePicker();
        dp2 = new DatePicker();
        getChildren().addAll(dp1,new Label("-"),dp2);
        dp1.setMaxWidth(150);
        dp2.setMaxWidth(150);
        setAlignment(Pos.CENTER_LEFT);
    }

    public DatePicker getDp1() {
        return dp1;
    }

    public void setDp1(DatePicker dp1) {
        this.dp1 = dp1;
    }

    public DatePicker getDp2() {
        return dp2;
    }

    public void setDp2(DatePicker dp2) {
        this.dp2 = dp2;
    }
    
    
    
    @Override
    public <T> Predicate get(CriteriaBuilder cb, Root<T> root, String field) {
        LocalDate t1 = null;
        LocalDate t2 = null;
        
        t1 = dp1.getValue();
        t2 = dp2.getValue();
        Path path = getPath(field, root);
        
        if(t1 == null && t2 == null)
            return cb.isNotNull(path);
        else if(t1 == null)
            return cb.lessThan(path,t2);
        else if(t2 == null)
            return cb.greaterThan(path,t1);
        else 
            return cb.between(path, t1, t2);
    }

    @Override
    public void clearField() {
        dp1.setValue(null);
        dp2.setValue(null);
    }

    @Override
    public void storedExValue() {
        exValue1 = dp1.getValue();
        exValue2 = dp2.getValue();
    }

    @Override
    public void setExValue() {
        dp1.setValue(exValue1);
        dp2.setValue(exValue2);
    }
    
}
