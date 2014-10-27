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
<<<<<<< HEAD
import static com.gmplaces.controlers.Utils.parseExceptions;
=======
import static com.gmplaces.controlers.ClassName.getCurrentClassName;
>>>>>>> 842b895293614b6a1ecc0a62c74af4a71dfc2037


public class GetDataControler extends HttpServlet {

<<<<<<< HEAD
    final Logger logger = Logger.getLogger(GetDataControler.class);
=======
    final Logger logger = Logger.getLogger(getCurrentClassName());
>>>>>>> 842b895293614b6a1ecc0a62c74af4a71dfc2037

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        ServletContext ctx = request.getServletContext();
        IDataService dataService = (IDataService)ctx.getAttribute("dataservice");
        try{
            List<Address> list = dataService.getData();
<<<<<<< HEAD

            logger.debug("list size: "+String.valueOf(list.size()));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(list));
       } catch (Exception exp) {
            logger.error(parseExceptions(exp));
=======
            logger.debug("GetDataControler getData(), list size: "+String.valueOf(list.size()));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(list));
        } catch (Exception exp) {
            logger.info("ERROR: GetDataControler" + exp.getMessage());
>>>>>>> 842b895293614b6a1ecc0a62c74af4a71dfc2037
        }
    }


    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
    {

        doGet(request, response);
    }



}
