import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
import java.io.*; 
 
public class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    String str = "initialize";
    String a[];
   
  
    Compteur c=new Compteur();
    public String getstr() {
    	return " set index_compteur="+c.getIndex_compteur() +" where Num_secteur="+c.getNum_secteur()+" "+"and Num_compteur="+c.getNum_compteur() ;
    }
 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            try{  
          		Class.forName("com.mysql.jdbc.Driver");  
          			Connection con=DriverManager.getConnection(  
          			"jdbc:mysql://localhost:3306/radee_ta?autoReconnect=true&useSSL=false","root","root");  
          		Statement stmt=(Statement)con.createStatement();
        
       		  while((str = br.readLine())!=null ){
       			
       			String b[]=str.split(";");
       			System.out.println("le numero de secteur est: "+b[0]);
       			System.out.println("le numero de compteur est: "+b[1]);
       			System.out.println("la consomation est: "+b[2]);
       			 
          c.setNum_secteur(Integer.parseInt(b[0]));
          c.setNum_compteur(Integer.parseInt(b[1]));
          c.setIndex_compteur(Integer.parseInt(b[2]));
         
           
      	  stmt.executeUpdate("update Compteur"+this.getstr());
       
                    
                    
       		  }
          con.close();
    		
              }catch(Exception e){ System.out.println(e);} 
         
            
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }
}
      
    
