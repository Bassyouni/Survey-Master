package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Model.Router;
import java.lang.String;
import java.util.HashMap;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<a href=\"Intro.jsp\"></a>\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Survey</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        ");

            Router router = new Router();
            String SessionManager = "SessionManager";
            String cookieName = "MyCurrentSession";
            Cookie cookies [] = request.getCookies ();
            Cookie myCookie = null;           
            
            if (cookies != null)
            {
                for (int i = 0; i < cookies.length; i++) 
                {
                    if (cookies [i].getName().equals (cookieName))
                    {
                        myCookie = cookies[i];
                        break;
                    }
                    if (cookies[i].getName().equals("userNotFound"))
                    {
                        out.print(router.showIncorrenctUsernameOrPassword());
                        Cookie cookie = cookies[i];
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                    }
                }
            }

            //First time
            if (myCookie == null)
            {
                out.print(router.userFirstTime(application, SessionManager));
            }
            else // Not First time
            {
                //Does the sessionManger object exists?
                if (application.getAttribute(SessionManager) == null)
                {
                    myCookie.setValue(null);
                    myCookie.setMaxAge(0);
                    response.addCookie(myCookie);
                    out.print(router.userFirstTime(application, SessionManager));
                }
                else
                {
                    String sessionID = myCookie.getValue();
                    HashMap<String, HttpSession> sessionMangerHash = (HashMap<String, HttpSession>)application.getAttribute(SessionManager);
                    HttpSession userSession =  sessionMangerHash.get(sessionID);

                    //is there a session with sessionID retrived from the cookie
                    if (userSession == null)
                    {
                        myCookie.setValue(null);
                        myCookie.setMaxAge(0);
                        response.addCookie(myCookie);
                        out.print(router.userFirstTime(application, SessionManager));
                    }
                    else
                    {
                        session = userSession;
                        response.sendRedirect("Home.jsp");           
                    }

                }
            }
            
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
