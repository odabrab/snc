/*
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *******  LICENSE (LICENCA): GNU GPL v3
 *******
 *******  File mod-socket.c.
 *******
 *******  Copyright (c) 2020 Marcio Barbado Junior
 *******  Sao Paulo, Brasil
 *******
 *******  This program is free software; you can redistribute it
 *******  and/or modify it under the terms of the GNU General Public
 *******  License as published by the Free Software Foundation;
 *******  either version 3 of the License, or (at your option) any
 *******  later version.
 *******
 *******  This program is distributed in the hope that it will be
 *******  useful, but WITHOUT ANY WARRANTY; without even the implied
 *******  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *******  PURPOSE. See the GNU General Public License for more
 *******  details.
 *******  You should have received a copy of the GNU General Public
 *******  License along with this program; if not, write to the Free
 *******  Software Foundation, Inc., 59 Temple Place - Suite 330,
 *******  Boston, MA02111-1307, USA.
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *********************************************************************
 */

/*
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *******  THE NETWORKING LIBRARY
 *******
 *********************************************************************
 *********************************************************************
 *********************************************************************
 */

/*
 *********************************************************************
 *********************************************************************
 *******  PREPROCESSING
 *******
 *********************************************************************
 *********************************************************************
 */

#include "mod-socket.h"

/*
 *********************************************************************
 *********************************************************************
 *******  FUNCTION(S)
 *******
 *********************************************************************
 *********************************************************************
 */

/*
 *********************************************************************
 *******  FUNCTION
 *******
 *******  int_fun_socket_create()
 *******  
 *******  1) Introduction
 *******  This function tries to create a socket.
 *******
 *******  A atribuicao do socket int_socket é feita atraves da funcao
 *******  socket(), sendo que:
 *******
 *******  * o primeiro parametro
 *******    diz respeito à familia de sockets de entrada, as mais
 *******    comuns sao
 *******
 *******    AF_INET ARPA Internet Protocols
 *******    AF_UNIX Unix Internet Protocols
 *******    AF_ISSO ISO Protocols
 *******    AF_NS   Xerox Network System Protocols;
 *******
 *******  * o segundo parametro diz respeito ao tipo de protocolo, os
 *******    mais utilizados sao
 *******
 *******    SOCK_STREAM socket baseado em TCP
 *******    SOCK_DGRAM  socket baseado em UDP; and
 *******
 *******  * o terceiro parametro diz respeito ao codigo do protocolo,
 *******    e pode ser
 *******
 *******    0  Internet Protocol                 (IP)
 *******    1  Internet Control Message Protocol (ICMP)
 *******    2  Internet Group Multicast Protocol (IGMP)
 *******    3  Gateway-Gateway Protocol          (GGP)
 *******    6  Transmission Control Protocol     (TCP)
 *******    17 User Datagram Protocol            (UDP)
 *******
 *******  2) Parameter(s)
 *******  This section provides descriptions of the parameters:
 *******
 *******    cha_ptr_address
 *******    e.g., an IP address,
 *******    this parameter can be used to communicate (return) a
 *******    char type value to the outside world. It however requires
 *******    the function to be called properly. Check the usage
 *******    examples section.
 *******
 *******    int_port
 *******    remote host's TCP port to be used for communication.
 *******
 *******  3) Return
 *******  I often work with int returns because most of my functions
 *******  are supposed to provide codes, e.g., error codes. Other
 *******  results are "returned" via pointers in the parameters.
 *******  These are mere examples of error codes which could be
 *******  returned through the int_return variable.
 *******
 *******  int_return
 *******    0 normal termination.
 *******    1 int_parameter_first inadequate behavior.
 *******
 *******  4) Usage example(s)
 *******  In order to use this function, include this header and write
 *******  something like:
 *******
 *******  int int_variable = int_fun_noun_verb(15, &cha_variable);
 *********************************************************************
 *********************************************************************
 */
int int_fun_socket_create(char *cha_ptr_address, int int_port){

/*
 *********************************************************************
 *******  FUNCTION DECLARATION(S)
 *******
 *******  Function int_fun_socket_create()'s declaration(s).
 *********************************************************************
 */

/*
 *  Recebe o retorno da funcao connect(), que por sua vez executa a
 *  conexao na porta definida em htons()
 */
  int int_connect;

/*
 *  The int_port_receiver variable holds the value of
 *  int_port.
 */
  int int_port_receiver;

/*
 *  The int_return variable holds an integer to be returned by the
 *  function.
 */
  int int_return;

/*
 *  The int_socket variable holds the value of the socket.
 */
  int int_socket;

/*
 *********************************************************************
 *******  FUNCTION INITIALIZATION(S)
 *******
 *******  int_fun_socket_create()
 *********************************************************************
 */

  int_port_receiver             = int_port;
  int_return                    = 0;
  int_socket                    = socket(AF_INET, SOCK_STREAM, 0);

	struct_socket.sin_addr.s_addr = inet_addr(cha_ptr_address);
	struct_socket.sin_family      = AF_INET;
	struct_socket.sin_port        = htons(int_port);

  int_connect                   = connect(int_socket, (struct *sockaddr)&struct_socket, sizeof(struct_socket));

/*
 *********************************************************************
 *******  FUNCTION BODY
 *******
 *******  Function int_fun_socket_create()'s body.
 *********************************************************************
 */

  if (int_port_receiver <= 0){

    int_return = 3;

    perror("port error\n");
  }

  if (int_socket < 0){

    int_return = 1;

    perror("socket error\n");
  }

  else{
  
    printf("socket %d created\n", int_socket);
  }

  if (int_connect < 0){

    int_return = 2;

    perror("port closed\n");
  }

  else{
  
    printf("port %d open\n", int_port);

    bzero(&(struct_socket.sin_zero), 8);
    
    close (int_socket);
  }

  return int_return;

} /*
   *  End of function int_fun_socket_create().
   */

