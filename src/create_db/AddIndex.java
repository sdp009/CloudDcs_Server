/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package create_db;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author shaun
 */
public class AddIndex {
    //for preparing desktop file list
    public static Path path;

    //for data smoothing
    String TryExec=null;
    String insertNm=null;
    String Exec=null;
    String IconPath=null;
    String Type=null;
    String Comment=null;
    String Terminal=null;
    String NoDisplay=null;
    String Discription=null;
    String SystemName=null;
    List<String> Category = new ArrayList<>();
    List<String> Mime = new ArrayList<>();
    String deskFile;
    String strLine;//path

    private void getComputerName()
    {
        /*
        Map<String, String> env = System.getenv();
        //System.out.println(env);
        if (env.containsKey("COMPUTERNAME"))
            return env.get("COMPUTERNAME");
        else if (env.containsKey("HOSTNAME"))
            return env.get("HOSTNAME");
        else
            return "Unknown Computer";
        */
        try {
            InetAddress addr = java.net.InetAddress.getLocalHost();    
            //System.out.println(addr);
            SystemName = addr.getHostName();    
            //System.out.println("Hostname of system = " + SystemName);
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    }
    
    public void prepareDesktopList() throws IOException{
        this.getComputerName();
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
                "2. sudo locate *.desktop > " + path.toAbsolutePath());
        //Files.write(file, dlist, Charset.forName("UTF-8"));
    }
    
    public void dataSmoothing(){
        FileInputStream fstream,cstream;
        BufferedReader br, cr;        
        
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
                    //reading inside .desktop file        
                    while ((deskFile=cr.readLine()) != null)
                    {
                        //check for attrib only once, hence attrib==null
                        //check for Type=Application, if not then continue        
                        if(Type == null && deskFile.contains("Type=") && deskFile.indexOf("Type=")==0){
                            Type=verify.checkType(deskFile);
                            
                            if(Type.contains("Application")){
                                System.err.println("Invalid application TYPE: "+ this.strLine);
                            }
                        }    
                        //check for Name=String, if not then continue
                        if(insertNm == null && deskFile.contains("Name=") && deskFile.indexOf("Name=")==0)
                            insertNm = verify.checkName(deskFile);
                        
                        //exec,  if not then continue
                        if(Exec == null && deskFile.contains("Exec") && deskFile.indexOf("Exec")==0)
                            Exec = verify.checkExec(deskFile);
                                
                        //icon, optional
                        if(IconPath == null && deskFile.contains("Icon") && deskFile.indexOf("Icon")==0)
                            IconPath = verify.checkIcon(deskFile);
                                
                        //tryexec, optional
                        if(TryExec == null && deskFile.contains("TryExec") && deskFile.indexOf("Exec")==3)
                            TryExec = verify.checkTryExec(deskFile);
                        
                        //comment, optional
                        if(Comment == null && deskFile.contains("Comment=") && deskFile.indexOf("Comment=")==0)
                            Comment = verify.checkComment(deskFile);
                        
                        //terminal, optional
                        if(Terminal == null && deskFile.contains("Terminal=") && deskFile.indexOf("Terminal=")==0)
                            Terminal = verify.checkTerminal(deskFile);
                        
                        //noDisplay, optional
                        if(NoDisplay == null && deskFile.contains("NoDisplay=") && deskFile.indexOf("NoDisplay=")==0){
                            NoDisplay = verify.checkDisplay(deskFile);
                            if(NoDisplay.contains("true") || NoDisplay.contains("yes")){
                                System.out.println(NoDisplay + "Invalid application NoDISPLAY: "+ this.strLine);
                                break;
                            }
                        }
                        //category
                        if(Category.isEmpty() && deskFile.contains("Categories=") && deskFile.indexOf("Categories=")==0){
                            Category = verify.checkCategory(deskFile);
                        }
                        
                        //mime
                        if(Mime.isEmpty() && deskFile.contains("MimeType=") && deskFile.indexOf("MimeType=")==0){
                            Mime = verify.checkCategory(deskFile);
                        }
                        //description
                        
                        //system name
                                
                    }
                    /*System.out.printf("\nName = %s\n",insertNm);
                    System.out.printf("Exec = %s\n",Exec);
                    System.out.printf("IconPath = %s\n",IconPath);
                    System.out.printf("TryExec = %s\n",TryExec);
                    System.out.println("Comment="+Comment);
                    System.out.println("Terminal="+Terminal);
                    System.out.println("NoDisplay="+NoDisplay);
                    System.out.println("Category="+Category);
                    System.out.println("MimeType="+Mime);
                    */
                    if(Exec==null || insertNm==null){
                        System.out.println("Invalid Exec OR Name: "+ this.strLine);
                    }
                    else
                        this.desktopToJson();
                    
                    insertNm = null;
                    Exec = null;
                    IconPath = null;
                    TryExec = null;
                    Comment = null;
                    Terminal = null;
                    NoDisplay = null;
                    Category = new ArrayList<>();
                    Mime = new ArrayList<>();
                    
                }
                catch(IOException e)
                {
                    System.out.println(lineNo + "  Error in opening desktop file:" + strLine);
                }
                //Close the input stream
            lineNo++;    
            }
        }
        catch(IOException e)
        {
            System.err.println("Error in opening DesktopList file: "+lineNo);
        }
    }
    
    public void desktopToJson(){
        try{
            //String[] comments= new String[1];
            //ArrayList <String> comments= new ArrayList<>();
              //  comments.add("testing array list");
            Map<String, Object> json = new HashMap<>();
            //json.put("user","kimchy");
            //json.put("postDate",new Date());
            //json.put("message","trying out Elasticsearch\'s dataformat");
            //json.put("comments", comments);
            
            json.put("name",this.insertNm);
            json.put("comment",this.Comment);
            json.put("exec",this.Exec);
            json.put("tryexec",this.TryExec);
            json.put("path",this.strLine);
            json.put("systemNm",this.SystemName);
            json.put("icon",this.IconPath);
            json.put("terminal",this.Terminal);
            json.put("categories", this.Category);
            json.put("mimeTypes",this.Mime);
            
            /*Gson gson = new Gson(); 
            String toJson = gson.toJson(json); 
            System.out.println(toJson);
            */
            JSONObject map2json = new JSONObject(json);
            //System.out.println("\n" + map2json);
            
            //writing to file****
            path = Paths.get("RawDesktopToJson.json");

            try {
                Files.createFile(path);
            } catch (FileAlreadyExistsException e) {
                //System.err.println("File created: " + e.getMessage());
            }
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(path.toAbsolutePath().toString(),true));
            //writer.append("[");
            writer.append(map2json.toString());
            writer.append(",");
     
            writer.close();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
