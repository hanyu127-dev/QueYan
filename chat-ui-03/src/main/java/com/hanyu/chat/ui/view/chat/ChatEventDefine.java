package com.hanyu.chat.ui.view.chat;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ChatEventDefine {
    // 获取组件
    private ChatInit chatInit;
    private IChatEvent chatEvent;
    private IChatMethod chatMethod;

    public ChatEventDefine(ChatInit chatInit, IChatEvent chatEvent, IChatMethod chatMethod) {
        this.chatInit = chatInit;
        this.chatEvent = chatEvent;
        this.chatMethod = chatMethod;
        chatInit.move();
        barChat();
        barFriend();

    }

    /**
     * 根据条件切换聊天面板
     *
     * @param bar_chat       聊天面板按钮
     * @param group_bar_chat 面板
     * @param toggle         条件
     */
    private void switchBarChat(Button bar_chat, Pane group_bar_chat, boolean toggle) {
        if (toggle) {
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_2.png')");
            group_bar_chat.setVisible(true);
        } else {
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_0.png')");
            group_bar_chat.setVisible(false);
        }
    }

    /**
     * 根据条件切换朋友面板
     * @param bar_friend 按钮
     * @param group_bar_friend 朋友面板
     * @param toggle 条件
     */
    private void switchBarFriend(Button bar_friend, Pane group_bar_friend, boolean toggle) {
        if (toggle) {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_2.png')");
            group_bar_friend.setVisible(true);
        } else {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_0.png')");
            group_bar_friend.setVisible(false);
        }
    }

    private void barChat() {
        // 设置面板可不可见
        chatInit.bar_chat.setOnAction(event -> {
            switchBarChat(chatInit.bar_chat,chatInit.group_bar_chat,true);
            switchBarFriend(chatInit.bar_friend,chatInit.group_bar_friend,false);
        });
        // 鼠标移入的时候展示背景图
        chatInit.bar_chat.setOnMouseEntered(event -> {
            boolean visible = chatInit.group_bar_chat.isVisible();
            if (visible) return;
            chatInit.bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_1.png')");
        });
        // 鼠标移除的时候展示背景图
        chatInit.bar_chat.setOnMouseExited(event -> {
            boolean visible = chatInit.group_bar_chat.isVisible();
            if (visible) return;
            chatInit.bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_0.png')");
        });
    }
    private void barFriend() {
        // 设置面板可不可见
        chatInit.bar_friend.setOnAction(event -> {
            switchBarChat(chatInit.bar_chat,chatInit.group_bar_chat,false);
            switchBarFriend(chatInit.bar_friend,chatInit.group_bar_friend,true);
        });
        // 鼠标移入的时候展示背景图
        chatInit.bar_friend.setOnMouseEntered(event -> {
            boolean visible = chatInit.group_bar_friend.isVisible();
            if (visible) return;
            chatInit.bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_1.png')");
        });
        // 鼠标移除的时候展示背景图
        chatInit.bar_friend.setOnMouseExited(event -> {
            boolean visible = chatInit.group_bar_friend.isVisible();
            if (visible) return;
            chatInit.bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_0.png')");
        });
    }
}
