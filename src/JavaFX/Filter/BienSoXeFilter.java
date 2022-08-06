/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Filter;


import java.util.ArrayList;
import java.util.function.UnaryOperator;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author dokha
 */
public class BienSoXeFilter extends Filter{

    public BienSoXeFilter(Label errorLabel, int limit,  boolean checkEmpty) {
        this.errorLabel = errorLabel;
        this.limit = limit;
        this.checkEmpty = checkEmpty;
    }
    
    
    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        String newText = change.getControlNewText();
        if(newText.equals("") && checkEmpty){
            errorLabel.setVisible(true);
            errorLabel.setText("Không được để trống");
            return change;
        }
        System.out.println(newText.length());
        if(newText.length() > 10){
            errorLabel.setVisible(true);
            errorLabel.setText("Chỉ được phép 10 ký tự");
            return null;
        }
        
        if(!newText.matches("[0-9]{2}B-[0-9]{3}.[0-9]{2}")){
            errorLabel.setVisible(true);
            errorLabel.setText("Không đúng định đạng biển số xe");
            return change;
        }
        int count = 0;
        errorLabel.setVisible(false);
        return change;
    }
    
}
