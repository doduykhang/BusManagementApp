/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import Entity.DonViQuanLy;
import Hibernate.DataGetter;
import JavaFX.CustomNode.CustomRadioGroup;
import JavaFX.CustomNode.TimePicker;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import sun.security.util.Password;

/**
 *
 * @author dokha
 */
public enum Nodes {
    TEXTFILED {
        @Override
        public Node getNode(Field field) {
            return new TextField();
        }

        @Override
        public Object getValue(Node node) {
            TextField textField = (TextField) node;
            return textField.getText();
        }
        
        @Override
        public void setValue(Object value, Node node) {
            TextField textField = (TextField) node;
            String string = "";
            
            if(value.getClass().getSimpleName().equals("BigDecimal")){
                BigDecimal bigDecimal = (BigDecimal) value;
                string = bigDecimal.toPlainString();
                string = string.substring(0, string.indexOf("."));
            }
            else     
                string = "" + value;
            textField.setText(string);
        }

        @Override
        public void clearContent(Node node) {
            TextField textField = (TextField) node;
            textField.clear();
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            TextField textField = (TextField) node;
            textField.setEditable(bool);
        }
    },
    TEXTAREA {
        @Override
        public Node getNode(Field field) {
            TextArea textArea = new TextArea();
            textArea.setMaxWidth(150);
            textArea.setWrapText(true);
            return textArea;
        }

        @Override
        public Object getValue(Node node) {
            TextArea textArea = (TextArea) node;
            return textArea.getText();
        }
        
        @Override
        public void setValue(Object value, Node node) {
            TextArea textArea = (TextArea) node;
            String string = "" + value;
            textArea.setText(string);
        }

        @Override
        public void clearContent(Node node) {
            TextArea textArea = (TextArea) node;
            textArea.clear();
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            TextArea textArea = (TextArea) node;
            textArea.setEditable(bool);
        }
    },
    CHECKBOX {
        @Override
        public Node getNode(Field field) {
            return new CheckBox();
        }

        @Override
        public Object getValue(Node node) {
            CheckBox checkBox = (CheckBox)node;
            return checkBox.isSelected();
        }

        @Override
        public void setValue(Object value, Node node) {
            CheckBox checkBox = (CheckBox) node;
            Boolean bool = (Boolean) value;
            checkBox.setSelected(bool);
        }

        @Override
        public void clearContent(Node node) {
            CheckBox checkBox = (CheckBox) node;
            checkBox.setSelected(false);
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            CheckBox checkBox = (CheckBox) node;
            checkBox.setDisable(!bool);
        }
    },
    Password {
        @Override
        public Node getNode(Field field) {
            return new PasswordField();
        }

        @Override
        public Object getValue(Node node) {
            PasswordField textField = (PasswordField) node;
            return textField.getText();
        }
        
        @Override
        public void setValue(Object value, Node node) {
            PasswordField textField = (PasswordField) node;
            String string = "";
                string = "" + value;
            textField.setText(string);
        }

        @Override
        public void clearContent(Node node) {
            PasswordField textField = (PasswordField) node;
            textField.clear();
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            PasswordField textField = (PasswordField) node;
            textField.setEditable(bool);
        }
    },
    COMBOBOX{
        @Override
        public Node getNode(Field field) {
            ComboBox comboBox = new ComboBox();
                Callback<ListView<Object>, ListCell<Object>> factory = lv -> new ListCell<Object>() {

                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    Entity t = (Entity) item;
                    setText(empty ? "" : (String)t.getDisplayValue());
                }

            };
            comboBox.getItems().addAll(DataGetter.listObject(field.getType().getSimpleName()));
            comboBox.setCellFactory(factory);
            comboBox.setButtonCell(factory.call(null));
            
            return comboBox;
        }

        @Override
        public Object getValue(Node node) {
            ComboBox comboBox = (ComboBox) node;
            System.out.println(comboBox.getSelectionModel().getSelectedItem());
            return comboBox.getSelectionModel().getSelectedItem();
        }

        @Override
        public void setValue(Object value, Node node) {
            ComboBox comboBox = (ComboBox) node;
            for(int i = 0; i < comboBox.getItems().size(); i++){
                if(comboBox.getItems().get(i).equals(value)){
                    comboBox.getSelectionModel().select(i);
                    break;
                }
            }
        }

        @Override
        public void clearContent(Node node) {
            ComboBox comboBox = (ComboBox) node;
            comboBox.getSelectionModel().select(0);
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            ComboBox comboBox = (ComboBox) node;
            comboBox.setDisable(!bool);
        }
        
    },
    CUSTOMBOX {
        @Override
        public Node getNode(Field field) {
            ComboBox comboBox = new ComboBox();
                Callback<ListView<Object>, ListCell<Object>> factory = lv -> new ListCell<Object>() {

                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    Entity t = (Entity) item;
                    setText(empty ? "" : (String)t.getDisplayValue());
                }

            };
            DonViQuanLy donViQuanLy = new DonViQuanLy();
            donViQuanLy.setTenDonVi("BGTVT");
            donViQuanLy.setMaDonVi(null);
            comboBox.getItems().add(donViQuanLy);
            
            comboBox.getItems().addAll(DataGetter.listObject(field.getType().getSimpleName()));
            comboBox.setCellFactory(factory);
            comboBox.setButtonCell(factory.call(null));
            
            return comboBox;
        }

        @Override
        public Object getValue(Node node) {
            ComboBox comboBox = (ComboBox) node;
            if(comboBox.getSelectionModel().getSelectedIndex() == -1)
                return null;
            return comboBox.getSelectionModel().getSelectedItem();
        }

        @Override
        public void setValue(Object value, Node node) {
            ComboBox comboBox = (ComboBox) node;
            if(value == null){
                comboBox.getSelectionModel().select(1);
            }
            for(int i = 0; i < comboBox.getItems().size(); i++){
                if(comboBox.getItems().get(i).equals(value)){
                    comboBox.getSelectionModel().select(i);
                    break;
                }
            }
        }

        @Override
        public void clearContent(Node node) {
            ComboBox comboBox = (ComboBox) node;
            comboBox.getSelectionModel().select(0);
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            ComboBox comboBox = (ComboBox) node;
            comboBox.setDisable(!bool);
        }
    },
    DATEPICKER{
        @Override
        public Node getNode(Field field) {
            DatePicker datePicker = new DatePicker();
            return datePicker;
        }

        @Override
        public Object getValue(Node node) {
             DatePicker datePicker = (DatePicker) node;
             return datePicker.getValue();
        }

        @Override
        public void setValue(Object value, Node node) {
            DatePicker datePicker = (DatePicker) node;
            datePicker.setValue((LocalDate)value);
        }

        @Override
        public void clearContent(Node node) {
            DatePicker datePicker = (DatePicker) node;
            datePicker.setValue(LocalDate.now());
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            DatePicker datePicker = (DatePicker) node;
            datePicker.setDisable(!bool);
        }
    },
    TIMEPICKER{
        @Override
        public Node getNode(Field field) {
            return new TimePicker();
        }

        @Override
        public Object getValue(Node node) {
            TimePicker timePicker = (TimePicker) node;
            return timePicker.getValue();
        }

        @Override
        public void setValue(Object value, Node node) {
            TimePicker timePicker = (TimePicker) node;
            timePicker.setValue((LocalTime)value);
        }

        @Override
        public void clearContent(Node node) {
            TimePicker timePicker = (TimePicker) node;
            timePicker.clear();
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            TimePicker timePicker = (TimePicker) node;
            timePicker.disableField(bool);
        }
    },
    RADIOBUTTON {
        @Override
        public Node getNode(Field field) {
            CustomRadioGroup customRadioGroup = new CustomRadioGroup();
            customRadioGroup.getR3().setVisible(false);
            return customRadioGroup;
        }

        @Override
        public Object getValue(Node node) {
            CustomRadioGroup customRadioGroup = (CustomRadioGroup)node;
            int i = customRadioGroup.getValue();
            return (i == 0) ? true:false;
        }

        @Override
        public void setValue(Object value, Node node) {
            CustomRadioGroup customRadioGroup = (CustomRadioGroup)node;
            Boolean bool = (Boolean) value;
            customRadioGroup.setValue(bool);
        }

        @Override
        public void clearContent(Node node) {
            CustomRadioGroup customRadioGroup = (CustomRadioGroup)node;
            customRadioGroup.getR1().setSelected(true);
        }

        @Override
        public void enableNode(Node node, Boolean bool) {
            CustomRadioGroup customRadioGroup = (CustomRadioGroup)node;
            customRadioGroup.setDisable(!bool);
        }
    };
    
    public abstract Node getNode(Field field);
    public abstract Object getValue(Node node);
    public abstract void setValue(Object value, Node node);
    public abstract void clearContent(Node node);
    public abstract void enableNode(Node node, Boolean bool);
}
