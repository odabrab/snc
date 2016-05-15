/**
 * 
 */
package br.com.bdslabs.snc.dev;

import java.io.IOException;
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
	public Peer(String str_remote_address, int int_port_number) throws UnknownHostException, IOException{
		
		this.socketSocket = new Socket(str_remote_address, int_port_number);
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
	public void playClient(){
		
		
	} // playClient() method end.
}
