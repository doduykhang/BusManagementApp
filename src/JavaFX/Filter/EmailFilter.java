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
public class EmailFilter extends Filter{
    
    
    public EmailFilter(Label errorLabel, int limit,  boolean checkEmpty) {
        this.errorLabel = errorLabel;
        this.limit = limit;
        this.checkEmpty = checkEmpty;
    }
    
    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        String controlNewText = change.getControlNewText();
        if(controlNewText.equals("") && checkEmpty){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được để trống");
            return change;
        }
        
        if(controlNewText.matches(".*@.*@")){
            errorLabel.setVisible(true);
            errorLabel.setText("Đã có @");
            return null;
        }
        
        if(controlNewText.matches(".*\\s.*")){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được có khoảng cách");
            return null;
        }
        
        if(!controlNewText.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+")){
            errorLabel.setVisible(true);
            errorLabel.setText("Email sai định dạng");
            return change;
        }
        
        if(controlNewText.length() > limit){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được quá " + limit + " ký tự");
            return null;
        }
        
        errorLabel.setVisible(false);
        return change;
        
    }
    
}
