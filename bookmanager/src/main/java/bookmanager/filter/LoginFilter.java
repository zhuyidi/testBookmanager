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
 * Created by dela on 1/18/18.
 */

// 对于想要在Spring中使用过滤器, 就要继承OncePerRequestFilter
// OncePerRequestFilter, 顾名思义, 就是每个请求只过一遍这个过滤器

@WebFilter("/auth/*")
public class LoginFilter extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                FilterChain filterChain) throws ServletException, IOException {
        String LOGIN_PAGE = "/bookmanager";  // 登录的uri
        String uri = httpServletRequest.getRequestURI();
        Object sessionUid = null;

        try {
            sessionUid = httpServletRequest.getSession(false).getAttribute("uid");
        } catch (NullPointerException e) {
        } finally {
            if(null == sessionUid) {

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

                out.print(builder.toString());
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
