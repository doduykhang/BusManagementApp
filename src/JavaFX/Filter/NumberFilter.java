/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Filter;

import java.util.function.UnaryOperator;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author dokha
 */
public class NumberFilter extends Filter{

    public NumberFilter(Label errorLabel, int limit,  boolean checkEmpty) {
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
            
            try {
                long n = Long.parseLong(txt);
                if(0 <= n && n <= limit){
                    errorLabel.setVisible(false);
                    return change;
                }
                else{
                    errorLabel.setVisible(true);
                    errorLabel.setText("không được lớn hơn " + limit);
                    return null;
                }
                
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                errorLabel.setVisible(true);
                errorLabel.setText("chỉ được nhập số");
                return null;
            }
    }
    
}
