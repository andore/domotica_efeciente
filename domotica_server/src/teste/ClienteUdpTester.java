package teste;

import java.net.* ;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

/**
 *  A simple datagram client
 *  Shows how to send and receive UDP packets in Java
 *
 *  @author  P. Tellenbach,  http://www.heimetli.ch
 *  @version V1.00
 */
public class ClienteUdpTester
{
   private final static int PACKETSIZE = 100 ;

   public static void main( String args[] )
   {
      // Check the arguments

      DatagramSocket socket = null ;

      try
      {
         
    	 String ip = JOptionPane.showInputDialog("Digite o endereco", "localhost");
    	  
    	  // Convert the arguments first, to ensure that they are valid
         InetAddress host = InetAddress.getByName(ip) ;
         int port         = Integer.parseInt( "9999" ) ;

         // Construct the socket
         socket = new DatagramSocket() ;

         // Construct the datagram packet
         int contSensor = 0;
         int contAtuador = 0;
         int contArduino = 0;
         while(true)
         {
        	     		 
        	 String msg = JOptionPane.showInputDialog("mensagem:", "010" + new DecimalFormat("00").format(contArduino) + "quarto    "
        	 		+ "02" 
        			+ new DecimalFormat("00").format(contSensor+=1) + "02luz       "
        		  + new DecimalFormat("00").format(contSensor+=1) + "03temp      "
        	 		
        			+ "02" 
        			+ new DecimalFormat("00").format(contAtuador+=1) + "02lampada   "
        		  + new DecimalFormat("00").format(contAtuador+=1) + "03ventilador"
        			 
        			 );
        	 {
        		 if(msg==null)
        		 {
        			 break;
        		 }
        	 }
        	 byte [] data = msg.getBytes() ;
             DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;

             // Send it
             socket.send( packet ) ;
             
             contArduino++;
             
             
             //Set a receive timeout, 2000 milliseconds
             socket.setSoTimeout( 2000 ) ;

            // Prepare the packet for receive
            packet.setData( new byte[PACKETSIZE] ) ;

            // Wait for a response from the server
            socket.receive( packet ) ;

            String resposta =  new String(packet.getData());
            
            // Print the response
            System.out.println("Recebido do Servidor:");
            System.out.println("Operacao:[" + resposta.substring(0, 2) + "]");
            System.out.println("Mensagem:[" + resposta.substring(2) + "]");
         }
         socket.close() ;
         
      }
      catch( Exception e )
      {
         System.out.println( e ) ;
      }
      finally
      {
         if( socket != null )
            socket.close() ;
      }
   }
}