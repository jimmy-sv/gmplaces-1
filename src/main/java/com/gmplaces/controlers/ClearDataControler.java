package com.gmplaces.controlers;

import com.gmplaces.models.IDataService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.gmplaces.controlers.Utils.parseExceptions;


public class ClearDataControler extends HttpServlet {

    final Logger logger = Logger.getLogger(ClearDataControler.class);

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        ServletContext ctx = request.getServletContext();
        IDataService dataService = (IDataService)ctx.getAttribute("dataservice");
        String result;

        try{

            result = dataService.clearData();
            logger.debug("result: "+result);

        } catch (Exception exp) {

            logger.error(parseExceptions(exp));

            result = "ERROR";

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
