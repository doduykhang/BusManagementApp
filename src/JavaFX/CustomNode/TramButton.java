/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.CustomNode;

import Entity.Tram;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author dokha
 */
public class TramButton extends Button{
    private Tram tram;

    public TramButton(Tram tram) {
        super(tram.getTenTram());
        setLayoutX(tram.getX());
        setLayoutY(tram.getY());
        Image imageOk = new Image(getClass().getResourceAsStream("/JavaFX/Icon/pin.png"));
        setGraphic(new ImageView(imageOk));
        setStyle("-fx-background-color: transparent;"
                        + "-fx-font-size:40;"
                        + "-fx-text-fill: red"
                        );
        this.tram = tram;
    }

    public Tram getTram() {
        return tram;
    }

    public void setTram(Tram tram) {
        this.tram = tram;
    }


    
}
