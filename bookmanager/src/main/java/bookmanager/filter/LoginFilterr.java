package bookmanager.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dela on 11/30/17.
 */

// 对于想要在Spring中使用过滤器, 就要继承OncePerRequestFilter
// OncePerRequestFilter, 顾名思义, 就是每个请求只过一遍这个过滤器
// 写完过滤器, 记得要去web.xml中配置
//@WebFilter("/auth/*")
public class LoginFilterr extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String LOGIN_PAGE = "/bookmanager"; //未登录首页
        String uri = httpServletRequest.getRequestURI(); // 请求的URI
//        String[] notFilterUri = {"/bookmanager", "label", "img", "js", "css", "login", "font-awesome-4.7.0"}; // 不过滤的URI数组
        String[] notFilterUri = {"login"}; // 不过滤的URI数组
        boolean doFilter = true; // 是否过滤的标志
        Object session = null;

        // 也可以设定包括固定字符串的URI才会被过滤

        for (String s : notFilterUri) {
            System.out.println(uri);
             //如果当前请求的uri包含不过滤的URI数组中的URI, 则不执行过滤
            if (uri.indexOf(s) != -1) {
                System.out.println("notfilter:" + uri);
                System.out.println(uri.indexOf(uri));
                doFilter = false;
                break;
            } else {
                System.out.println("uri: " + uri);
            }
        }

        // 执行过滤
        if (doFilter) {
            // 从session中获取用户的登录令牌
            System.out.println("执行过滤");

            try {
//                System.out.println("getSession(uid) :" + httpServletRequest.getSession(false).getAttribute("uid"));
                session = httpServletRequest.getSession(false).getAttribute("uid");
                System.out.println("session" + session);

            } catch (NullPointerException e) {
            } finally {
                if (null == session) {

                    System.out.println("session uid为空");


                    // 如果当前的操作的用户没有登录令牌, 那就弹出弹框提示重新登录, 并跳转到未登录页面
                    // 设置request和response的字符集, 防止乱码
                    httpServletRequest.setCharacterEncoding("UTF-8");
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    // 打印登录弹框
                    PrintWriter out = httpServletResponse.getWriter();
                    StringBuilder builder = new StringBuilder();

                    builder.append("<script language=\"javascript\">");
                    builder.append("alert(\"非法访问！\");");
                    builder.append("top.location='");
                    builder.append(LOGIN_PAGE);
                    builder.append("';");
                    builder.append("</script>");

//                    builder.append("<script type=\"text/javascript\">");
//                    builder.append("alert('登录已过期, 返回首页重新登录!')");
//                    builder.append("window.yop.location.href='");
//                    builder.append(LOGIN_PAGE);
//                    builder.append("';");
//                    builder.append("</script>");

                    System.out.println(builder.toString());

//                    out.print("hello world");

                    out.print(builder.toString());
                } else {
                    // 如果session中有当前操作的用户的登录令牌, 则继续执行后面的servlet
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }
            }
//                System.out.println("session==null:" + session == null);

        } else {
            // 如果不执行过滤, 则继续执行后面的servlet
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
