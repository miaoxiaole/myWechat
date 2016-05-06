package org.liufeng.course.message.resp;

/**
 * 文本消息
 *
 * @author wuyue
 * @date 2016-05-05
 */
public class TextMessage extends BaseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}