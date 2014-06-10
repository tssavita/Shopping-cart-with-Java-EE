package exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author savita
 */

/* Class to catch exception. */
public class ItemsNotRetrieved extends Exception {
    public ItemsNotRetrieved() {
    }

    public ItemsNotRetrieved (String msg) {
        super (msg);
    }
}
