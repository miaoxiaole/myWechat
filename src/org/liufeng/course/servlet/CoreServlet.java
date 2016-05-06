package org.liufeng.course.servlet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.MusicMessage;
import org.liufeng.course.message.resp.NewsMessage;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.service.CoreService;
import org.liufeng.course.util.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 核心请求处理类
 *
 * Created by wuyue on 2016/5/5.
 */

public class CoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * 确认请求来自服务器
     *
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }

        out.close();
        out =null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //调用核心业务类接收消息，处理消息
        String respMessage = CoreService.processRequest(request);

        //响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }


}
