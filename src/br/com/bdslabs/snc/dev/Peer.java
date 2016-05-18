/*
 *********************************************************************
 *********************************************************************
 *******	LICENSE (LICENCA): GNU GPL v2
 *******
 *******	File "Peer.java".
 *******
 *******	Copyright (c) 2016 Anderson Misson
 *******                       Danilo
 *******                       Marcio Barbado, Jr.
 *******                       Marcos
 *******                       Roger
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author mbarbado
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
 * Plain socket.
 */
	Socket socketSocket;

/*
 *********************************************************************
 *******	CONSTRUCTOR METHOD(S)
 *******
 *********************************************************************
 */

/**
 * Used to start a chat with more than two parts.
 * 
 */
	public Peer(){
		
		this.socketSocket = new Socket(Proxy.NO_PROXY);
	}

/**
 * This constructor method is a fast way to start a private chat.
 * 
 * @throws IOException 
 * @throws UnknownHostException 
 * 
 */
	public Peer(String str_remote_peer, int int_remote_port_number) throws UnknownHostException, IOException{
		
		this.socketSocket = new Socket(str_remote_peer, int_remote_port_number);
	}

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
 * Blocks and listens.
 * 
 */
	public void playServer(){
		
		
	} // playServer() method end.

/**
 * Tries to establish connection.
 * 
 */
	public void playClient(String str_remote_peer) throws IOException{

/*
 *********************************************************************
 *******  METHOD DECLARATION(S)
 *******
 *********************************************************************
 */
		
        String str_FromServer;
        String str_FromUser;
		
		/* Input related. */
		BufferedReader	inBufferedReader;
		BufferedReader	stdInBufferedReader;
		
		/* Output related. */
		PrintWriter		outPrintWriter;

/*
 *********************************************************************
 *******  METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */

		try{
			
			inBufferedReader	= new BufferedReader(new InputStreamReader(socketSocket.getInputStream()));
	        stdInBufferedReader	= new BufferedReader(new InputStreamReader(System.in));
			outPrintWriter		= new PrintWriter(socketSocket.getOutputStream(), true);

			while ((str_FromServer = inBufferedReader.readLine()) != null){
				
				System.out.println("Server: " + str_FromServer);
				
				if (str_FromServer.equals("Bye.")){
					
					break;
				}
				
				str_FromUser = stdInBufferedReader.readLine();
				
				if (str_FromUser != null) {

					System.out.println("Client: " + str_FromUser);
					outPrintWriter.println(str_FromUser);
                }
			}
		}
		
		catch(UnknownHostException e){

			System.err.println("Don't know about host " + str_remote_peer);
            System.exit(2);			
		}
		
		catch (IOException e){
			
            System.err.println("Couldn't get I/O for the connection to " + str_remote_peer);
            System.exit(3);
        }
	} // playClient() method end.

/**
 * Deliver a file.
 * 
 */
	public void playSeeder(){
			
			
	} // playSeeder() method end.
	
/**
 * Receive a file.
 * 
 */
	public void playLeecher(){
				
				
	} // playLeecher() method end.
} // 
