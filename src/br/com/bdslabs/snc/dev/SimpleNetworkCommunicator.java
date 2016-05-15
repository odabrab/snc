/*
 *********************************************************************
 *********************************************************************
 *******	LICENSE (LICENCA): GNU GPL v2
 *******
 *******	File "SimpleNetworkCommunicator.java".
 *******
 *******	Copyright (c) 2016 Marcio Barbado, Jr.
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

import br.com.bdslabs.osi.dev.OperatingSystemIdentifier;

/**
 * <p>
 *   The SimpleNetworkCommunicator class provides conditions for a
 *   simple chat, supporting file sharing and crypto.
 * </p>
 * <p>
 *   It uses Berkeley Sockets from the transport layer.
 * </p>
 * <p>
 *   The SOCKET primitive, present on Berkeley UNIX [2].
 * </p>
 * <p>
 *   A multicast socket
 *   Its architecture is unicast-based.
 * </p>
 * <p>
 *   Multicast socket for peer-to-peer communication is possible with
 *   UDP packets, which is not secure.
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
 * [1] Considerations for Choosing Unicast or Multicast
 * https://docs.oracle.com/middleware/1212/wls/CLUST/features.htm#CLUST695
 * 
 * [2] REDES DE COMPUTADORES - Andrew S. Tanenbaum
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
 *******  MAIN METHOD DECLARATIONS
 *******
 *********************************************************************
 */
		
		boolean						bool_loop;
		boolean[]					bool_discover_operating_system;
		int							int_port;
		String						str_address;
		String						str_topo;
		String						str_usage;
		Peer						peerPeer;
		OperatingSystemIdentifier	operatingSystemIdentifier;

/*
 *********************************************************************
 *******  MAIN METHOD INITIALIZATIONS
 *******
 *********************************************************************
 */
		
		bool_loop					= false;
		int_port					= Integer.parseInt(args[1]);
		str_address					= args[0];
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
		str_usage					= "usage: java SimpleNetworkCommunicator [ADDRESS] [PORT]\n";
		operatingSystemIdentifier	= new OperatingSystemIdentifier();

/*
 *********************************************************************
 *******	MAIN METHOD BODY
 *******
 *******	To see all system information:
 *******
 *******	System.getProperties().list(System.out);
 *********************************************************************
 */
		
		// bool_discover_operating_system = operatingSystemIdentifier.descubraSistemaOperacional();
		
		do{
			
			operatingSystemIdentifier.clearConsole(false);
			System.out.print(str_topo);
			
			if (args.length != 2){
				
	            System.err.println(str_usage);
	            System.exit(1);
	        }
			
			if (true){
				
				peerPeer	= new Peer(str_address, int_port);
				bool_loop	= false;
			}
			
			else if (true){
				
				peerPeer	= new Peer();
				bool_loop	= false;
			}
			
			else{
				
				bool_loop	= false;
			}
			
		} while (bool_loop);
		
		System.exit(0);

	} /* Method main() end. */

} /* Class SimpleNetworkCommunicator end. */
