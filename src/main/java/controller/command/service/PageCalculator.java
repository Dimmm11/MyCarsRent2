package controller.command.service;

public class PageCalculator {
    public int getNumPages(int listSize){
        int res=0;
        if(listSize%3==0){
            res=listSize/3;
        }else {
            res = listSize/3+1;
        }
        System.out.println("RES in method getNumPages: "+res);
        return res;
    }
}
