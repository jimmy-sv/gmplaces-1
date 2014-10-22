package com.gmplaces.controlers;


import com.gmplaces.models.Address;
import com.gmplaces.models.IDataService;
import com.google.gson.Gson;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PutDataControler extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        ServletContext ctx = request.getServletContext();
        IDataService dataService = (IDataService)ctx.getAttribute("dataservice");
        String result;
        try{
            double lat = Double.valueOf(request.getParameter("lat"));
            double lng = Double.valueOf(request.getParameter("lng"));
            String desc = String.valueOf(request.getParameter("description"));
            Address addr = new Address(lat,lng,desc);

            result  = dataService.putData(addr);
        } catch (Exception exp) { result = "Fatal ERROR";}

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
