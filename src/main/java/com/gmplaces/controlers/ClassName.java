package com.gmplaces.controlers;


public final  class ClassName {

    private ClassName(){}

    public static String getCurrentClassName(){
        try{
            throw new RuntimeException();
        }
        catch (RuntimeException exp){
            return exp.getStackTrace()[1].getClassName();
        }
    }


}
