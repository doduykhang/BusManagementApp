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
public abstract class Filter implements UnaryOperator<TextFormatter.Change>{
    protected Label errorLabel;
    protected int limit;
    protected boolean checkEmpty;

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isCheckEmpty() {
        return checkEmpty;
    }

    public void setCheckEmpty(boolean checkEmpty) {
        this.checkEmpty = checkEmpty;
    }
    
}
