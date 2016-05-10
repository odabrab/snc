/*
 *********************************************************************
 *********************************************************************
 *******	LICENSE (LICENCA): GNU GPL v2
 *******
 *******	File "AirTrafficAuthenticator.java".
 *******
 *******	Copyright (c) 2014 Marcio Barbado, Jr.
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
 *******	JSNC (JAVA SIMPLE NETWORK COMMUNICATOR)
 *********************************************************************
 */

/*
 *********************************************************************
 *******	PACKAGE(S)
 *******
 *********************************************************************
 */

package br.com.bdslabs.jsnc.dev;

/**
 * <p>
 *   It creates conditions for a simple chat, supporting file sharing
 *   and crypto.
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
 * @author		Anderson Misson <missonsama@gmail.com>
 * @author		Danilo
 * @author		Marcio Barbado, Jr. <marcio.barbado@gmail.com>
 * @author		Marcos
 * @author		Roger
 * @version
 * @copyright	Copyright (c) 2016 Marcio Barbado, Jr.
 * @license
 */
public class JavaSimpleNetworkCommunicator{

/*
 *********************************************************************
 *******	CONSTRUCTOR(S)
 *********************************************************************
 */

	public JavaSimpleNetworkCommunicator(){
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
