package com.gmplaces.controlers;

import com.gmplaces.models.Address;
import com.gmplaces.models.IDataService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static com.gmplaces.controlers.ClassName.getCurrentClassName;

/**
 * Created with IntelliJ IDEA.
 * User: svertepniy
 * Date: 22.10.14
 * Time: 9:41
 * To change this template use File | Settings | File Templates.
 */
public class ClearDataControler extends HttpServlet {

    final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        ServletContext ctx = request.getServletContext();
        IDataService dataService = (IDataService)ctx.getAttribute("dataservice");
        String result;

        try{
            result = dataService.clearData();
        } catch (Exception exp) {
           logger.info("ERROR: ClearDataControler "+exp.getMessage());
           result = "FATAL ERROR";
        }

        CodeAns ans = new CodeAns(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(ans));
    }


    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
    {

        doGet(request, response);
    }

}
