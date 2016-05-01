package teste;

import java.io.IOException;
import java.io.PrintStream;
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
   private final static String enderecoServidor = "127.0.0.1";
   private final static int portaServidor = 9991;
   private static int contSensor = 0;
   private static int contAtuador = 0;
   private static int contArduino = 0;
   
   
   public Socket getSocketTcp(String endereco, int porta) throws UnknownHostException, IOException
   {
	   Socket cliente = new Socket(endereco,porta);
	   return cliente;
   }
   
   
   public void enviaTcp(String mensatem, Socket sock) throws IOException
   {
	   PrintStream saida = new PrintStream(sock.getOutputStream());
	   saida.println(mensatem);
	   saida.close();
   }
   
   
   public void testeEnvioTcp() throws NullPointerException
   {
	   Socket cliente = null;
	   String msg = null;
	   
	   try
	   {
		   cliente = getSocketTcp(enderecoServidor, portaServidor);
		   System.out.println("Conectado Servidor: IP[" + enderecoServidor + "] " + "PORTA:[" + portaServidor + "]");
		   msg = criaMensagem();
		   
		   if(msg!=null)
		   {
			   System.out.println("Enviando mensagem:[" + msg + "]");
			   enviaTcp(msg, cliente);
			   System.out.println("Enviado com SUCESSO");
		   }
		   else
		   {
			   System.out.println("Mesnagem NULA");
			   throw new NullPointerException();
		   }  
	   }
	   catch (UnknownHostException e)
	   {
		   System.out.println("Erro ao tentar conectar: IP[" + enderecoServidor + "] " + "PORTA:[" + portaServidor + "]");
		   e.printStackTrace();
	   } 
	   catch (IOException e)
	   {
		   System.out.println("Erro ao tentar enviar mensatem[" + msg + "]");
		   e.printStackTrace();
	   }
	   finally
	   {
		   if(cliente!=null)
		   {
			   try 
			   {
				   cliente.close();
			   } 
			   catch (IOException e)
			   {
				   System.out.println("Erro ao tentar fechar socket:\n" + e.getMessage());
			   }
		   }
	   }
	   
	   
   }
   
   public String criaMensagem()
   {
	   String msg = JOptionPane.showInputDialog("mensagem:", "010" + new DecimalFormat("00").format(contArduino++) + "quarto    "
   	 		+ "02" 
   			+ new DecimalFormat("00").format(contSensor++) + "02luz       "
   		  + new DecimalFormat("00").format(contSensor++) + "03temp      "
   	 		
   			+ "02" 
   			+ new DecimalFormat("00").format(contAtuador++) + "02lampada   "
   		  + new DecimalFormat("00").format(contAtuador++) + "03ventilador"
   			 
   			 );
	   
	  return msg;
   }
   
   
   public static void main( String args[] )
   {
      
	   ClienteUdpTester teste = new ClienteUdpTester();
	   
	   while(true)
	   {
		   try
		   {
			   teste.testeEnvioTcp();
		   }
		   catch (Exception e)
		   {
			   break;
		   } 
	   }
		   
	   
	   
	   // Check the arguments

      /*DatagramSocket socket = null ;

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
      }*/
   }
}