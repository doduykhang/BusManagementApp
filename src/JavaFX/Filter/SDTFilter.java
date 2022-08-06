/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Filter;

import java.util.function.UnaryOperator;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author dokha
 */
public class SDTFilter extends Filter{

    public SDTFilter(Label errorLabel, int limit,  boolean checkEmpty) {
        this.errorLabel = errorLabel;
        this.limit = limit;
        this.checkEmpty = checkEmpty;
    }
    
    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        String controlNewText = change.getControlNewText();
        String controlText = change.getControlText();
        
        if(controlNewText.equals("") && checkEmpty){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được để trống");
            return change;
        }
        
        if(!controlNewText.matches("[0-9]*")){
            errorLabel.setVisible(true);
            errorLabel.setText("Chỉ được nhập số");
            return null;
        }
        
        if(controlNewText.matches(".*\\s.*")){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được có khoảng cách");
            return null;
        }
        
        if(controlNewText.length() > 10 && change.isAdded()){
            errorLabel.setVisible(true);
            errorLabel.setText("Chỉ được 10 chữ số");
            return null;
        }
        
        if(!controlNewText.matches("[0-9]{10}")){
            errorLabel.setVisible(true);
            errorLabel.setText("phải có 10 chữ số");
            return change;
        }
        errorLabel.setVisible(false);
        return change;
    }
}
