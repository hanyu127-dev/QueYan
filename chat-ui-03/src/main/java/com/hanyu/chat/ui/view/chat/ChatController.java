package com.hanyu.chat.ui.view.chat;

public class ChatController extends ChatInit implements IChatMethod{
    private ChatView chatView;
    private ChatEventDefine chatEventDefine;



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
}
