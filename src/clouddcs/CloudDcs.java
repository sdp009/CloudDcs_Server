/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clouddcs;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 *
 * @author shaun
 */
public class CloudDcs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        AddIndex add = new AddIndex();
        add.prepareDesktopList();
        add.dataSmoothing();
        //add.desktopToJson();
    }
    
}
