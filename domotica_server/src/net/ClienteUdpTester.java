package net;

import java.net.* ;

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
         // Convert the arguments first, to ensure that they are valid
         InetAddress host = InetAddress.getByName( "192.168.1.43") ;
         int port         = Integer.parseInt( "9999" ) ;

         // Construct the socket
         socket = new DatagramSocket() ;

         // Construct the datagram packet
         byte [] data = "01999mensagem".getBytes() ;
         DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;

         // Send it
         socket.send( packet ) ;

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