/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import JavaFX.Nodes;
import JavaFX.TableFK;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author dokha
 */
public class InfoPane extends GridPane{
    private ArrayList<Label> errorLabels;
    private ArrayList<Node> node;
    private ArrayList<Nodes> nodes;
    private ArrayList<String> fieldName;
    private ArrayList<Constraint> constraints;
    
    public InfoPane(ArrayList<Label> errorLabels, ArrayList<Node> node, ArrayList<Nodes> nodes, ArrayList<String> fieldName) {
        this.errorLabels = errorLabels;
        this.node = node;
        this.nodes = nodes;
        this.fieldName = fieldName;
    }
    
    public InfoPane(){
        errorLabels = new ArrayList<>();
        node = new ArrayList<>();
        nodes = new ArrayList<>();
        fieldName = new ArrayList<>();
        constraints = new ArrayList<>();
    }
    
    public ArrayList<Node> getNode() {
        return node;
    }

    public void setNode(ArrayList<Node> node) {
        this.node = node;
    }

    public ArrayList<Nodes> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Nodes> nodes) {
        this.nodes = nodes;
    }
    
    public ArrayList<Label> getErrorLabels() {
        return errorLabels;
    }

    public void setErrorLabels(ArrayList<Label> errorLabels) {
        this.errorLabels = errorLabels;
    }

    public ArrayList<String> getFieldName() {
        return fieldName;
    }

    public void setFieldName(ArrayList<String> fieldName) {
        this.fieldName = fieldName;
    }

    public ArrayList<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(ArrayList<Constraint> constraints) {
        this.constraints = constraints;
    }
    
    public Object getFieldVale(int i){
        return nodes.get(i).getValue(node.get(i));
    }
    
    public void setFieldVale(int i, Object value){
        nodes.get(i).setValue(value,node.get(i));
    }
    
    public void clearFields(){
        for(int i = 0; i < node.size(); i++){
            nodes.get(i).clearContent(node.get(i));
        }
    }
    
    public void disableFields(){
        for(int i = 0; i < node.size(); i++){
            nodes.get(i).enableNode(node.get(i),false);
        }
    }
    
    public void enableFields(){
        for(int i = 0; i < node.size(); i++){
            nodes.get(i).enableNode(node.get(i),true);
        }
    }
}
