package com.hanyu.chat.ui.view.chat;

public class ChatView {
    private ChatInit chatInit;
    private IChatEvent iChatEvent;

    public ChatView(ChatInit chatInit, IChatEvent iChatEvent) {
        this.chatInit = chatInit;
        this.iChatEvent = iChatEvent;
    }
}
