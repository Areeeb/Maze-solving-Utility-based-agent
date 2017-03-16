/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs401.k142113.a1p5;

/**
 *
 * @author Areeb
 */
public class Node {
    public int x ;
    public int y;
    public String value = "";
    boolean visited = false;
    public Node predecessor;
    public int distance;
    
    public Node[] neighbors = new Node [8]; //Frontier Nodes
    
    
    
}
