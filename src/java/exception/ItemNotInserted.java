/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;

/**
 *
 * @author savita
 */

/* Class to catch Exception. */
public class ItemNotInserted extends Exception {
    public ItemNotInserted () {
    }
    
    public ItemNotInserted (String msg) {
        super (msg);
    }
}
