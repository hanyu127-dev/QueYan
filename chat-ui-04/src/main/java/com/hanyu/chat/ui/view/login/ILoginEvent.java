package com.hanyu.chat.ui.view.login;

/**
 * 窗体事件接口（具体实现交给调用方）
 */
public interface ILoginEvent {
    /**
     * 登陆验证
     * @param userId        用户ID
     * @param password  用户密码
     */
    void doLoginCheck(String userId, String password);
}
