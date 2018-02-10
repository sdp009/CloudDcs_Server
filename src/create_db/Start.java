/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package create_db;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author shaun
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        AddIndex add = new AddIndex();
        
        //prepare desk filelist
        add.prepareDesktopList();
        
        //prepare json data
        add.dataSmoothing();
        
        //add index
        //add.desktopToJson();
        
       
    }
    
}
