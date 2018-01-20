package xupt.se.ttms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.User;
import xupt.se.ttms.service.EmployeeSrv;
import xupt.se.ttms.service.UserSrv;


@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 8786335034409045572L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> list = new UserSrv().findUserAll();
        List<Employee> emplist = new EmployeeSrv().findEmployeeAll();


        Employee emp = null;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 设置jsp页面编码
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String user = null;
        String pass = null;
        String emp_type = null;
        String head_path = null;
        String result = "no";
        int type = 0;

        if (username == null || username.equals("")) {
            result = "";
        } else {
            for (User u : list) {
                if (u.getEmp_no().equals(username)) {
                    user = u.getEmp_no();
                    pass = u.getEmp_pass();
                    type = u.getEmp_type();
                    if (type == 1) {
                        emp_type = "管理员";
                    } else {
                        emp_type = "普通用户";
                    }
                    head_path = u.getHead_path();
                    emp = new EmployeeSrv().findEmployeeByNo(user);
                    result = "yes";
                }
            }
        }

        if (password == null || password.equals("")) {
            out.write(result);
            out.close();
            return;
        }

//        JSONArray jsonArray = JSONArray.fromObject(list);

        if (username != null && username.equals(user) && password.equals(pass)) {
            // 转发携带原request封装的数据
            request.getSession().setAttribute("loginflag", "ok");
//            request.getSession().setAttribute("userlist", list);
            request.getSession().setAttribute("emplist", emplist);
//            request.getSession().setAttribute("userlist", jsonArray.toString());
            request.getSession().setAttribute("empNo", username);
            request.setAttribute("password", password);
            request.setAttribute("empType", emp_type);
            request.setAttribute("headPath", head_path);
            request.setAttribute("emp", emp);
            if (type == 1) {
                request.getSession().setAttribute("auth", "ok");
            } else {
                request.getSession().setAttribute("auth", "no");
            }
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } else {
            request.setAttribute("desc", "用户名或密码错误!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
