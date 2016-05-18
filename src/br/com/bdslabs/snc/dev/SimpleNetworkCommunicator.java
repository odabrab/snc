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
 *******                       Marcos
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
import java.util.Scanner;

import br.com.bdslabs.oss.dev.OperatingSystemSpecific;

/**
 * <p>
 *   The SimpleNetworkCommunicator class provides conditions for a
 *   simple chat, supporting file distribution and crypto.
 * </p>
 * <p>
 *   Multicast sockets for peer-to-peer communication is possible with
 *   UDP packets, but these are not secure. Thus,
 *   SimpleNetworkCommunicator presents a unicast architecture. It
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
 *   It uses Berkeley Sockets from the transport layer.
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
 * @author		Anderson Misson <missonsama@gmail.com>
 * @author		Danilo
 * @author		Marcio Barbado, Jr. <marcio.barbado@gmail.com>
 * @author		Marcos
 * @author		Roger
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
		
		boolean[]				bool_discover_operating_system;
		int						int_port;
		int						int_error_code;
		String					str_topo;
		String					str_usage;
		String					str_request_message;
		String					str_group_file;
		String					str_id_file;
		String					str_Line;
		String					str_address_and_port;
		String[]				str_LineValuesArray;
		StringBuilder			addressStringBuilder;
		StringBuilder			nicknameStringBuilder;
		BufferedReader			readCSVBufferedReader;
	    Scanner					scannerScanner;
	    
		OperatingSystemSpecific	operatingSystemSpecific;
		Peer					peerPeer;

/*
 *********************************************************************
 *******  MAIN METHOD INITIALIZATION(S)
 *******
 *********************************************************************
 */
		
		int_error_code				= 0;
		int_port					= args.length <= 1 ? 0 : Integer.parseInt(args[1]);
		addressStringBuilder		= args.length == 0 ? new StringBuilder() : new StringBuilder(args[0]);
		str_topo					= " ------------------------------------------------------\n" +
		                              "|                                                      |\n" +
		                              "| ▄██▀██▄ ██   ██ ▄██▀██▄                              |\n" +
		                              "| ▀██▄▄   ███▄ ██ ██                                   |\n" +
		                              "|   ▀▀██▄ ██ ▀███ ██                                   |\n" +
		                              "| ▀██▄██▀ ██   ██ ▀██▄██▀  SIMPLE NETWORK COMMUNICATOR |\n" +
		                              "|                                                      |\n" +
		                              "| license: GNU GPL v2                                  |\n" +
		                              "|                                                      |\n" +
		                              " ------------------------------------------------------\n";
		str_usage					= "usage example for one-to-one communication:\n" +
		                              "java SimpleNetworkCommunicator [ADDRESS] [PORT]\n";
		str_request_message			= "Please provide address and port or Q to quit:\n";
		str_address_and_port		= "";
		str_group_file				= "src/br/com/bdslabs/snc/dev/.group.csv";
		str_id_file					= "src/br/com/bdslabs/snc/dev/.id.csv";
		nicknameStringBuilder		= new StringBuilder();
		str_Line					= "";
	    scannerScanner				= new Scanner(System.in);
		operatingSystemSpecific		= new OperatingSystemSpecific();
		peerPeer					= new Peer();

/*
 *********************************************************************
 *******	MAIN METHOD BODY
 *******
 *********************************************************************
 */
		
		bool_discover_operating_system	= operatingSystemSpecific.descubraSistemaOperacional();
		readCSVBufferedReader			= parseCSV(str_id_file);
		
		while (bool_discover_operating_system[0] == false){
			
			while ((str_Line = readCSVBufferedReader.readLine()) != null){
		    	
				str_LineValuesArray		= str_Line.split(",");
		    	nicknameStringBuilder.append(str_LineValuesArray[1]);
		    }
		    
			readCSVBufferedReader.close();
		    readCSVBufferedReader = parseCSV(str_group_file);
			
			while ((str_Line = readCSVBufferedReader.readLine()) != null){
		    	
		    	/* Each line. */
		    	str_LineValuesArray = str_Line.split(",");
		        /* Example to print the first column. */
		        // System.out.println(str_LineValuesArray[0]);
		    }
			
			readCSVBufferedReader.close();
			
			do{
				
				operatingSystemSpecific.clearConsole(bool_discover_operating_system[1]);
				
				System.out.print("\n" + str_topo + "Hello " + System.getProperty("user.name", nicknameStringBuilder.toString()) + ".\n");
				
				if (int_error_code	== 1){
					
					System.err.print(str_usage);
				}
				
				if (args.length == 2){
					
					peerPeer	= new Peer(addressStringBuilder.toString(), int_port);
				}
				
				else if (args.length == 0){
					
					System.out.print(str_request_message);
					
					str_address_and_port	= scannerScanner.next();					
					addressStringBuilder.append(str_address_and_port);
					int_port				= Integer.parseInt(str_address_and_port);
					
					peerPeer	= new Peer();
				}
				
				else{
					
					int_error_code	= 1;
				}
				
				/* Play the client role. */
				if (true){
					
					peerPeer.playClient(addressStringBuilder.toString());
				}
				
				/* Play the server role. */
				else{
					
					peerPeer.playServer();
				}
				
			} while (int_error_code != 0 && str_request_message.toLowerCase() != "q");
		}

		System.exit(int_error_code);

	} /* Method main() end. */

} /* Class SimpleNetworkCommunicator end. */
