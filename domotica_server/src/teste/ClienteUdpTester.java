package teste;

import java.net.* ;

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
         
         while(true)
         {
        	 String msg = JOptionPane.showInputDialog("mensagem:", "01001quarto    010201luz       010201lampada   ");
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
         }
         


         // Set a receive timeout, 2000 milliseconds
         //socket.setSoTimeout( 2000 ) ;

         // Prepare the packet for receive
         //packet.setData( new byte[PACKETSIZE] ) ;

         // Wait for a response from the server
         //socket.receive( packet ) ;

         // Print the response
         //System.out.println( new String(packet.getData()) ) ;
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