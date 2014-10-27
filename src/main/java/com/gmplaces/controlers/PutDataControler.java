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

import static com.gmplaces.controlers.Utils.parseExceptions;


public class PutDataControler extends HttpServlet {

    final Logger logger = Logger.getLogger(PutDataControler.class);

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        ServletContext ctx = request.getServletContext();
        IDataService dataService = (IDataService)ctx.getAttribute("dataservice");
        String result;
        try{
            request.setCharacterEncoding("UTF-8");
            logger.debug("input data lat:" + request.getParameter("lat") );
            double lat = Double.valueOf(request.getParameter("lat"));
            logger.debug("input data lng:" + request.getParameter("lng") );
            double lng = Double.valueOf(request.getParameter("lng"));
            logger.debug("input data description:" + request.getParameter("description") );
            String desc = String.valueOf(request.getParameter("description"));
            Address addr = new Address(lat,lng,desc);
            result  = dataService.putData(addr);
            logger.debug("result:" + result);
        } catch (Exception exp) {
            result = "ERROR";
            logger.info(parseExceptions(exp));
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
