package com.hanyu.chat.ui.view.chat;

import com.hanyu.chat.ui.view.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * 获取所有组件以及完成初始化
 */

public abstract class ChatInit extends UIObject {
    private static final String RESOURCE_NAME = "/fxml/chat/chat.fxml";
    // 组件
    public Button bar_portrait;  // 头像
    public Button bar_chat;  // 聊天
    public Button bar_friend; //朋友
    public Button bar_collection; // 收藏
    public Button bar_set; // 设置
    public Pane group_bar_chat; // 聊天面板
    public Pane group_bar_friend; // 朋友面板
    public IChatEvent chatEvent;

    public String userId;
    public String userNickName;
    public String userHead;

    public TextArea txt_input;

    public ChatInit(IChatEvent chatEvent) {
        this.chatEvent = chatEvent;
        try {
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        }catch (Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/login/img/system/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }

    private void obtain(){
        bar_portrait = getUI("bar_portrait",Button.class);
        bar_chat = getUI("bar_chat",Button.class);
        bar_friend = getUI("bar_friend",Button.class);
        bar_collection = getUI("bar_collection",Button.class);
        bar_set = getUI("bar_set",Button.class);
        group_bar_chat = getUI("group_bar_chat",Pane.class);
        group_bar_friend = getUI("group_bar_friend",Pane.class);
        txt_input = getUI("txt_input", TextArea.class);
    }
}
