/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package create_db;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author shaun
 */
public class DataVerification {
    
    String checkType(String deskFile){
        String Type=null;
        //if(deskFile.contains("Type=") && deskFile.indexOf("Type=")==0){
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
                    
            Type = deskFile.substring(index, indexL);    
        //}
        return Type;
    }
    
    String checkName(String deskFile){
        String insertNm = null;
        //if(deskFile.contains("Name=") && deskFile.indexOf("Name=")==0)
        //{
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
                    
            insertNm = deskFile.substring(index, indexL);
            insertNm=insertNm.replaceAll("\"", "\\\\\""); 
            insertNm=insertNm.toLowerCase();
        //}
        //System.out.printf("Name = %s\n",insertNm);
        return insertNm;
    }
    
    String checkExec(String deskFile){
        String Exec = null;
            //if(deskFile.contains("Exec") && deskFile.indexOf("Exec")==0)
            {
                int index=deskFile.indexOf("=")+1;
                int indexL=deskFile.length();
                    
                Exec = deskFile.substring(index, indexL);
                Exec=Exec.replaceAll("\"", "\\\\\"");
            }
        //System.out.printf("Exec = %s\n",Exec);
        return Exec;
    }
    
    String checkIcon(String deskFile){
        String IconPath = null;
        //if(deskFile.contains("Icon") && deskFile.indexOf("Icon")==0)
        {
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
                    
            IconPath = deskFile.substring(index, indexL);
            IconPath=IconPath.replaceAll("\"", "\\\\\"");   
        }
        //System.out.printf("IconPath = %s\n",IconPath);                    
        return IconPath;
    }
    
    String checkTryExec(String deskFile){
        String TryExec = null;
        //if(deskFile.contains("TryExec") && deskFile.indexOf("Exec")==3)
        {
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
                    
            TryExec = deskFile.substring(index, indexL);
            TryExec=TryExec.replaceAll("\"", "\\\\\"");                        
        } 
        //System.out.printf("TryExec = %s\n\n",TryExec);
        return TryExec;
    }
    
    //comment, optional
    String checkComment(String deskFile){
        String Comment=null;
        //if(deskFile.contains("Comment=") && deskFile.indexOf("Comment=")==0){
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
                    
            Comment = deskFile.substring(index, indexL);
            Comment=Comment.replaceAll("\"", "\\\\\"");  
        //}
        return Comment;
    }
                        
    //terminal, optional
    String checkTerminal(String deskFile){
        String term=null;
        //if(deskFile.contains("Terminal=") && deskFile.indexOf("Terminal=")==0){
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
            
            term = deskFile.substring(index, indexL);
        //}
        return term;
    }
    //noDisplay, optional
    String checkDisplay(String deskFile){
        String disp=null;
        //if(deskFile.contains("NoDisplay=") && deskFile.indexOf("NoDisplay=")==0){
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
            
            disp = deskFile.substring(index, indexL);
        //}
        return disp;
    }
    //category
    List<String> checkCategory(String deskFile){
        List<String> Cat;
        String substr="";
        
        //if(deskFile.contains("Categories=") && deskFile.indexOf("Categories=")==0){
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
            
            substr = deskFile.substring(index, indexL);
            substr=substr.replaceAll("\"", "\\\\\"");  

        //}
        //System.out.println("cat substr: "+substr);
        Cat = Arrays.asList(substr.split(";"));
        
        return Cat;
    }

    //mime
    List<String> checkMime(String deskFile){
        List<String> Mime;
        String substr="";
        
        //if(deskFile.contains("MimeType=") && deskFile.indexOf("MimeType=")==0){
            int index=deskFile.indexOf("=")+1;
            int indexL=deskFile.length();
            
            substr = deskFile.substring(index, indexL);
            substr=substr.replaceAll("\"", "\\\\\"");  
            
        //}
        //System.out.println("Mime substr: "+substr);
        
        Mime = Arrays.asList(substr.split(";"));
        
        return Mime;
    }                    
    
}
