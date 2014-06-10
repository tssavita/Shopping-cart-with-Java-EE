/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author savita
 */
public class ImageConversion {
    
    public static byte[] InputStreamToByte(InputStream fis) throws FileNotFoundException {
        
        System.out.println ("Entered utils package\n");
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        byte[] buf = new byte[10000];
        
        try {
            
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
            
            byte[] bytes = bos.toByteArray();
            return bytes;
            
        } 
        catch (IOException ex) {
            System.out.println ("Caught at inputStreamtobytes");
            return null;
        }
    }

    
}
