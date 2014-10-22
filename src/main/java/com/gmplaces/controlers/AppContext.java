package com.gmplaces.controlers;


import com.gmplaces.models.DataServiceImplStub;
import com.gmplaces.models.IDataService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


public class AppContext implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        IDataService dataService = new DataServiceImplStub();
        ctx.setAttribute("dataservice",dataService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        /*NOP*/
    }
}
