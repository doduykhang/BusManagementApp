/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import JavaFX.Filter.NumberFilter;
import java.time.LocalTime;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

/**
 *
 * @author dokha
 */
public class TimePicker extends HBox{
    private TextField hourField;
    private TextField minuteField;

    public TimePicker() {
        hourField = new TextField();
        minuteField = new TextField();
        
        hourField.setMaxWidth(50);
        minuteField.setMaxWidth(50);
        
        getChildren().add(hourField);
        getChildren().add(new Label(":"));
        getChildren().add(minuteField);
        setAlignment(Pos.CENTER_LEFT);
        
        hourField.setTextFormatter(new TextFormatter<String>(new NumberFilter(new Label(), 23, false)));
        minuteField.setTextFormatter(new TextFormatter<String>(new NumberFilter(new Label(), 59, false)));
    }

    public TextField getHourField() {
        return hourField;
    }

    public void setHourField(TextField hourField) {
        this.hourField = hourField;
    }

    public TextField getMinuteField() {
        return minuteField;
    }

    public void setMinuteField(TextField minuteField) {
        this.minuteField = minuteField;
    }
    
    public LocalTime getValue(){
        Integer hour = null;
        if(!hourField.getText().equals(""))
            hour = Integer.parseInt(hourField.getText());
        
        Integer minute = null;
        if(!minuteField.getText().equals(""))
            minute = Integer.parseInt(minuteField.getText());
        if(hour == null || minute == null)
            return null;
        return LocalTime.of(hour, minute);
    }
    
    public void setValue(LocalTime localTime){
        hourField.setText(localTime.getHour() + "");
        minuteField.setText(localTime.getMinute()+ "");
    }
    
    public void clear(){
        hourField.clear();
        minuteField.clear();
    }
    
    public void disableField(boolean bool){
        hourField.setEditable(bool);
        minuteField.setEditable(bool);
    }
    
    public void setFilter(Label errorLabel){
        hourField.setTextFormatter(new TextFormatter<String>(new NumberFilter(errorLabel, 23, false)));
        minuteField.setTextFormatter(new TextFormatter<String>(new NumberFilter(errorLabel, 59, false)));
    }
}
