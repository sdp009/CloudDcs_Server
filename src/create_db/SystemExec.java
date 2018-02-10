/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package create_db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author shaun
 */
public class SystemExec {
    
    public ArrayList <String> cmd(String args) throws IOException 
    { 
        //int a;
        ArrayList <String> opt= new ArrayList<String>();
        String line ="";
        BufferedReader reader;

            try 
            {  
                Process p=Runtime.getRuntime().exec(args); 
                //p.waitFor(); 
                reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 

                while(reader.readLine() != null)
                {
                    line=reader.readLine();
                    opt.add(line);
                }
            }    
            catch(IOException e1)
            { e1.printStackTrace(); } 

            System.out.println("output list :  " + opt);

        return opt; 
    } 
}
