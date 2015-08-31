import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet implements Servlet{

    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("SimpleServlet#init");
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("SimpleServlet#service");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("<h1>Run SimpleServlet#service()<h1>");
    }


    public ServletConfig getServletConfig() {
        return null;
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}