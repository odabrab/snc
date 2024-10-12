#!/usr/bin/env python3.11
# -*- coding: utf-8 -*-

######################################################################
######################################################################
######################################################################
########  GENERIC PEER
########  
########  File name:     peer.py.
########  Author:        Marcio Barbado, Jr.
########  Contact:       <marcio.barbado@bdslabs.com.br>.
########  Place:         São Paulo, Brasil.
########  Copyright (c): 2024.
########  License:       GNU GPL v2.
########
########  This program is free software; you can redistribute it
########  and/or modify it under the terms of the GNU General Public
########  License as published by the Free Software Foundation;
########  version 2 of the license.
########
########  This program is distributed in the hope that it will be
########  useful, but WITHOUT ANY WARRANTY; without even the implied
########  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
########  PURPOSE.  See the GNU General Public License for more
########  details.
########
########  You should have received a copy of the GNU General Public
########  License along with this program; if not, write to the Free
########  Software Foundation, Inc., 59 Temple Place - Suite 330,
########  Boston, MA  02111-1307, USA.
######################################################################
######################################################################
######################################################################

######################################################################
######################################################################
########  LIBRARY(IES)
########
########  Algumas bibliotecas Python devem ser incluídas no início do
########  código, e.g., bibliotecas __future__.
######################################################################
######################################################################

import os;

import socket;

import sys;

######################################################################
######################################################################
########  FUNCTION(S)
########
######################################################################
######################################################################

######################################################################
########  FUNCTION FUN_INT_ABRIR()
########
########  Abre um par para conexões remotas de entrada.
########
########
########  Exemplo de uso para teste de loopback:
########
########  var_int_abrir = fun_int_abrir(0, 5);
######################################################################
def fun_int_abrir(var_par_str_endereco_local = '127.0.0.1', var_par_int_porta_local = 0, var_par_int_quantidade_de_conexoes = 1, var_par_str_opcoes_de_conexao = ""):

  var_str_opcoes_de_conexao = var_par_str_opcoes_de_conexao;
  
  var_int_porta_local       = var_par_int_porta_local;
  
  var_int_quantidade_de_conexoes = var_par_int_quantidade_de_conexoes;
  
  var_int_retorno           = 0;
  
  #  Declaração e inicialização de uma variável para o socket.
  var_socket                = socket.socket(socket.AF_INET, socket.SOCK_STREAM);

  var_str_endereco_local    = var_par_str_endereco_local;

  #  A função bind() deve receber uma tupla de valores.
  var_socket.bind((var_str_endereco_local, var_int_porta_local));

  #  Operação ternária.
  var_int_porta_local       = var_par_int_porta_local if var_par_int_porta_local != 0 else var_socket.getsockname()[1];
  
  #  O socket criado começa a escutar o número de conexões
  #  especificado no argumento entregue ao parâmetro
  #  var_par_int_quantidade_de_conexoes.
  var_socket.listen(var_int_quantidade_de_conexoes);
  
  print("Socket criado: ", var_socket, ".\n",
        "Porta local aberta para conexão(ões): ", var_int_porta_local, ".\n",
        "Aguardando conexão(ões).");
  
  #  Começar a ouvir as conexões de entrada.
  #
  #  O nome da função recv() vem de receive, que significa receber.
  #  Ela é utilizada para receber dados do outro host. O parâmetro que
  #  recv() utiliza é a quantidade de bytes que deve ser recebida como
  #  dados de entrada. Caso o valor recebido seja igual a zero, isso
  #  significa que o outro host se desconectou.
  while True:

    var_socket_da_conexao_remota, var_str_endereco_da_conexao_remota = var_socket.accept();

    print("Conexão de par remoto aceita e estabelecida.\n",
          "Informações sobre o socket da conexão remota: ", var_socket_da_conexao_remota, ".\n",
          "Endereço da conexão remota: ", var_str_endereco_da_conexao_remota, ".");

    #  Buffer de até 1024 bytes ou caracteres.
    var_str_buffer = var_socket_da_conexao_remota.recv(1024).decode();

    if len(var_str_buffer) > 0 and len(var_str_buffer) <= 1024:

      print(var_str_buffer);
      break;

  var_socket.close();
  
  return var_int_retorno; #  Fim da função fun_int_abrir().

######################################################################
########  FUNÇÃO FUN_INT_CONECTAR()
########
########  .
######################################################################
def fun_int_conectar(var_par_str_endereco_remoto, var_par_int_porta_remota):
  
  var_int_porta_remota    = var_par_int_porta_remota;
  
  var_int_retorno         = 0;
  
  var_socket              = socket.socket();
  
  var_str_endereco_remoto = var_par_str_endereco_remoto;
  
  var_local               = var_socket.getsockname()[1];
  
  print("Socket criado: ", var_socket, "\n",
        "Endereço local e porta local em uso: ", var_local);
  
  var_socket.connect((var_str_endereco_remoto, var_int_porta_remota));
  
  var_mensagem = input(" -> ");
  
  var_socket.send(var_mensagem.encode());
  
  var_resposta = var_socket.recv(1024).decode();
  
  print("Resposta do outro par: ", var_resposta);
  
  var_socket.close();
  
  return var_int_retorno; #  Fim da função fun_int_conectar().

######################################################################
######################################################################
########  DECLARATION(S) AND INITIALIZATION(S)
########
######################################################################
######################################################################

######################################################################
########  VARIABLE VAR_STR_BANNER
########
########  .
######################################################################
var_str_banner = """
 --------------------------------- 
| ██▀▀▀▄  ██▀▀▀▀▀ ██▀▀▀▀▀ ██▀▀▀▄  |
| ██    █ ██      ██      ██    █ |
| ██▄▄▄▀  ██▀▀▀▀▀ ██▀▀▀▀▀ ██▄▄▄▀  |
| ██      ██▄▄▄▄▄ ██▄▄▄▄▄ ██  █▄▄ |
|                                 |
| Licença: GNU GPL v2             |
 --------------------------------- 

operating system:
kernel:

PYTHON VERSION INFORMATION
""" + str(sys.version_info) + """
""";

######################################################################
########  VARIABLE VAR_INT_PORTA
########
########  .
######################################################################
var_int_porta    = 0;

######################################################################
########  VARIABLE VAR_INT_CONEXOES
########
########  Quantidade de conexões de entrada a permitir.
######################################################################
var_int_conexoes = 1;

######################################################################
########  VARIABLE VAR_STR_ENDERECO
########
########  .
######################################################################
var_str_endereco = "127.0.0.1";

######################################################################
########  VARIABLE VAR_STR_OPCOES
########
########  Opções de conexão.
######################################################################
var_str_opcoes   = "";

######################################################################
########  VARIABLE VAR_STR_RAWINPUT
########
########  .
######################################################################
var_str_rawinput = "";

######################################################################
######################################################################
########  BODY
########
######################################################################
######################################################################

if __name__ == "__main__":
  
  os.system('cls' if os.name == 'nt' else 'clear');
  
  print(var_str_banner);

  while True:

    print("""ESCOLHA UMA OPÇÃO ABAIXO POR FAVOR\n
          \n
          A)\tAbrir para conexão(ões) de entrada\n
          C)\tConectar-se a um par aberto\n
          F)\tFechar
          """);

    var_str_rawinput = input();
    
    #  Well, normally, a switch statement would follow but this is
    #  Python.
    if ((var_str_rawinput == 'F') or (var_str_rawinput == 'f')):
    
      print('\nSo long.\n');
      break;
      
    elif ((var_str_rawinput == 'A') or (var_str_rawinput == 'a')):
      
      print('\nABRIR PARA CONEXÕES DE ENTRADA\n');
      
      fun_int_abrir(var_str_endereco, var_int_porta, var_int_conexoes, var_str_opcoes);
    
    elif ((var_str_rawinput == 'C') or (var_str_rawinput == 'c')):
      
      print('\nCONECTAR A UM PAR ABERTO\n');
      
      var_str_endereco = input('Forneça o endereço do par.\n');
      
      var_int_porta    = int(input('Forneça a porta do par.\n'));

      fun_int_conectar(var_str_endereco, var_int_porta);
    
    else:

      print('\nOpção inválida.\n');

      continue;

quit();
