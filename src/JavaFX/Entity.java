/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import java.util.ArrayList;

/**
 *
 * @author dokha
 */
public interface Entity {
    public Object getId();
    public Object getValue(String fieldName);
    public Object getDisplayValue();
    public void setId(Object value);
    public void setValue(String fieldName, Object value);
}
