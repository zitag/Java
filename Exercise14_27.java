//HW3-Ch14 - I/O
import java.awt.Graphics;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
   
public class Exercise14_27 extends JFrame {
    public static void main(String[] args) throws Exception {
		Scanner consoleInput = new Scanner(System.in);
		System.out.println("Enter the filename ");
		// Check if source file exists
		String filename = consoleInput.nextLine();
		int[][] position = null;
		File file = new File(filename);
		ArrayList<int[]> edge = new ArrayList<int[]>();   

		// confirm existence of the file
		if (!file.exists()) {			
			System.out.println("That is an invalid file name: " + file);
		} else {
            System.out.println("File " + file + " is valid.");
        }
            BufferedReader br = new BufferedReader(new FileReader(filename));
			// read first line
            String line = br.readLine();                        
            int vertices = Integer.parseInt(line);  
            position = new int[vertices][2];
               int i=0;
			// read each line and output values to the console
            while((line = br.readLine()) != null) {              
                System.out.print(line+" \n");
                   String[] s = line.split(" ");                  
                       int[] points = new int[s.length - 3];         
                       position[i][0]=Integer.parseInt(s[1]);
                       position[i][1]=Integer.parseInt(s[2]);
                       for(int j = 3; j < s.length; j++)             
                          points[j-3] = Integer.parseInt(s[j]);      
                         edge.add(points);                                   
                    i++;        
            }
            GraphView gv = new GraphView(position,edge);			
            JFrame frame = new JFrame("Show Graph");
            frame.setContentPane(gv);			
            frame.setSize(500,500);			
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);            
            frame.setVisible(true);
    }
} 

    class GraphView extends JPanel {
        private int[][] position;
        private ArrayList<int[]> edge;
        //empty graphview constructor
        public GraphView() {
        }
       
        //overloaded graphview  with specified position and edge
        public GraphView(int[][] position, ArrayList<int[]> edge) {
           this.position = position;
           this.edge = edge;   
        }
            
		protected void paintComponent(Graphics g) {
            super.paintComponent(g);  	
		
       Graphics2D g2 = (Graphics2D) g;
            for(int i = 0; i < edge.size(); i++){                   
               Point2D.Double point = new Point2D.Double(position[i][0],position[i][1]);
			   
			   //Print edge number in red color and draw a circle on the edge
               g2.setColor(Color.RED);	              			   
               g2.drawString(String.valueOf(i), position[i][0] - 15 , position[i][1] );				   
               g2.fillOval ( position[i][0] - 3, position[i][1] - 3, 6, 6 );
			       //Draw lines
                  for(int j = 0;j < edge.get(i).length; j++){				    
                     g2.draw(new Line2D.Double(position[i][0],position[i][1],position[edge.get(i)[j]][0],position[edge.get(i)[j]][1]));
                  }
            }       
       }
    }	