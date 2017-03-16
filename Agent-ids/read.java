/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs401.k142113.a1p4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public void fileOpen(){ //input file
        try{
            x = new Scanner (new File("C:\\Users\\Areeb\\Documents\\NetBeansProjects\\CS401-K142113-A1P4\\src\\cs401\\k142113\\a1p4\\A1-in1.txt"));
            
        }
        catch(Exception e){
            System.out.println("error");
        }
    }
    
    public void fileOpenToWrite(){ //output file
        try{
            y = new BufferedWriter (new FileWriter("C:\\Users\\Areeb\\Documents\\NetBeansProjects\\CS401-K142113-A1P4\\src\\cs401\\k142113\\a1p4\\output file.txt"));
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
            }
        }
        
        
        while(x.hasNext()){     //Assigning neighbors of each mnode in the maze
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
            //System.out.println("next line");
            i++;
            j=0;
            //x.nextLine();
        }
    }
    
    public void iterative_deepening() throws IOException{
        //Queue<Node> queue = new LinkedList<Node>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(element[Integer.parseInt(init_1)][Integer.parseInt(init_2)]);
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].predecessor = null;
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].visited = true;
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].distance = 1;
        int iloop = 0;
        Node pushed = element[Integer.parseInt(init_1)][Integer.parseInt(init_2)];
        
        //while(pushed.x != Integer.parseInt(goal_1)){
          //  while(pushed.y != Integer.parseInt(goal_2)){
        int check = 0, final_check = 0, store = Integer.MAX_VALUE, counter = 0;
        Node goal = null;
        
        
        //outerloop:while(check <= final_check){
        outerloop: while(counter < Integer.parseInt(m) * Integer.parseInt(n)){ //While all nodes are not discovered
            
            //pushed = element[Integer.parseInt(init_1)][Integer.parseInt(init_2)];
            //stack.push(pushed);
            outerloop2: while(check <= final_check){ //chech is the iterator, final_check is the depth
                //System.out.println(check + " " + final_check);
            if(pushed.x == Integer.parseInt(goal_1) && pushed.y == Integer.parseInt(goal_2)){
                if(pushed.distance < store){
                    goal = pushed;
                    //System.out.println("goal distance: " + goal.distance);
                    store = pushed.distance;
                    check++;
                }
            
                //break outerloop;
            }
                //System.out.println("check: " + check + " " + " check2: " + final_check);
            //System.out.println(pushed.x + " " + pushed.y);
            while(iloop < 8){
                    if(pushed.neighbors[iloop] == null){
                        iloop++;
                    }
                
                    else if(pushed.neighbors[iloop].visited == true){
                        iloop++;
                    }
                
                    else if(pushed.neighbors[iloop].value.equals("1")){
                        iloop++;
                    }
                    else{
                        if(check < final_check){
                            //System.out.println("hello2");
                        stack.push(pushed.neighbors[iloop]);
                        check++;
                        pushed.neighbors[iloop].distance = pushed.distance + 1;
                        pushed.neighbors[iloop].predecessor = pushed;
                        pushed.neighbors[iloop].visited = true;
                        pushed = pushed.neighbors[iloop];
                        //System.out.println(pushed.x + " " + pushed.y);
                        iloop = 0;
                        break;
                        }
                        else{
                            iloop++;
                        }
                    }
                    
                }
                //System.out.println("broken");
                if(iloop == 8){
                    iloop = 0;
                    if(pushed.predecessor == null){
                        //System.out.println("done");
                        break outerloop2;
                    }
                    pushed = pushed.predecessor;
                    stack.pop();
                    
                    check--;
                    
                    
                }
            
            } //outerloop2
            //System.out.println("hello");
            while(!stack.isEmpty()){
                stack.pop();
            }
            pushed = element[Integer.parseInt(init_1)][Integer.parseInt(init_2)];
            //System.out.println(pushed.x + " " + pushed.y);
            stack.push(pushed);
            for(int final_loop = 0; final_loop < Integer.parseInt(m); final_loop++){
                for(int final_loop2 = 0; final_loop2 < Integer.parseInt(n); final_loop2++){
                    element[final_loop][final_loop2].visited = false;
                    element[final_loop][final_loop2].distance = 0;
                }
            }
            pushed.visited = true;
        
            final_check++;
        
            check = 0;
            counter++;
            //System.out.println();
        } //outerloop
        if(goal ==  null){
            System.out.println("Goal not reachable");
        }
        else{
            print(goal);
            System.out.println(store);
            y.write(Integer.toString(store));
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
