package org.liufeng.course.message.req;

/**
 * @author wuyue
 * @data 2016/5/5
 */
public class TextMessage extends BaseMessage {

    //消息内容
    private String Content;

    public String getContent(){
        return Content;
    }

    public void setContent(String content){
        Content = content;
    }
}
