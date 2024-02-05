package com.hanyu.chat.ui.util;

import com.hanyu.chat.ui.view.chat.element.group_bar_chat.ElementTalk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储对话栏元素的
 */
public class CacheUtil {

    // 对话框组
    public static Map<String, ElementTalk> talkMap = new ConcurrentHashMap<String, ElementTalk>(16);

}
