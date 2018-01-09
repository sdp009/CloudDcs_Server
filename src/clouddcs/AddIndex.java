/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clouddcs;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shaun
 */
public class AddIndex {
    
    public static Path path;

    public void prepareDesktopList() throws IOException{
        /*
        //run locate *.desktop
        SystemExec exec = new SystemExec();
        
        ArrayList<String> dlist = new ArrayList<String>();
        dlist = exec.cmd("sudo locate ettercap.desktop");
        */
       
        //create empty file
        path = Paths.get("DesktopList.txt");

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.err.println("File created: " + e.getMessage());
        }
        
         System.out.println("Since, SystemExec.java is not working as expected,\n"+
                "Run following cmd:");
        System.out.println("1. sudo updatedb;\n" +
                "2. sudo locate *.desktop > " + path.toAbsolutePath() + ";");
        //Files.write(file, dlist, Charset.forName("UTF-8"));
    }
    
    public void dataSmoothing(){
        FileInputStream fstream,cstream;
        BufferedReader br, cr;
        
        String deskFile,insert_query;
        String strLine;//path
        
        String TryExec=null;
        String insertNm=null;
        String Exec=null;
        String IconPath=null;
        String Type=null;
        String Comment=null;
        String Terminal=null;
        String NoDisplay=null;
        
        ArrayList<String> Category = new ArrayList<>();
        ArrayList<String> Mime = new ArrayList<>();
    
        int lineNo=1;
        int maxLen=0;
        int flag=0;
        // Open the file
        try
        {
            DataVerification verify = new DataVerification();
            
            //open file
            fstream = new FileInputStream(path.toAbsolutePath().toString());
            br = new BufferedReader(new InputStreamReader(fstream));
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // open .desktop file
                try 
                {   
                    cstream = new FileInputStream(strLine);
                    cr = new BufferedReader(new InputStreamReader(cstream));
                            
                    while ((deskFile=cr.readLine()) != null)
                    {
                        //check for Type=Application, if not then continue        
                        if(Type == null)
                            Type=verify.checkType(deskFile);
                        //check for Name=String, if not then continue
                        if(insertNm == null)
                        insertNm = verify.checkName(deskFile);
                        
                        //exec,  if not then continue
                        if(Exec == null)
                        Exec = verify.checkExec(deskFile);
                                
                        //icon, optional
                        if(IconPath == null)
                        IconPath = verify.checkIcon(deskFile);
                                
                        //tryexec, optional
                        if(TryExec == null)
                        TryExec = verify.checkTryExec(deskFile);
                        
                        //comment, optional
                        if(Comment == null)
                            Comment = verify.checkComment(deskFile);
                        
                        //terminal, optional
                        if(Terminal == null)
                            Terminal = verify.checkTerminal(deskFile);
                        
                        //noDisplay, optional
                        if(NoDisplay == null)
                            NoDisplay = verify.checkDisplay(deskFile);
                        
                        //category
                        
                        //mime
                        
                        //description
                                
                    }
                    System.out.printf("Name = %s\n",insertNm);
                    System.out.printf("Exec = %s\n",Exec);
                    System.out.printf("IconPath = %s\n",IconPath);
                    System.out.printf("TryExec = %s\n\n",TryExec);
                    System.out.println("Comment="+Comment);
                    System.out.println("Terminal="+Terminal);
                    System.out.println("NoDisplay="+NoDisplay);
                    insertNm = null;
                    Exec = null;
                    IconPath = null;
                    TryExec = null;
                }
                catch(IOException e)
                {
                    System.out.println("Error in opening desktop file:" + strLine);
                }
                //Close the input stream
            }
        }
        catch(IOException e)
        {
            System.err.println("Error in opening DesktopList file");
        }
    }
    
    public void desktopToJson(){
        try{
            //String[] comments= new String[1];
            ArrayList <String> comments= new ArrayList<>();
                comments.add("testing array list");
            Map<String, Object> json = new HashMap<>();
            json.put("user","kimchy");
            json.put("postDate",new Date());
            json.put("message","trying out Elasticsearch\'s dataformat");
            json.put("comments", comments);
            
            Gson gson = new Gson(); 
            String toJson = gson.toJson(json); 
            System.out.println(toJson);
            
            JSONObject map2json = new JSONObject(json);
            System.out.println("\n" + map2json);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
