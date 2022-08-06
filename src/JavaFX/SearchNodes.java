/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import Hibernate.DataGetter;
import JavaFX.CustomNode.CustomRadioGroup;
import JavaFX.CustomNode.SeachBoolean;
import JavaFX.CustomNode.Search.SearchDonViBox;
import JavaFX.CustomNode.SearchComboBox;
import JavaFX.CustomNode.SearchDate;
import JavaFX.CustomNode.SearchNode;
import JavaFX.CustomNode.SearchNum;
import JavaFX.CustomNode.SearchText;
import JavaFX.CustomNode.SearchTime;
import java.lang.reflect.Field;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author dokha
 */
public enum SearchNodes {
    NUMBER {
        @Override
        public SearchNode getSearchNode(Field field) {
            return new SearchNum();
        }
    },
    BOOLEAN{
        @Override
        public SearchNode getSearchNode(Field field) {
            SeachBoolean seachBoolean = new SeachBoolean();
            
            return seachBoolean;
        }
    },
    TEXT{
        @Override
        public SearchNode getSearchNode(Field field) {
            return new SearchText();
        }
    },
    COMBOBOX{
        @Override
        public SearchNode getSearchNode(Field field) {
            SearchComboBox searchComboBox = new SearchComboBox();
            ComboBox comboBox = searchComboBox.getComboBox();
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
            
            return searchComboBox;
        }
    },
    DONVIBOX{
        @Override
        public SearchNode getSearchNode(Field field) {
            SearchDonViBox searchComboBox = new SearchDonViBox();
            ComboBox comboBox = searchComboBox.getComboBox();
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
            
            return searchComboBox;
        }
    },
    DATE{
        @Override
        public SearchNode getSearchNode(Field field) {
            return new SearchDate();
        }
    },
    TIME{
        @Override
        public SearchNode getSearchNode(Field field) {
            return new SearchTime();
        }
    },
    CUSTOMRADIO{
        @Override
        public SearchNode getSearchNode(Field field) {
            return new CustomRadioGroup();
        }
    };
    public abstract SearchNode getSearchNode(Field field);
}
