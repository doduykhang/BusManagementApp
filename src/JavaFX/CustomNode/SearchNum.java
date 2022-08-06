/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dokha
 */
public class SearchNum extends SearchNode{
    private TextField n1;    
    private TextField n2;    
    private String exValue1;
    private String exValue2;
    
    public SearchNum() {
        super();
        this.n1 = new TextField();
        this.n2 = new TextField();
        this.getChildren().addAll(n1,new Label("-"),n2);
        n1.setMaxWidth(50);
        n2.setMaxWidth(50);
        setAlignment(Pos.CENTER_LEFT);
    }
    
    public TextField getN1() {
        return n1;
    }

    public void setN1(TextField n1) {
        this.n1 = n1;
    }

    public TextField getN2() {
        return n2;
    }

    public void setN2(TextField n2) {
        this.n2 = n2;
    }
    
    @Override
    public <T> Predicate get(CriteriaBuilder cb, Root<T> root, String field){
        int n1 = -1;
        int n2 = -1;
        String t1 = this.n1.getText().replace(".", "");
        String t2 = this.n2.getText().replace(".", "");
        if(!t1.equals(""))
            n1 = Integer.parseInt(t1);
        if(!t2.equals(""))
            n2 = Integer.parseInt(t2);
        
        Path path = getPath(field, root);
        
        if(n1 == -1 && n2 == -1)
            return cb.isNotNull(path);
        else if(n1 == -1)
            return cb.lt(path,n2);
        else if(n2 == -1)
            return cb.gt(path,n1);
        else 
            return cb.between(path, n1, n2);
        
    }

    @Override
    public void clearField() {
        n1.clear();
        n2.clear();
    }

    @Override
    public void storedExValue() {
        exValue1 = n1.getText();
        exValue2 = n2.getText();
    }

    @Override
    public void setExValue() {
        n1.setText(exValue1);
        n2.setText(exValue2);
    }

}
