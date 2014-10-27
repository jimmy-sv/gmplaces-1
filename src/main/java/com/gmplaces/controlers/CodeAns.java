package com.gmplaces.controlers;


public class CodeAns {

    public String getCodeAns() {
        return codeAns;
    }

    public void setCodeAns(String codeAns) {
        this.codeAns = codeAns;
    }

    private String codeAns;

    public CodeAns (String mesg){
        this.codeAns = mesg;
    }

}
