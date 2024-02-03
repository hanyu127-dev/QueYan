package com.hanyu.chat.ui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Stage {
    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";
    private Parent root;
    public Login(){
        try{
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        }catch (Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        // 设置窗体能否被用户调整
        setResizable(false);
        this.getIcons().add(new Image("/fxml/login/img/system/logo.png"));

    }
}
