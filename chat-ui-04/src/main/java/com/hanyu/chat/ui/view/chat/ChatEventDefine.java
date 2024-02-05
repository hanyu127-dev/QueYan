package com.hanyu.chat.ui.view.chat;

import com.hanyu.chat.ui.view.chat.data.TalkBoxData;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.Date;

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
        min();
        quit();
        barChat();
        barFriend();
        doEventTouchSend();
        doEventTextSend();

    }
    private void min(){
        chatInit.getUI("group_bar_chat_min", Button.class).setOnAction(event -> {
            chatInit.setIconified(true);
        });
    }
    private void quit(){
        chatInit.getUI("group_bar_chat_close", Button.class).setOnAction(event -> {
            chatInit.close();
            System.exit(0);
        });
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

    private void doEventTouchSend() {
        Label touch_send = chatInit.getUI("touch_send", Label.class);
        touch_send.setOnMousePressed(event -> {
            doEventSendMsg();
        });
    }

    private void doEventTextSend() {
        TextArea txt_input = chatInit.getUI("txt_input", TextArea.class);
        txt_input.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                doEventSendMsg();
            }
        });
    }

    private void doEventSendMsg() {
        TextArea txt_input = chatInit.getUI("txt_input", TextArea.class);
        MultipleSelectionModel selectionModel = chatInit.getUI("talkList", ListView.class).getSelectionModel();
        Pane selectedItem = (Pane) selectionModel.getSelectedItem();
        // 对话信息
        TalkBoxData talkBoxData = (TalkBoxData) selectedItem.getUserData();
        String msg = txt_input.getText();
        if (null == msg || "".equals(msg) || "".equals(msg.trim())) {
            return;
        }
        Date msgDate = new Date();
        // 发送消息
        System.out.println("发送消息：" + msg);
        // 发送事件给自己添加消息
        chatMethod.addTalkMsgRight(talkBoxData.getTalkId(), msg, msgDate, true, true, false);
        txt_input.clear();
    }
}
