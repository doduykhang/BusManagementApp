/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author dokha
 */
public class SearchPane extends GridPane{
    private ArrayList<String> fieldName;
    private ArrayList<SearchNode> searchNodes;

    public SearchPane() {
        fieldName = new ArrayList<>();
        searchNodes = new ArrayList<>();
    }

    public ArrayList<String> getFieldName() {
        return fieldName;
    }

    public void setFieldName(ArrayList<String> fieldName) {
        this.fieldName = fieldName;
    }

    public ArrayList<SearchNode> getSearchNodes() {
        return searchNodes;
    }

    public void setSearchNodes(ArrayList<SearchNode> searchNodes) {
        this.searchNodes = searchNodes;
    }
    
    public String getName(int i){
        return fieldName.get(i);
    }
    
    public void clearSearchNodes(){
        for(SearchNode sn : searchNodes)
            sn.clearField();
    }
    
    public void storeExValue(){
        for(SearchNode sn : searchNodes)
            sn.storedExValue();
    }
    
    public void setExValue(){
        for(SearchNode sn : searchNodes)
            sn.setExValue();
    }
    
}
