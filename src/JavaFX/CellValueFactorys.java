/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import JavaFX.Filter.MoneyFilter;
import java.math.BigDecimal;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author dokha
 */
public enum CellValueFactorys {
    FK {
        @Override
        public Callback<TableColumn.CellDataFeatures<Object, String>, ObservableValue<String>> test(String fieldName) {
            return new Callback<TableColumn.CellDataFeatures<Object,String>,ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Object, String> param) {
                    Entity t = (Entity)param.getValue();
                    return new SimpleStringProperty((String)t.getValue("FK" + fieldName));
                    //return new SimpleStringProperty(" ");
                }
            };
        }
    }, 
    NORMAL{
        @Override
        public Callback<TableColumn.CellDataFeatures<Object, String>, ObservableValue<String>> test(String fieldName) {
            return new PropertyValueFactory<>(fieldName);
        }
    }, 
    BOOLEAN{
        @Override
        public Callback<TableColumn.CellDataFeatures<Object, String>, ObservableValue<String>> test(String fieldName) {
            return new Callback<TableColumn.CellDataFeatures<Object,String>,ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Object, String> param) {
                    BooleanColumn t = (BooleanColumn)param.getValue();
                    return new SimpleStringProperty(t.boolColumnValue(fieldName));
                }
            };
        }
    }, 
    MONEY{
        @Override
        public Callback<TableColumn.CellDataFeatures<Object, String>, ObservableValue<String>> test(String fieldName) {
            return new Callback<TableColumn.CellDataFeatures<Object,String>,ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Object, String> param) {
                    Entity t = (Entity)param.getValue();
                    String string  = "";
                    BigDecimal bigDecimal = (BigDecimal) t.getValue(fieldName);
                    string = bigDecimal.toPlainString();
                    string = string.substring(0, string.indexOf("."));
                    string = MoneyFilter.getMoney(string);
                    return new SimpleStringProperty(string);
                }
            };
        }
    };
    
    public abstract Callback<TableColumn.CellDataFeatures<Object,String>,ObservableValue<String>> test(String fieldName);
}
