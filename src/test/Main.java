/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import crud.ProduitCrud;
import entities.Produit;

/**
 *
 * @author kheir
 */
public class Main {
    public static void main(String[] args){
        
        Produit p =new Produit(4,"dfdf","dfddf","fffff","fgh","fgh","fghj");
        ProduitCrud pc =new ProduitCrud();
        //pc.ajouterproduit(p);
        System.out.println(pc.affich());
    }
    
    
}
