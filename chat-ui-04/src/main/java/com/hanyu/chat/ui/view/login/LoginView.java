package com.hanyu.chat.ui.view.login;

/**
 * 窗体展示（这部分内容当前类主要承担着窗体初始化和窗体的事件的一个构造函数，对于后续其他复杂页面初始化更多的预定义元素才会更有用。）
 */
public class LoginView {
    protected LoginInit loginInit;
    protected  ILoginEvent loginEvent;

    public LoginView(LoginInit loginInit, ILoginEvent loginEvent) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
    }
}
