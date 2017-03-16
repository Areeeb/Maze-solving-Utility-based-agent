/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs401.k142113.a1p3;
import java.util.*;
import java.io.*;

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
            x = new Scanner (new File("C:\\Users\\Areeb\\Documents\\NetBeansProjects\\CS401-K142113-A1P3\\src\\cs401\\k142113\\a1p3\\A1-in1.txt"));
            
        }
        catch(Exception e){
            System.out.println("error");
        }
    }
    
    public void fileOpenToWrite(){ //Output file
        try{
            y = new BufferedWriter (new FileWriter("C:\\Users\\Areeb\\Documents\\NetBeansProjects\\CS401-K142113-A1P3\\src\\cs401\\k142113\\a1p3\\output file.txt"));
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
        
        
        while(x.hasNext()){ //Assigning neigbors to nodes in the maze
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
    
    public void dfs() throws IOException{
        //Queue<Node> queue = new LinkedList<Node>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(element[Integer.parseInt(init_1)][Integer.parseInt(init_2)]);
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].predecessor = null;
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].visited = true;
        element[Integer.parseInt(init_1)][Integer.parseInt(init_2)].distance = 1;
        int iloop = 0;
        Node pushed = element[Integer.parseInt(init_1)][Integer.parseInt(init_2)];
        outerloop: while(!stack.isEmpty()){
            
        while(pushed.x != Integer.parseInt(goal_1) || pushed.y != Integer.parseInt(goal_2)){
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
                        stack.push(pushed.neighbors[iloop]);
                        pushed.neighbors[iloop].distance = pushed.distance + 1;
                        pushed.neighbors[iloop].predecessor = pushed;
                        pushed.neighbors[iloop].visited = true;
                        
                        pushed = pushed.neighbors[iloop];
                        iloop = 0;
                        break;
                    }
                }
                if(iloop == 8){
                    
                    pushed = pushed.predecessor;
                    stack.pop();
                    if(stack.isEmpty()){
                        break outerloop;
                    }
                    iloop = 0;
                }
            
        }
        if(pushed.x == Integer.parseInt(goal_1) || pushed.y == Integer.parseInt(goal_2)){
            
            break outerloop;
        }
        
        }
        if(stack.isEmpty()){
            System.out.println("Goal is not reachable!!!");
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

