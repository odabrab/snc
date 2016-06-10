/*
 *********************************************************************
 *********************************************************************
 *******	LICENSE (LICENCA): GNU GPL v2
 *******
 *******	File "Peer.java".
 *******
 *******	Copyright (c) 2016 Anderson Misson
 *******                       Danilo de Araujo Vasconcellos
 *******                       Marcio Barbado, Jr.
 *******                       Marcos Roberto de Menezes
 *******                       Roger Coudounarakis
 *******
 *******	Bachelor's degree in Computer Science, Brazil
 *******
 *******	This program is free software; you can redistribute it
 *******	and/or modify it under the terms of the GNU General
 *******	Public License as published by the Free Software
 *******	Foundation; version 2 of the License.
 *******
 *******	This program is distributed in the hope that it will be
 *******	useful, but WITHOUT ANY WARRANTY; without even the
 *******	implied warranty of MERCHANTABILITY or FITNESS FOR A
 *******	PARTICULAR PURPOSE.  See the GNU General Public License
 *******	for more details.
 *******
 *******	You should have received a copy of the GNU General
 *******	Public License along with this program; if not, write to
 *******	the Free Software Foundation, Inc., 59 Temple Place -
 *******	Suite 330, Boston, MA  02111-1307, USA.
 *********************************************************************
 *********************************************************************
 */

/**
 * 
 */
package br.com.bdslabs.snc.dev;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * <p>
 *   Port numbers between 0 and 1,023 are reserved for privileged
 *   users, that is, super user or root associated to standard
 *   services, such as e-mail, FTP, and HTTP.
 *   In selecting a port number, choose one that is greater than
 *   1,023.
 *   The class PrintStream has methods for displaying textual
 *   representation of Java primitive data types.
 * </p>
 * 
 * @author		Anderson Misson				<missonsama@gmail.com>
 * @author		Danilo A. Vasconcellos
 * @author		Marcio Barbado, Jr.			<marcio.barbado@gmail.com>
 * @author		Marcos Roberto de Menezes
 * @author		Roger Coudounarakis
 * @see			SimpleNetworkCommunicator
 *
 */
public class Peer{

/*
 *********************************************************************
 *******  ATTRIBUTE(S)/FIELD(S)
 *******
 *******  Declarations.
 *********************************************************************
 */

/**
 * 
 */
	int[]		intRemotePortNumbers;

/**
 * 
 */
	String[]	strRemoteAddresses;


/*
 *********************************************************************
 *******	CONSTRUCTOR METHOD(S)
 *******
 *********************************************************************
 */

/**
 * 
 * @throws IOException 
 * @throws UnknownHostException 
 * 
 */
	public Peer() throws UnknownHostException, IOException{
		
		this(new String[1]);
	}

/**
 * 
 * @throws IOException 
 * @throws UnknownHostException 
 * 
 */
	public Peer(String[] strRemotePeers) throws UnknownHostException, IOException{

		this(strRemotePeers, new int[strRemotePeers.length]);
	}

/**
 * 
 * @throws IOException 
 * @throws UnknownHostException 
 * 
 */
	public Peer(String str_RemotePeer, int int_remote_port) throws UnknownHostException, IOException{
			
		this.strRemoteAddresses[0]		= str_RemotePeer;
		this.intRemotePortNumbers[0]	= int_remote_port;
	} // Peer() constructor method end.
	
/**
 * 
 * @throws IOException 
 * @throws UnknownHostException 
 * 
 */
	public Peer(String[] strRemotePeers, int[] intRemotePorts) throws UnknownHostException, IOException{
		
		this.strRemoteAddresses		= strRemotePeers;
		this.intRemotePortNumbers	= intRemotePorts;
	} // Peer() constructor method end.

/**
 * Performs an autoboxing iteration.
 * Converts an Integer array to an int array.
 * 
 * @throws IOException 
 * @throws UnknownHostException 
 * 
 */
	public Peer(String[] strRemotePeers, Integer[] remotePortsIntegerArray) throws UnknownHostException, IOException{
		
		final int[] int_remotePortsArray;
		
		int_remotePortsArray = new int[remotePortsIntegerArray.length];
		
        for (int i = 0; i < remotePortsIntegerArray.length; i++) {
        	
        	int_remotePortsArray[i] = remotePortsIntegerArray[i].intValue();
        }
		
		this.strRemoteAddresses		= strRemotePeers;
		this.intRemotePortNumbers	= int_remotePortsArray;
	} // Peer() constructor method end.
	
/*
 *********************************************************************
 *******	INITIALIZATION(S)
 *******
 *********************************************************************
 */

/*
 *********************************************************************
 *******	METHOD(S)
 *******
 *********************************************************************
 */

/**
 * Initially, any server blocks and listens.
 * Opens socket(s) and stream(s), communicate, and then close them
 * all.
 * Method chooses a TCP stream over UDP.
 * @return 
 * 
 */
	public int playServer() throws UnknownHostException, IOException{

/*
 *********************************************************************
 *******  METHOD DECLARATION(S)
 *******
 *********************************************************************
 */
		
		int					int_error_code;

		/* TCP stream(s) used to read text received from server. */
		DataInputStream		serverDataInputStream;
		
		/* TCP stream(s) used to send text to server. */
		DataOutputStream	serverDataOutputStream;
		PrintStream			printStream;
		
		ServerSocket		serverSocket;
		Socket				newServerSocket;
		StringBuilder		messageFromClientStringBuilder;

/*
 *********************************************************************
 *******  METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */
		
		int_error_code					= -1;
		
		serverDataInputStream			= null;
		
		serverDataOutputStream			= null;
		printStream						= null;
		
		/* Opens a server socket and starts listening (accepting). */
		serverSocket					= new ServerSocket(0);
		newServerSocket					= serverSocket.accept();
		
		messageFromClientStringBuilder	= new StringBuilder();

/*
 *********************************************************************
 *******	METHOD BODY
 *******
 *********************************************************************
 */

		try{

			/* Opens stream to read from client. */
			serverDataInputStream						= new DataInputStream(newServerSocket.getInputStream());
			/* Opens stream to write to client. */
			serverDataOutputStream						= new DataOutputStream(newServerSocket.getOutputStream());
			/* Opens an optional stream to write to client. */
			printStream									= new PrintStream(newServerSocket.getOutputStream());

			/* Streams and sockets ready, and message successfully received. */
			if (serverSocket != null && newServerSocket != null && serverDataInputStream != null && (serverDataOutputStream != null || printStream != null) && !messageFromClientStringBuilder.append(serverDataInputStream.readUTF()).equals("null")){

				System.out.print("socket address:\t" + newServerSocket.getLocalSocketAddress().toString() + "\n" +
						         "Inet address:\t" + newServerSocket.getLocalAddress().toString() + "\n" +
						         "port number:\t" + newServerSocket.getLocalPort() + "\n" +
						         "\n" +
						         "A remote peer, suposedly acting as a client, said:\n" +
						         messageFromClientStringBuilder + "\n" +
						         "\n" +
						         "REMOTE PEER INFORMATION\n" +
						         "remote socket address:\t" + newServerSocket.getRemoteSocketAddress().toString() + "\n" +
						         "remote Inet address:\t" + newServerSocket.getInetAddress().toString() + "\n" +
						         "remote port number:\t" + newServerSocket.getPort() + "\n");
				serverDataOutputStream.writeUTF("This message is a DataOutputStream server response confirming your message was received.");

				/* Close stream(s). */
				serverDataInputStream.close();
				serverDataOutputStream.close();
				printStream.close();
			}
		}

		catch(UnknownHostException unknownHostException){

			System.err.print("\nException 005: unknown host.\n" +
					         "Unknown host exception: " + unknownHostException.getMessage() + "\n");
			unknownHostException.printStackTrace();
			int_error_code = 5;
		}

		catch(IOException ioException){

			System.err.print("\nException 004: probaby there was an I/O problem with the server socket.\n" +
			                 "Could not connect remote peer.\n" +
					         "I/O exception: " + ioException.getMessage() + "\n");
			ioException.printStackTrace();
			int_error_code = 4;
		}
		
		finally{
			
			/* Close sockets. */
			serverSocket.close();
			newServerSocket.close();
		}
		
		return int_error_code;
	} // playServer() method end.

/**
 * Tries to establish a connection.
 * DataInputStream allows client to read from server.
 * 
 */
	public void playClient(StringBuilder msgStringBuilder) throws UnknownHostException, IOException{

/*
 *********************************************************************
 *******  METHOD DECLARATION(S)
 *******
 *********************************************************************
 */

		ArrayList<Socket>	socketArrayList;
		
		/* TCP stream(s) used to read text received from server. */
		DataInputStream		clientDataInputStream;
		
		/* TCP stream(s) used to send text to server. */
		DataOutputStream	clientDataOutputStream;
		PrintStream			clientPrintStream;
		// PrintWriter			clientPrintWriter;

		StringBuilder		messageFromServerStringBuilder;
		StringBuilder		messageToServerStringBuilder;

/*
 *********************************************************************
 *******  METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */
		
		clientDataInputStream			= null;
		
		clientDataOutputStream			= null;
		clientPrintStream				= null;
		// clientPrintWriter			= null;

		socketArrayList					= new ArrayList<Socket>();
		messageFromServerStringBuilder	= new StringBuilder();
		messageToServerStringBuilder	= msgStringBuilder;

/*
 *********************************************************************
 *******	METHOD BODY
 *******
 *********************************************************************
 */
		
		try{
			
			System.out.print("\nType your message and hit enter to send it to the group.\n");

			for (int int_remote_peers = 0; int_remote_peers < this.strRemoteAddresses.length; int_remote_peers++){
				
				/* Opens a client socket. */
				socketArrayList.add(new Socket(this.strRemoteAddresses[int_remote_peers], this.intRemotePortNumbers[int_remote_peers]));
				/* Opens stream to read from server. */
				clientDataInputStream			= new DataInputStream(socketArrayList.get(int_remote_peers).getInputStream());
				/* Opens an optional stream to read from server in a buffered fashion. */
				// inBufferedReader				= new BufferedReader(new InputStreamReader(clientSocket[int_remote_peers].getInputStream()));
		        /* Opens stream to write to server. */
				clientDataOutputStream			= new DataOutputStream(socketArrayList.get(int_remote_peers).getOutputStream());
				/* Opens an optional stream to write to server. */
				clientPrintStream				= new PrintStream(socketArrayList.get(int_remote_peers).getOutputStream());
				/* Opens an optional stream to write to server. It uses bytes instead of characters. */
				// clientPrintWriter			= new PrintWriter(socketArrayList.get(int_remote_peers).getOutputStream(), true);
				
				/* Streams and sockets ready. */
				if (socketArrayList.get(int_remote_peers) != null && clientDataInputStream != null && (clientDataOutputStream != null || clientPrintStream != null)){

					clientDataOutputStream.writeUTF(messageToServerStringBuilder.toString());
					/* Alternative. */
					// clientDataOutputStream.writeChars(System.in.toString());
					
					System.out.print("\nA remote peer, suposedly acting as a server, said:\n" +
							         messageFromServerStringBuilder.append(clientDataInputStream.readUTF()) + "\n");
				}
				
				/* Close stream(s). */
				clientDataInputStream.close();
				clientDataOutputStream.close();
				clientPrintStream.close();
				
				/* Close socket(s). */
				socketArrayList.get(int_remote_peers).close();
			}
		}

		catch(UnknownHostException unknownHostException){
			
			System.err.print("\nException 002: unknown remote host.\n" +
                             "Unknown host exception: " + unknownHostException.getMessage() + "\n");
			unknownHostException.printStackTrace();
			System.exit(2);
		}
		
		catch(IOException ioException){
			
			System.err.print("\nException 003: probaby there was an I/O problem with the client socket.\n" +
         	                 "Could not connect remote peer.\n" +
                             "I/O exception: " + ioException.getMessage() + "\n");
			ioException.printStackTrace();
            System.exit(3);
		}
	} // playClient() method end.

/**
 * Receives a file.
 * 
 */
	public void playLeecher(StringBuilder pathToReceiveStringBuilder){

		
	} // playLeecher() method end.

/**
 * Delivers a file.
 * 
 */
	public void playSeeder(StringBuilder pathToDeliverStringBuilder){
			
			
	} // playSeeder() method end.
} // Peer class end.
