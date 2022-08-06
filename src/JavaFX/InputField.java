/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import JavaFX.Filter.Filters;
import JavaFX.Nodes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author dokha
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InputField{
    public String fieldName();
    public Filters filters() default Filters.None;
    public Nodes nodes() default Nodes.TEXTFILED;
    public int limit() default 255;
    public String trueRadio() default "";
    public String falseRadio() default "";
}
