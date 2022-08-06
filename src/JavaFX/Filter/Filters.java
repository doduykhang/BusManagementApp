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
public enum Filters {    
    None{
        @Override
        public UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty) {
            return null;
        }
    },
    Number{
        @Override
        public UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty) {
            return new NumberFilter(errorLabel, limit, checkEmpty);
        }
    },
    Text{
        @Override
        public UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty) {
            return new TextFilter(errorLabel, limit, checkEmpty);
        }
    },
    
    EMAIL{
        @Override
        public UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty) {
            return new EmailFilter(errorLabel,50, checkEmpty);
        }
    },
    
    BIENSOXE{
        @Override
        public UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty) {
            return new BienSoXeFilter(errorLabel,limit,checkEmpty);
        }
    },
    
    SDT{
        @Override
        public UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty) {
            return new SDTFilter(errorLabel,limit,checkEmpty);
        }
    },
    MONEY{
        @Override
        public UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty) {
            return new MoneyFilter(errorLabel,limit,checkEmpty);
        }
    },
    NOTBLANK{
    @Override
        public UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty) {
            return new NonBlankFilter(errorLabel,limit,checkEmpty);
        }
    };
    public abstract UnaryOperator<TextFormatter.Change> test(Label errorLabel, int limit, boolean checkEmpty);
}
