package com.hanyu.chat.ui;

import com.hanyu.chat.ui.view.chat.ChatController;
import com.hanyu.chat.ui.view.chat.IChatEvent;
import com.hanyu.chat.ui.view.chat.IChatMethod;
import com.hanyu.chat.ui.view.login.ILoginMethod;
import com.hanyu.chat.ui.view.login.LoginController;
import com.hanyu.chat.ui.view.login.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Application extends javafx.application.Application {
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        IChatMethod chat = new ChatController();
        chat.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
