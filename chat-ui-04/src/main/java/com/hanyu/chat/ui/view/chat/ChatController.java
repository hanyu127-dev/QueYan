package com.hanyu.chat.ui.view.chat;

import com.hanyu.chat.ui.util.CacheUtil;
import com.hanyu.chat.ui.util.Ids;
import com.hanyu.chat.ui.view.chat.data.GroupsData;
import com.hanyu.chat.ui.view.chat.data.RemindCount;
import com.hanyu.chat.ui.view.chat.data.TalkData;
import com.hanyu.chat.ui.view.chat.element.group_bar_chat.ElementInfoBox;
import com.hanyu.chat.ui.view.chat.element.group_bar_chat.ElementTalk;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;

public class ChatController extends ChatInit implements IChatMethod{
    private ChatView chatView;
    private ChatEventDefine chatEventDefine;

    public ChatController(IChatEvent chatEvent) {
        super(chatEvent);
    }

    @Override
    public void initView() {
        chatView = new ChatView(this,null);
    }

    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this,null,this);
    }

    @Override
    public void doShow() {
        super.show();
    }


    @Override
    public void setUserInfo(String userId, String userNickName, String userHead) {
        // 展示头像，
        super.userId = userId;
        super.userNickName = userNickName;
        super.userHead = userHead;
        bar_portrait.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
    }

    // 在对话栏中填充对象
    @Override
    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) {
        ListView<Pane> talkList = getUI("talkList",ListView.class);
        // 判断对话框是否填充过该对象
        ElementTalk elementTalk = CacheUtil.talkMap.get(talkId);
        if (null!=elementTalk){
            // 如果没有填充过该对象,判断是否有节点
            Node talkNode  = talkList.lookup("#" + Ids.ElementTalkId.createTalkPaneId(talkId));
            if (null!=talkNode){
                talkList.getItems().add(talkIdx,elementTalk.getPane());
            }
            if (selected){
                // 设置选中
                talkList.getSelectionModel().select(elementTalk.getPane());
            }
            return;
        }
        // 如果没有
        // 初始化对话框元素
        ElementTalk talkElement = new ElementTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);
        CacheUtil.talkMap.put(talkId, talkElement);

        // 填充到对话框
        ObservableList<Pane> talkListItems = talkList.getItems();
        Pane talkElementPane  = talkElement.getPane();
        if (talkIdx >= 0) {
            talkListItems.add(talkIdx, talkElementPane);  // 添加到第一个位置
        } else {
            talkListItems.add(talkElementPane);           // 顺序添加
        }
        // 是否被选中
        if (selected){
            talkList.getSelectionModel().select(talkElementPane);
        }
        // 对话框元素点击事件
        talkElementPane.setOnMousePressed(event -> {
            System.out.println("点击对话框：" + talkName);
        });
        // 鼠标事件[移入/移出]
        talkElementPane.setOnMouseEntered(event -> {
            // 设置删除按钮可见
            talkElement.getDelete().setVisible(true);
        });
        talkElementPane.setOnMouseExited(event -> {
            talkElement.getDelete().setVisible(false);
        });

        // 从对话框中删除
        talkElement.getDelete().setOnMouseClicked(event -> {
            System.out.println("删除对话框：" + talkName);
            talkList.getItems().remove(talkElementPane);
            talkElement.clearMsgSketch();
        });

        // 对话框元素点击事件
        talkElementPane.setOnMousePressed(event -> {
            // 填充消息栏
            fillInfoBox(talkElement, talkName);
            // 清除消息提醒
            Label msgRemind = talkElement.getMsgRemind();
            msgRemind.setUserData(new RemindCount(0));
            msgRemind.setVisible(false);
        });

    }
    // 好友的消息（个人）
    @Override
    public void addTalkMsgUserLeft(String talkId, String msg, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk elementTalk = CacheUtil.talkMap.get(talkId);
        ListView<Pane> infoBoxList = elementTalk.getInfoBoxList();
        TalkData talkUserData = (TalkData) infoBoxList.getUserData();
        Pane left = new ElementInfoBox().left(talkUserData.getTalkName(), talkUserData.getTalkHead(), msg);
        // 消息填充
        infoBoxList.getItems().add(left);
        // 滚动条
        infoBoxList.scrollTo(left);
        elementTalk.fillMsgSketch(msg, msgData);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(0, elementTalk.getPane(), elementTalk.getMsgRemind(), idxFirst, selected, isRemind);
        // 填充对话框聊天窗口
        fillInfoBox(elementTalk, talkUserData.getTalkName());
    }


    // 好友的消息（群组）
    @Override
    public void addTalkMsgGroupLeft(String talkId, String userId, String userNickName, String userHead, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        // 自己的消息抛弃
        if (super.userId.equals(userId)) return;
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        if (null == talkElement) {
            GroupsData groupsData = (GroupsData) getUI(Ids.ElementTalkId.createFriendGroupId(talkId), Pane.class).getUserData();
            if (null == groupsData) return;
            addTalkBox(0, 1, talkId, groupsData.getGroupName(), groupsData.getGroupHead(), userNickName + "：" + msg, msgDate, false);
            talkElement = CacheUtil.talkMap.get(talkId);
            // 事件通知(开启与群组发送消息)
            System.out.println("事件通知(开启与群组发送消息)");
        }
        ListView<Pane> listView = talkElement.getInfoBoxList();
        TalkData talkData = (TalkData) listView.getUserData();
        Pane left = new ElementInfoBox().left(userNickName, userHead, msg);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);
        talkElement.fillMsgSketch(userNickName + "：" + msg, msgDate);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(1, talkElement.getPane(), talkElement.getMsgRemind(), idxFirst, selected, isRemind);
        // 填充对话框聊天窗口
        fillInfoBox(talkElement, talkData.getTalkName());
    }

    // 自己的消息
    @Override
    public void addTalkMsgRight(String talkId, String msg, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.getInfoBoxList();
        Pane right = new ElementInfoBox().right(userNickName, userHead, msg);
        // 消息填充
        listView.getItems().add(right);
        // 滚动条
        listView.scrollTo(right);
        talkElement.fillMsgSketch(msg, msgData);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(0, talkElement.getPane(), talkElement.getMsgRemind(), idxFirst, selected, isRemind);
    }

    // 在消息栏填充左右的列表视图（ListView）
    private void fillInfoBox(ElementTalk elementTalk, String talkName) {
        // 判断是否已经填充
        String talkId = elementTalk.getPane().getUserData().toString();
        Pane info_pane_box = getUI("info_pane_box",Pane.class);
        String boxUserId = (String) info_pane_box.getUserData();
        // 判断是否已经填充[talkId]，当前已填充则返回
        if (talkId.equals(boxUserId)) return;

        // 没有填充
        ListView<Pane> listView = elementTalk.getInfoBoxList();
        info_pane_box.setUserData(talkId);
        info_pane_box.getChildren().clear();
        info_pane_box.getChildren().add(listView);
        // 对话框名称
        Label info_name = getUI("info_name", Label.class);
        info_name.setText(talkName);
    }
}
