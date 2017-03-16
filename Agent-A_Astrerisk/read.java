/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs401.k142113.a1p6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Areeb
 */
public class read {
    private Scanner x;
    BufferedWriter y;
    String m,n,init_1,init_2,goal_1,goal_2;
    Node[][] element;
    
    
    Node predec;
    //private Formatter y;
    
    public void fileOpen(){ //Input file
        try{
            x = new Scanner (new File("C:\\Users\\Areeb\\Documents\\NetBeansProjects\\CS401-K142113-A1P6\\src\\cs401\\k142113\\a1p6\\A1-tc7.txt"));
            
        }
        catch(Exception e){
            System.out.println("error");
        }
    }
    
    public void fileOpenToWrite(){ //Output file
        try{
            y = new BufferedWriter (new FileWriter("C:\\Users\\Areeb\\Documents\\NetBeansProjects\\CS401-K142113-A1P6\\src\\cs401\\k142113\\a1p6\\output file.txt"));
        }
        catch(Exception e){
            System.out.println("error");
        }
    }
    
    public void fileRead() throws IOException{ //Read input file
        String m_col = x.next();
        String n_row = x.next();
        x.nextLine();
        String initial_i = x.next();
        String initial_j = x.next();
        x.nextLine();
        String goal_i = x.next();
        String goal_j = x.next();
        x.nextLine();
        m = m_col;
        n= n_row;
        init_1 = initial_i;
        init_2 = initial_j;
        goal_1 = goal_i;
        goal_2 = goal_j;
        int i=0, j=0;
        element = new Node[Integer.parseInt(m)][Integer.parseInt(n)];
        for(int loop1 = 0; loop1<Integer.parseInt(m);loop1++){
            for(int loop2 = 0; loop2<Integer.parseInt(n);loop2++){
                    element[loop1][loop2] = new Node();
                    for(int temp_i=0; temp_i<8; temp_i++){
                    element[loop1][loop2].neighbors[temp_i] = new Node();
                    element[loop1][loop2].neighbors[temp_i] = null;
                }
            }
        }
        
        
        
        
        while(x.hasNext()){ //Assign neighbors to each node of the maze
            String temp_line = x.nextLine();
            Scanner sa = new Scanner(temp_line);
            while(sa.hasNext()){
                element[i][j].value = sa.next();
                element[i][j].x = i;
                element[i][j].y = j;
                if(i>0 && j>0 && i<Integer.parseInt(m)-1 && j< Integer.parseInt(n)-1){
                    element[i][j].neighbors[0] = element[i+1][j];
                    element[i][j].neighbors[1] = element[i+1][j+1];
                    element[i][j].neighbors[2] = element[i][j+1];
                    element[i][j].neighbors[3] = element[i-1][j+1];
                    element[i][j].neighbors[4] = element[i-1][j];
                    element[i][j].neighbors[5] = element[i-1][j-1];
                    element[i][j].neighbors[6] = element[i][j-1];
                    element[i][j].neighbors[7] = element[i+1][j-1];
                }
                else if(i > 0 && i < Integer.parseInt(m)-1 && j == 0){
                    element[i][j].neighbors[0] = element[i+1][j];
                    element[i][j].neighbors[1] = element[i+1][j+1];
                    element[i][j].neighbors[2] = element[i][j+1];
                    element[i][j].neighbors[3] = element[i-1][j+1];
                    element[i][j].neighbors[4] = element[i-1][j];
                }
                else if(i > 0 && i < Integer.parseInt(m)-1 && j == Integer.parseInt(n)-1){
                    element[i][j].neighbors[0] = element[i+1][j];
                    element[i][j].neighbors[4] = element[i-1][j];
                    element[i][j].neighbors[5] = element[i-1][j-1];
                    element[i][j].neighbors[6] = element[i][j-1];
                    element[i][j].neighbors[7] = element[i+1][j-1];
                }
                else if(j > 0 && j < Integer.parseInt(n)-1 && i == 0){
                    element[i][j].neighbors[0] = element[i+1][j];
                    element[i][j].neighbors[1] = element[i+1][j+1];
                    element[i][j].neighbors[2] = element[i][j+1];
                    element[i][j].neighbors[6] = element[i][j-1];
                    element[i][j].neighbors[7] = element[i+1][j-1];
                }
                else if(j > 0 && j < Integer.parseInt(n)-1 && i == Integer.parseInt(m)-1){
                    element[i][j].neighbors[2] = element[i][j+1];
                    element[i][j].neighbors[3] = element[i-1][j+1];
                    element[i][j].neighbors[4] = element[i-1][j];
                    element[i][j].neighbors[5] = element[i-1][j-1];
                    element[i][j].neighbors[6] = element[i][j-1];
                }
                else if(i == 0 && j == 0){
                    element[i][j].neighbors[0] = element[i+1][j];
                    element[i][j].neighbors[1] = element[i+1][j+1];
                    element[i][j].neighbors[2] = element[i][j+1];
                }
                else if(i == Integer.parseInt(m)-1 && j == Integer.parseInt(n)-1){
                    element[i][j].neighbors[4] = element[i-1][j];
                    element[i][j].neighbors[5] = element[i-1][j-1];
                    element[i][j].neighbors[6] = element[i][j-1];
                }
                else if(i == Integer.parseInt(m)-1 && j == 0){
                    element[i][j].neighbors[2] = element[i][j+1];
                    element[i][j].neighbors[3] = element[i-1][j+1];
                    element[i][j].neighbors[4] = element[i-1][j];
                }
                else if(i == 0 && j == Integer.parseInt(n)-1){
                    element[i][j].neighbors[0] = element[i+1][j];
                    element[i][j].neighbors[6] = element[i][j-1];
                    element[i][j].neighbors[7] = element[i+1][j-1];
                }
                j++;
            }
            i++;
            j=0;
        }
    }
    
    public void A_Asterisk() throws IOException{
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].distance = 1;
        boolean path = true;
        PriorityQueue<Map.Entry<Node, Integer>> queue = new PriorityQueue<>(Comparator.comparing(entry -> entry.getValue())); //Priority queue
        HashMap <Node, Integer> hm = new HashMap<Node, Integer>();
        hm.put(element[Integer.parseInt(init_1)][Integer.parseInt(init_2)], Integer.parseInt(m) - 1);
        //stack.push(element[Integer.parseInt(init_1)][Integer.parseInt(init_2)]);
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].predecessor = null;
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].visited = true;
        for(Map.Entry<Node, Integer> e : hm.entrySet()){
            Node k = e.getKey();
            int v = e.getValue();
            //System.out.println(k.value + " " + v);
            queue.add(e);
                    
            //System.out.println("hello");
        }
        hm.remove(element[Integer.parseInt(init_1)][Integer.parseInt(init_2)], Integer.parseInt(m) - 1);
        
        
        
        int iloop = 0, distance_to_goal = 0, x_to_goal = 0, y_to_goal = 0;
        //Node pushed = element[Integer.parseInt(init_1)][Integer.parseInt(init_2)];
        Node pushed = queue.poll().getKey();
        pushed.distance = 1;
        outerloop: while(pushed.x != Integer.parseInt(goal_1) || pushed.y != Integer.parseInt(goal_2)){
                
                //System.out.println("Node coordinates: " + pushed.x + " " + pushed.y);
                iloop = 0;
                
                while(iloop < 8){
                    if(pushed.neighbors[iloop] == null){
                        iloop++;
                    }
                    else if(pushed.neighbors[iloop].value.equals("1")){
                        iloop++;
                    }
                    else if(pushed.neighbors[iloop].visited == true){
                        iloop++;
                    }
                    else{
                        pushed.neighbors[iloop].predecessor = pushed;
                        pushed.neighbors[iloop].distance = pushed.neighbors[iloop].predecessor.distance + 1;
                        pushed.neighbors[iloop].visited = true;
                        //System.out.println("x: " + pushed.neighbors[iloop].x + " y: " + pushed.neighbors[iloop].y + " dist: " + pushed.neighbors[iloop].distance + " predec: " + pushed.neighbors[iloop].predecessor.x + " " + pushed.neighbors[iloop].predecessor.y);
                        x_to_goal = pushed.neighbors[iloop].x;
                        y_to_goal = pushed.neighbors[iloop].y;
                        do{
                            if(x_to_goal < Integer.parseInt(goal_1) && y_to_goal > Integer.parseInt(goal_2)){ //Move diagonal in lower negative direction
                                x_to_goal++;
                                y_to_goal--;
                                distance_to_goal++;
                //                System.out.println("operation 1");
                            }
                            else if(x_to_goal > Integer.parseInt(goal_1) && y_to_goal < Integer.parseInt(goal_2)){ //Move diagonal in upward positive side
                                x_to_goal--;
                                y_to_goal++;
                                distance_to_goal++;
                //                System.out.println("operation 2");
                            }
                            else if(x_to_goal > Integer.parseInt(goal_1) && y_to_goal > Integer.parseInt(goal_2)){ //move diagonal in upward negative direction
                                x_to_goal--;
                                y_to_goal--;
                                distance_to_goal++;
                //                System.out.println("operation 3");
                            }
                            else if(x_to_goal < Integer.parseInt(goal_1) && y_to_goal < Integer.parseInt(goal_2)){ //move diagonal in lower positive direction
                                x_to_goal++;
                                y_to_goal++;
                                distance_to_goal++;
                //                System.out.println("operation 4");
                            }
                            else if(x_to_goal == Integer.parseInt(goal_1) && y_to_goal < Integer.parseInt(goal_2)){ //move horizontally in positive direction
                                y_to_goal++;
                                distance_to_goal++;
                //                System.out.println("operation 5");
                            }
                            else if(x_to_goal == Integer.parseInt(goal_1) && y_to_goal > Integer.parseInt(goal_2)){ //move horizontally in negative direction
                                y_to_goal--;
                                distance_to_goal++;
                //                System.out.println("operation 6");
                            }
                            else if(x_to_goal < Integer.parseInt(goal_1) && y_to_goal == Integer.parseInt(goal_2)){ //move vertically in negative direction
                                x_to_goal++;
                                distance_to_goal++;
                //                System.out.println("operation 7");
                            }
                            else if(x_to_goal > Integer.parseInt(goal_1) && y_to_goal == Integer.parseInt(goal_2)){ //move vertically in positive direction
                                x_to_goal--;
                                distance_to_goal++;
                //                System.out.println("operation 8");
                            }
                //            System.out.println("x: " + x_to_goal);
                //            System.out.println("y: " + y_to_goal);
                //            System.out.println("dist: " + distance_to_goal);
                        }
                        while(x_to_goal != Integer.parseInt(goal_1) || y_to_goal != Integer.parseInt(goal_2));
                //        System.out.println("putting in hm");
                        hm.put(pushed.neighbors[iloop], distance_to_goal + pushed.neighbors[iloop].distance);
                        //pushed.neighbors[iloop].distance = pushed.distance + 1;
                        //pushed.neighbors[iloop].predecessor = pushed;
                        for(Map.Entry<Node, Integer> e : hm.entrySet()){
                            Node k = e.getKey();
                            int v = e.getValue();
                //            System.out.println(k.value + " " + v);
                            queue.add(e);
                            
                //            System.out.println("hello");
                        }
                        hm.remove(pushed.neighbors[iloop], distance_to_goal);
                //        System.out.println("Node x: " + pushed.neighbors[iloop].x);
                //        System.out.println("Node x: " + pushed.neighbors[iloop].y);
                //        System.out.println("Node dist: " + distance_to_goal);
                        //stack.push(pushed.neighbors[iloop]);
                        //pushed.neighbors[iloop].distance = pushed.distance + 1;
                        //pushed.neighbors[iloop].predecessor = pushed;
                        
                        //pushed = pushed.neighbors[iloop];
                        //iloop = 0;
                        //break;
                        iloop++;
                    }
                    distance_to_goal = 0;
                        x_to_goal = 0;
                        y_to_goal = 0;
                //        System.out.println("iloop: " + iloop);
                }
                
                /*
                for(Integer abc : hm.values()){
                    System.out.println(abc);
                }
                
                for(Map.Entry<Node, Integer> e : hm.entrySet()){
                    Node k = e.getKey();
                    int v = e.getValue();
                    System.out.println(k.value + " " + v);
                    queue.add(e);
                    
                    System.out.println("hello");
                }
                */
                //pushed = queue.poll().getKey();
                //Node pushed_parent = pushed;
                if(queue.isEmpty()){
                    path = false;
                    break outerloop;
                }
                pushed = queue.poll().getKey();
                //pushed.predecessor = pushed_parent;
                //pushed.distance = pushed.predecessor.distance + 1;
                
        }
        if(path == false){
            System.out.println("Goal is not reachable");
        }
        else{
            print(pushed);
            System.out.println(pushed.distance);
            y.write(Integer.toString(pushed.distance));
        }
        
    }
    
    public void print(Node printNode) throws IOException{
        if(printNode.predecessor == null){
            System.out.println(printNode.x + " " + printNode.y);
            y.write(printNode.x + " " + printNode.y);
            y.newLine();
        }
        else{
            
            print(printNode.predecessor);
            System.out.println(printNode.x + " " + printNode.y);
            y.write(printNode.x + " " + printNode.y);
            y.newLine();
        }
    }
    
    public void fileClose() throws IOException{
        x.close();
        y.close();
    }
}
