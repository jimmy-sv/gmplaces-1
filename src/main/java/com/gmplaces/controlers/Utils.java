package com.gmplaces.controlers;


import java.io.PrintWriter;
import java.io.StringWriter;

public final  class Utils {

    private Utils(){}


    public static String parseExceptions(Exception exp){

        StringWriter errors = new StringWriter();
        exp.printStackTrace(new PrintWriter(errors));
        return errors.toString();

    }

}
