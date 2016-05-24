/*
 *********************************************************************
 *********************************************************************
 *******	LICENSE (LICENCA): GNU GPL v2
 *******
 *******	File "SimpleNetworkCommunicator.java".
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

/*
 *********************************************************************
 *******	SNC (SIMPLE NETWORK COMMUNICATOR)
 *******
 *********************************************************************
 */

/*
 *********************************************************************
 *******	PACKAGE(S)
 *******
 *********************************************************************
 */

package br.com.bdslabs.snc.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import br.com.bdslabs.oss.dev.OperatingSystemSpecific;

/**
 * <p>
 *   The SimpleNetworkCommunicator class uses UNIX Berkeley Sockets to
 *   provide conditions for a simple chat, supporting file
 *   distribution and crypto.
 *   According to UNIX, a network communication is a type of I/O. Each
 *   process has a set of I/O descriptors to write to or read from,
 *   and such descriptors may refer to files, devices or communication
 *   channels (sockets) [4].
 *   IPC operations are based on socket pairs. A socket in one process
 *   transmits some data to another socket in another process [4].
 *   Socket programming can use stream communication (TCP/IP) or
 *   datagram communication (UDP/IP).
 *   Multicast sockets for peer-to-peer communication are feasible
 *   with UDP packets, but these are not secure. Moreover, UDP is a
 *   connectionless protocol, meaning that each time one sends
 *   datagrams, he also needs to send the local socket descriptor and
 *   the receiving socket's address. Not to mention a datagram size
 *   limit is 64 kilobytes.
 *   TCP is connection-oriented and it has a setup time, and no size
 *   limit.
 *   Thus, SimpleNetworkCommunicator presents a unicast architecture. It
 *   performs one-to-many peer-to-peer actions.
 * </p>
 * <p>
 *   In peer-to-peer (P2P) networks, each node can act as both a
 *   server and a client, which makes it scalable for file
 *   distribution.
 * </p>
 * <p>
 *   To reduce file distribution time within such model can be
 *   addressed [3].
 * </p>
 * <p>
 *   It uses Berkeley Sockets from the transport layer. These were
 *   built with a paradigm usually referred to as
 *   Open-Read-Write-Close.
 * </p>
 * <p>
 *   The SOCKET primitive, present on Berkeley UNIX [2].
 * </p>
 * <p>
 *   A multiple unicast poses as better solution then. A multicast one
 *   provides efficiency [1].
 * </p>
 * <p>
 *   IP packets contain a class D address for destination (from
 *   224.0.0.0 to 239.255.255.255) so for example, a multicast address
 *   could be 224.0.0.2.
 * </p>
 * Packet duplication at routing nodes.
 * 
 * REFERENCES
 * 
 * [1]	Considerations for Choosing Unicast or Multicast
 * https://docs.oracle.com/middleware/1212/wls/CLUST/features.htm#CLUST695
 * 
 * [2]	REDES DE COMPUTADORES - Andrew S. Tanenbaum
 * 
 * [3]	Minimizing Distribution Time for One-to-Many File Distribution
 * 		in P2P Networks - Hongyun Zheng and Hongfang Yu
 * 		Proceedings of the 2015 International Conference on Electrical
 * 		and Information Technologies for Rail Transportation
 * 
 * [4]	Sockets programming in Java: A tutorial | JavaWorld
 *      Qusay H. Mahmoud
 * http://www.javaworld.com/article/2077322/core-java/core-java-sockets-programming-in-java-a-tutorial.html
 * 
 * @author		Anderson Misson				<missonsama@gmail.com>
 * @author		Danilo A. Vasconcellos
 * @author		Marcio Barbado, Jr.			<marcio.barbado@gmail.com>
 * @author		Marcos Roberto de Menezes
 * @author		Roger Coudounarakis
 * @version
 * @copyright	Copyright (c) 2016 Marcio Barbado, Jr.
 * @license
 */
public class SimpleNetworkCommunicator{

/*
 *********************************************************************
 *******  ATTRIBUTE(S)/FIELD(S)
 *******
 *******  Declarations.
 *********************************************************************
 */

/*
 *********************************************************************
 *******	CONSTRUCTOR METHOD(S)
 *********************************************************************
 */

/**
 * 
 */
	public SimpleNetworkCommunicator(){
		
		
	}

/*
 *********************************************************************
 *******  GET AND SET METHODS
 *******
 *********************************************************************
 */

/*
 *********************************************************************
 *******  METHOD(S)
 *******
 *********************************************************************
 */
	
/**
 * Position
 * 
 * 0:		loopback
 * 1:		private (LAN)
 * 2:		public (external)
 * 
 * @throws IOException 
 * 
 */
	static ArrayList<String> obtainAvailableLocalAddresses() throws IOException{

/*
 *********************************************************************
 *******  METHOD DECLARATION(S)
 *******
 *********************************************************************
 */
		
		ArrayList<String>				str_AddressesArrayList;
		ArrayList<String>				str_PrivateAddressesArrayList;
		BufferedReader					inBufferedReader;
		String							str_LoopbackAddress;
		String							str_public_IP;
		URL								testURL;

/*
 *********************************************************************
 *******  METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */
		
		str_AddressesArrayList			= new ArrayList<String>();
		/* Private address. */
		Enumeration<NetworkInterface> enumerationNetworkInterface = NetworkInterface.getNetworkInterfaces();
		str_PrivateAddressesArrayList	= new ArrayList<String>();
		/* Loopback address. */
		str_LoopbackAddress 			= InetAddress.getLocalHost().getHostAddress();
		/* Public address. */
		testURL							= new URL("http://checkip.amazonaws.com");
		inBufferedReader				= new BufferedReader(new InputStreamReader(testURL.openStream()));
		str_public_IP					= inBufferedReader.readLine();

/*
 *********************************************************************
 *******	METHOD BODY
 *******
 *********************************************************************
 */
		
		while(enumerationNetworkInterface.hasMoreElements()){
			
			NetworkInterface networkInterface				= enumerationNetworkInterface.nextElement();
			Enumeration<InetAddress> enumerationInetAddress	= networkInterface.getInetAddresses();
			
			while (enumerationInetAddress.hasMoreElements()){
				
				InetAddress inetAddr = enumerationInetAddress.nextElement();
				str_PrivateAddressesArrayList.add(inetAddr.getHostAddress());
			}
		}
		
		inBufferedReader.close();
		
		str_AddressesArrayList.add(str_LoopbackAddress);
		str_AddressesArrayList.add(str_PrivateAddressesArrayList.get(2));
		str_AddressesArrayList.add(str_public_IP);
		
		return str_AddressesArrayList;
	} /* Method obtainExternalIP() end. */

/**
 * Parses ".csv" files.
 * 
 */
	static BufferedReader parseCSV(String str_File) throws Exception{
	
/*
 *********************************************************************
 *******  METHOD DECLARATION(S)
 *******
 *********************************************************************
 */

		String				str_CSVFile;
		BufferedReader		linesBufferedReader;
		
/*
 *********************************************************************
 *******  METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */
		
		str_CSVFile 		= str_File;
		// linesBufferedReader	= null;
		// linesBufferedReader	= new BufferedReader(new FileReader(str_CSVFile));

/*
 *********************************************************************
 *******	METHOD BODY
 *******
 *********************************************************************
 */
		
		try{
			
			/* BufferedReader used with FileReader improve reading performance. */
			linesBufferedReader	= new BufferedReader(new FileReader(str_CSVFile));
			
			return linesBufferedReader;
		}
		
		catch(Exception exceptionException){
			
			System.err.print("\nThere is a problem with file " + str_CSVFile + ". Maybe it was not found.\n" +
			                 "Working directory is " + System.getProperty("user.dir") + "\n");
			exceptionException.printStackTrace();
		}
		
		return null;
	} /* Method parseCSV() end. */
	
/*
 *********************************************************************
 *******  MAIN METHOD
 *******
 *********************************************************************
 */

/**
 * 
 * @param args
 */
	public static void main(String[] args) throws Exception{

/*
 *********************************************************************
 *******  MAIN METHOD DECLARATION(S)
 *******
 *******  For a constantly muting string, and for method chaining,
 *******  using StringBuilder is advisable because it provides
 *******  efficiency.
 *********************************************************************
 */
		
		ArrayList<Integer>		groupFilePortsIntegerArrayList;
		/* All addresses on the group file. */
		ArrayList<String>		str_GroupFileAddressesArrayList;
		/* All of the addresses which are available to local peer. */
		ArrayList<String>		str_LocalAddressesArrayList;
		ArrayList<String[]>		str_GroupFileValuesArrayList;
		BufferedReader			readCSVBufferedReader;
		int						int_action;
		int						int_error_code;
		int						int_index;
		OperatingSystemSpecific	operatingSystemSpecific;
		Peer					peerPeer;
		Scanner					scannerScanner;
		String					str_topo;
		String					str_usage;
		String					str_group_file;
		String					str_id_file;
		String					str_Line;
		String[]				str_IDFileValuesArray;
		StringBuilder			messageStringBuilder;
		StringBuilder[]			discoverOSStringBuilderArray;

/*
 *********************************************************************
 *******  MAIN METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */
		
		groupFilePortsIntegerArrayList	= new ArrayList<Integer>();
		str_GroupFileAddressesArrayList = new ArrayList<String>();
		str_LocalAddressesArrayList		= new ArrayList<String>();
		str_GroupFileValuesArrayList	= new ArrayList<String[]>();
		int_action						= -1;
		int_error_code					= 0;
		int_index						= 0;
		str_topo						= " ------------------------------------------------------\n" +
		                                  "|                                                      |\n" +
		                                  "| ▄██▀██▄ ██   ██ ▄██▀██▄                              |\n" +
		                                  "| ▀██▄▄   ███▄ ██ ██                                   |\n" +
		                                  "|   ▀▀██▄ ██ ▀███ ██                                   |\n" +
		                                  "| ▀██▄██▀ ██   ██ ▀██▄██▀  SIMPLE NETWORK COMMUNICATOR |\n" +
		                                  "|                                                      |\n" +
		                                  "| license: GNU GPL v2                                  |\n" +
		                                  "|                                                      |\n" +
		                                  " ------------------------------------------------------\n";
		str_usage						= "usage example for one-to-one communication:\n" +
		                                  "java SimpleNetworkCommunicator [ADDRESS] [PORT]\n";
		str_group_file					= "src/br/com/bdslabs/snc/dev/.group.csv";
		str_id_file						= "src/br/com/bdslabs/snc/dev/.id.csv";
		messageStringBuilder			= new StringBuilder();
		str_Line						= "";
		operatingSystemSpecific			= new OperatingSystemSpecific();
		peerPeer						= new Peer();
		scannerScanner					= new Scanner(System.in);
		discoverOSStringBuilderArray	= operatingSystemSpecific.identifyOSStringBuilderArray();
		
	    /* Reads id file (one line). */
		readCSVBufferedReader			= parseCSV(str_id_file);
	    str_IDFileValuesArray			= (str_Line = readCSVBufferedReader.readLine()) != null ? str_Line.split("\" *, *\"") : null;

/*
 *********************************************************************
 *******	MAIN METHOD BODY
 *******
 *******	Some print and scan.
 *********************************************************************
 */
		
		/* It was open on initialization section. */
		readCSVBufferedReader.close();
		
		if (discoverOSStringBuilderArray[1].equals("FLAG")){
			
			int_error_code = 6;
			System.err.print("\nException " + int_error_code + ": operating system not allowed.\n");
			System.exit(int_error_code);
		}
		
		operatingSystemSpecific.clearConsole(discoverOSStringBuilderArray[0].indexOf("indows") != -1);
		
		str_LocalAddressesArrayList = obtainAvailableLocalAddresses();

		System.out.print(str_topo +
		                 "\nHello " + System.getProperty("user.name", str_IDFileValuesArray[1]) + ".\n" +
		                 discoverOSStringBuilderArray[0] +
		                 "loopback IP in use:\t" + str_LocalAddressesArrayList.get(0) + "\n" +
		                 "internal IP in use:\t" + str_LocalAddressesArrayList.get(1) + "\n" +
                         "external IP in use:\t" + str_LocalAddressesArrayList.get(2) + "\n");
		
		/* Use address and port from command line. */
		if (args.length == 2){
				
			/* TODO: handle input. */
			peerPeer = new Peer(args[0], Integer.parseInt(args[1]));
		}
		
		/* Use addresses and ports from file. */
		else if (args.length == 0){
			
			/* Reads group file (multiple lines). */
			readCSVBufferedReader	= parseCSV(str_group_file);
			
		    System.out.print("\n" +
                             "G R O U P\n" +
		    		         "\n");
			
			while ((str_Line = readCSVBufferedReader.readLine()) != null){

				/* Each line is added, in a FIFO scheme. */
				str_GroupFileValuesArrayList.add(str_Line.split("\" *, *\""));
				str_GroupFileAddressesArrayList.add(str_GroupFileValuesArrayList.get(int_index)[4]);
				groupFilePortsIntegerArrayList.add(new Integer(str_GroupFileValuesArrayList.get(int_index)[5].replaceAll("\"", "")));
			    /* Print the second column (user name). */
			    System.out.print("* " + str_GroupFileValuesArrayList.get(int_index)[1] + "\n");
			    
			    int_index++;
			}
				
			readCSVBufferedReader.close();
			
			/* Nice trick on method toArray(), passing an array as argument. */
			peerPeer = new Peer(str_GroupFileAddressesArrayList.toArray(new String[0]), groupFilePortsIntegerArrayList.toArray(new Integer[0]));
		}
		
		else{
				
			System.err.print("\nException 001: " + str_usage);
			System.exit(1);
		}
		
		/* Main loop. */
		do{
				
			System.out.print("\nCHOOSE ACTION:\n" +
			                 "\n" +
					         "0) quit\n" +
			                 "1) send text\n" +
					         "2) send file\n" +
			                 "3) receive file\n");

			int_action = Integer.parseInt(scannerScanner.next());
				
				switch(int_action){
				
					case 0: {
						
						break;
					}
					
					/* Play the client role. */
					case 1: {
						
						peerPeer.playClient(messageStringBuilder);
						break;
					}
					
					/* Send file. */
					case 2: {

						peerPeer.playSeeder(new StringBuilder());
						break;
					}
					
					/* Receive file. */
					case 3: {

						peerPeer.playLeecher(new StringBuilder());
						break;
					}
					
					default: {
						
						peerPeer.playServer();
						break;
					}
				}
				
				if (scannerScanner.next().equals(null)){
					
					/* Play the server role. */
					peerPeer.playServer();
				}
			} while (int_error_code != 0 && int_action != 0);

		scannerScanner.close();
		System.exit(int_error_code);

	} /* Method main() end. */

} /* Class SimpleNetworkCommunicator end. */
