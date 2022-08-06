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
public class TextFilter extends Filter{

    public TextFilter(Label errorLabel, int limit,  boolean checkEmpty) {
        this.errorLabel = errorLabel;
        this.limit = limit;
        this.checkEmpty = checkEmpty;
    }
    
    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        // Deletion should always be possible.
        if (change.isDeleted()) {
            return change;
        }

        // How would the text look like after the change?
        String txt = change.getControlNewText();
        if(txt.equals("") && checkEmpty){
        errorLabel.setVisible(true);
        errorLabel.setText("Không được để trống");
        return change;
        }
        
        if(txt.matches("\\s*")){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được khoảng cách đầu hàng");
            return null;
        }
        
        if(txt.matches(".*\\s\\s.*")){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được 2 khoảng cách liền");
            return null;
        }
        
        if(txt.length() > limit){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được quá " + limit + " ký tự");
            return null;
        }
            
        errorLabel.setVisible(false);
        return change;
    }
    
}
