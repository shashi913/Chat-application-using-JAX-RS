/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.practicalexamfinal2024;


/**<QUESTION:Import necessary libraries (3 marks)
*/
 //PLACE YOUR CODES HERE
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;




public class ChatClient {
    
    /**<QUESTION: set up a logger for the ChatClient class (1 marks)
    */
    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());

    public static void main(String[] args) {
        try {
            // Set up file logging for the client
            FileHandler fileHandler = new FileHandler("client_log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            /**<QUESTION:
            - Connect the client to the server (2 marks)
                - You need to create a new object of Socket and call it as socket  
            */
             //PLACE YOUR CODES HERE
            Socket socket = new Socket("localhost", 12345);
            
            
            

             
             
             
             
            /**<QUESTION: Set up a new BufferedReader (3 marks) */
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); ///////////////////////////////////*************\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            /**<QUESTION: Set up a new PrintWriter (2 marks)*/
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Enter your username: ");
            String userName = reader.readLine();
            writer.println(userName);

      
            /**<QUESTION:
            - Start a thread to read messages from the server  (3 marks)
                - you can the ReadFromServer constructor
            */
            Thread readThread = new Thread(new ReadFromServer(socket));
            readThread.start();

            String message;
            
            /**<QUESTION:
            - Using a while loop with condition true, Read messages from the user and send them to the server (3 marks)
                - You nee
                - You may use BufferedReader object to invoke readLine() method
                - Then Using the PrintWriter object, you need to print the message 
            */
            
            //PLACE YOUR CODES HERE
            while(true){
                try{
                    //BufferedReader readeFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //String ServerMessage = readeFromServer.readLine();
                    //logger.log(Level.INFO, ServerMessage);
                    
                    System.out.println("Enter your message: ");
                    String messageFromUser = reader.readLine();
                    writer.println(messageFromUser);
                    
                }catch(IOException e){
                    logger.log(Level.SEVERE, "Error occured : " + e.getMessage(),e);
                }
            }
            
            
            
            
            
            /**<QUESTION:
            catch general exception (1 mark)
            */
        } catch (IOException e) {
            System.err.println("Error in the client: " + e.getMessage());
        }
    }
}

class ReadFromServer implements Runnable {
    /**< QUESTION: Set up a Logger for the read-from-server thread (1 mark)*/
    
    private static final Logger logger = Logger.getLogger(ReadFromServer.class.getName());
    
    
    private Socket socket;

    /**< QUESTION: Create a constructor of ReadFromServer class (2 marks)
        - you must use an instance of Socket as its parameter
    */
    //PLACE YOUR CODES HERE
    
    public ReadFromServer(Socket socket){
        this.socket = socket;
    }
    
    
 
    

   
    
    /****< QUESTION:
    - create a method called run that does not return anything (2 mark)
        - Inside the method, define a try-catch block (1 mark)
        - Inside the try block, do the followings: 
            - set up a BufferedReader to Read messages from the server (3 marks)
            - Create a new String variable called serverMessage (1 mark)
            - while the the reader reads the lines of serverMessage and 
                it is not null, print the server message - this must be done using a while loop (2 marks):
                - create a print statement and print serverMessage (1 mark)
        - Define a catch block with general exception (2 mark)
            - log a message using severe level like "Error reading from server: " (1 mark)>          
    */
    //PLACE YOUR CODES HERE
    
    @Override
    public void run(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverMessage;
            
            while((serverMessage = reader.readLine()) != null){
                System.out.println(serverMessage);
            }
            
        }catch(IOException e){
            logger.log(Level.SEVERE, "Error reading from server: "+e.getMessage(), e);
        }
    }
    
    
    
    

}
