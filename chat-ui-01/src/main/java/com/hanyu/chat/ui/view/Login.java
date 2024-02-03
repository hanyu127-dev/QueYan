package com.hanyu.chat.ui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        initEvent();

    }
    private void  initEvent(){
        move();
        min();
        quit();
        login();
    }

    private Double xOffset;
    private Double yOffset;

    private void move(){
        root.setOnMousePressed(event -> {
            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            root.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });
        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }

    private void min(){
        Button loginMin = getUI("login_min", Button.class);
        loginMin.setOnAction(event -> {
            setIconified(true);
        });
    }
    private void quit(){
        Button loginClose = getUI("login_close", Button.class);
        loginClose.setOnAction(event -> {
            close();
            System.exit(0);
        });
    }

    private void login(){
        TextField userId = getUI("userId",TextField.class);
        PasswordField password = getUI("userPassword",PasswordField.class);
        getUI("login_button", Button.class).setOnAction(event -> {
            System.out.println("登陆操作");
            System.out.println("用户ID：" + userId.getText());
            System.out.println("用户密码：" + password.getText());
        });
    }

    private <T> T getUI(String id,Class<T> clazz){
        return (T)root.lookup("#"+id);
    }
}
