package com.hanyu.chat.ui.view.login;

import com.hanyu.chat.ui.view.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * 窗口初始化类,获取一系列的组件。
 */
public abstract class LoginInit extends UIObject {
    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";
    public Button login_min;          // 登陆窗口最小化
    public Button login_close;        // 登陆窗口退出
    public Button login_button;       // 登陆按钮
    public TextField userId;          // 用户账户窗口
    public PasswordField userPassword;// 用户密码窗口
    // 窗体事件，如登陆等
    protected ILoginEvent loginEvent;

    public LoginInit(ILoginEvent loginEvent) {
        this.loginEvent = loginEvent;
        try{
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        }catch (Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill( Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/login/img/system/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }

    private void obtain() {
        login_min = getUI("login_min", Button.class);
        login_close = getUI("login_close", Button.class);
        login_button = getUI("login_button", Button.class);
        userId = getUI("userId", TextField.class);
        userPassword = getUI("userPassword", PasswordField.class);
    }

}
