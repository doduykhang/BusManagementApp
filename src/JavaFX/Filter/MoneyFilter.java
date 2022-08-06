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
public class MoneyFilter extends Filter{

    public MoneyFilter(Label errorLabel, int limit,  boolean checkEmpty) {
        this.errorLabel = errorLabel;
        this.limit = limit;
        this.checkEmpty = checkEmpty;
    }
    
    public static String getMoney(String moneyString){
        int n = moneyString.length()%3;
        int x = moneyString.length()-1;
        for(int i = x; i > 0; i--){
            if((i-n)%3 == 0){
                moneyString = moneyString.substring(0, i) + "." + moneyString.substring(i, moneyString.length());
            }
        }
        return moneyString;
    }
    
    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        String controlNewText = change.getControlNewText();
        
        if(controlNewText.equals("") && checkEmpty){
            if(errorLabel != null){
                errorLabel.setVisible(true);
                errorLabel.setText("Không được để trống");
            }
                
            return change;
        }
        
        String money = controlNewText.replace(".", "");
        if(!money.matches("[0-9]*")){
            if(errorLabel != null){
                errorLabel.setVisible(true);
                errorLabel.setText("Chỉ được nhập chữ số");
            }
            return null;
        }
        
        if(change.isAdded() || change.isDeleted()){
            change.setRange(0, change.getControlText().length());
            change.setText(getMoney(money));
        }
        
        if(errorLabel != null)
            errorLabel.setVisible(false);
        return change;
        
    }
    
}
