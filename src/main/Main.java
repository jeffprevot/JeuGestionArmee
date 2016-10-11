/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import control.MainControler;

/**
 *
 * @author jeff
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        runUI();
    }

    public static void runUI() {

        MainControler mainControler = new MainControler();
        mainControler.menuGeneral();

    }

}
