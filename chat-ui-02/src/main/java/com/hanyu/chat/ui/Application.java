package com.hanyu.chat.ui;

import com.hanyu.chat.ui.view.login.ILoginMethod;
import com.hanyu.chat.ui.view.login.LoginController;
import com.hanyu.chat.ui.view.login.LoginView;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ILoginMethod login = new LoginController((userId, password) -> {
            System.out.println("登陆 userId：" + userId + "password：" + password);
        });
        login.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
