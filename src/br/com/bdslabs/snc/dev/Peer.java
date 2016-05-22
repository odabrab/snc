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
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

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
	public Peer(String[] strRemotePeers, int[] intRemotePorts) throws UnknownHostException, IOException{
		
		this.strRemoteAddresses		= strRemotePeers;
		this.intRemotePortNumbers	= intRemotePorts;
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
 * 
 */
	public void playServer() throws UnknownHostException, IOException{

/*
 *********************************************************************
 *******  METHOD DECLARATION(S)
 *******
 *********************************************************************
 */

		/* TCP stream(s) used to read text received from server. */
		DataInputStream		serverDataInputStream;
		
		/* TCP stream(s) used to send text to server. */
		DataOutputStream	serverDataOutputStream;
		PrintStream			printStream;
		
		ServerSocket[]		serverSocket;
		Socket[]			clientSocket;
		Socket[]			newServerSocket;
		StringBuilder		messageFromClientStringBuilder;

/*
 *********************************************************************
 *******  METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */
		
		serverDataInputStream			= null;
		
		serverDataOutputStream			= null;
		printStream						= null;
		
		serverSocket					= null;
		clientSocket					= null;
		newServerSocket					= null;
		messageFromClientStringBuilder	= new StringBuilder();

/*
 *********************************************************************
 *******	METHOD BODY
 *******
 *********************************************************************
 */
		
		try{
			
			for (int int_remote_peers = 0; int_remote_peers < this.strRemoteAddresses.length; int_remote_peers++){
				
				clientSocket[int_remote_peers]				= null;
				/* Open a server socket. */
				serverSocket[int_remote_peers]				= new ServerSocket(0);
				newServerSocket[int_remote_peers]			= serverSocket[int_remote_peers].accept();
				
				/* Open stream to read from client. */
				serverDataInputStream						= new DataInputStream(newServerSocket[int_remote_peers].getInputStream());
				/* Open stream to write to client. */
				serverDataOutputStream						= new DataOutputStream(newServerSocket[int_remote_peers].getOutputStream());
				/* Open an optional stream to write to client. */
				printStream									= new PrintStream(newServerSocket[int_remote_peers].getOutputStream());
				
				/* Streams and sockets ready, and message successfully received. */
				if (serverSocket[int_remote_peers] != null && newServerSocket[int_remote_peers] != null && serverDataInputStream != null && (serverDataOutputStream != null || printStream != null) && !messageFromClientStringBuilder.append(serverDataInputStream.readUTF()).equals("null")){

					System.out.print("\nA remote peer, suposedly acting as a client, said:\n" +
					                 messageFromClientStringBuilder + "\n" +
							         "\n" +
							         "LOCAL PEER INFORMATION\n" +
					                 "local socket address:\t" + newServerSocket[int_remote_peers].getLocalSocketAddress().toString() + "\n" +
							         "local Inet address:\t" + newServerSocket[int_remote_peers].getLocalAddress().toString() + "\n" +
					                 "local port number:\t" + newServerSocket[int_remote_peers].getLocalPort() + "\n" +
					                 "\n" +
					                 "REMOTE PEER INFORMATION\n" +
					                 "remote socket address:\t" + newServerSocket[int_remote_peers].getRemoteSocketAddress().toString() + "\n" +
							         "remote Inet address:\t" + newServerSocket[int_remote_peers].getInetAddress().toString() + "\n" +
					                 "remote port number:\t" + newServerSocket[int_remote_peers].getPort() + "\n");
					serverDataOutputStream.writeUTF("This message is a DataOutputStream server response confirming your message was received.");
				}
				
				/* Close stream(s). */
				serverDataInputStream.close();
				serverDataOutputStream.close();
				printStream.close();
				
				/* Close socket(s). */
				clientSocket[int_remote_peers].close();
				serverSocket[int_remote_peers].close();
				newServerSocket[int_remote_peers].close();
			}
		}
		
		catch(UnknownHostException unknownHostException){
			
			System.err.print("\nException 005: unknown host.\n" +
                             "Unknown host exception: " + unknownHostException.getMessage() + "\n");
			unknownHostException.printStackTrace();
			System.exit(5);			
		}
		
		catch(IOException ioException){
			
			System.err.print("\nException 004: probaby there was an I/O problem with the server socket.\n" +
         	                 "Could not connect remote peer.\n" +
                             "I/O exception: " + ioException.getMessage() + "\n");
			ioException.printStackTrace();
            System.exit(4);
		}
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

		/* TCP stream(s) used to read text received from server. */
		DataInputStream		clientDataInputStream;
		
		/* TCP stream(s) used to send text to server. */
		DataOutputStream	clientDataOutputStream;
		PrintStream			clientPrintStream;
		PrintWriter			clientPrintWriter;
		
		Socket[]			clientSocket;
		StringBuilder		messageFromServerStringBuilder;
		StringBuilder		messageToServerStringBuilder;
		
		/* Input related. */
		// BufferedReader		inBufferedReader;
		// BufferedReader		stdInBufferedReader;
		
/*
 *********************************************************************
 *******  METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */
		
		clientDataInputStream			= null;
		
		clientDataOutputStream			= null;
		clientPrintStream				= null;
		clientPrintWriter				= null;

		clientSocket					= null;
		messageFromServerStringBuilder	= new StringBuilder();
		messageToServerStringBuilder	= msgStringBuilder;

/*
 *********************************************************************
 *******	METHOD BODY
 *******
 *********************************************************************
 */
		
		try{

			for (int int_remote_peers = 0; int_remote_peers < this.strRemoteAddresses.length; int_remote_peers++){
				
				/* Opens a client socket. */
				clientSocket[int_remote_peers]	= new Socket(this.strRemoteAddresses[int_remote_peers], this.intRemotePortNumbers[int_remote_peers]);
				/* Opens stream to read from server. */
				clientDataInputStream			= new DataInputStream(clientSocket[int_remote_peers].getInputStream());
				/* Opens an optional stream to read from server in a buffered fashion. */
				// inBufferedReader				= new BufferedReader(new InputStreamReader(clientSocket[int_remote_peers].getInputStream()));
		        /* Opens stream to write to server. */
				clientDataOutputStream			= new DataOutputStream(clientSocket[int_remote_peers].getOutputStream());
				/* Opens an optional stream to write to server. */
				// clientPrintStream			= new PrintStream(clientSocket[int_remote_peers].getOutputStream());
				/* Opens an optional stream to write to server. It uses bytes instead of characters. */
				// clientPrintWriter			= new PrintWriter(clientSocket[int_remote_peers].getOutputStream(), true);
				
				/* Streams and sockets ready. */
				if (clientSocket[int_remote_peers] != null && clientDataInputStream != null && (clientDataOutputStream != null || clientPrintStream != null)){

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
				clientSocket[int_remote_peers].close();
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
