/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import javafx.scene.Node;
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
public abstract class SearchNode extends HBox{
    public abstract <T> Predicate get(CriteriaBuilder cb, Root<T> root, String field);
    public abstract void clearField();
    public abstract void storedExValue();
    public abstract void setExValue();
    
    public <T> Path getPath(String field, Root<T> root){
        Path path;
        String [] fields = field.split(":");
        if(fields.length == 1)
            path = root.get(fields[0]);
        else
            path = root.get(fields[1]).get(fields[0]);
        return path;
    }
}
