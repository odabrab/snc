/*
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *******  LICENSE (LICENCA): [COMPLETE_LICENSE_NAME]
 *******
 *******  File main-test.c.
 *******
 *******  Copyright (c) 2020 Marcio Barbado Junior
 *******  Sao Paulo, Brasil
 *******
 *******  [LICENSE_NOTICE]
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *********************************************************************
 */

/*
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *******  TEST
 *******
 *******  On Linux, binary should be manually invoked in a command
 *******  line fashion as simply:
 *******
 *******  $ ./main-test.bin [ARGUMENT_000] [ARGUMENT_001]
 *******
 *******  example of a simple command without arguments:
 *******
 *******  $ ./main-test.bin
 *******
 *******  example of a command with two arguments, an address and a
 *******  port number:
 *******
 *******  $ ./main-test.bin 3 192.168.0.16 1572
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

#include "../../lib/C/mod-socket/mod-socket.h"

/*
 *********************************************************************
 *********************************************************************
 *******  GLOBAL DECLARATION(S)
 *******
 *********************************************************************
 *********************************************************************
 */

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
 *********************************************************************
 *******  FUNCTION
 *******
 *******  main() function
 *******  [TEXTO_COM_DESCRICAO_DETALHADA_MAS_SEM_EXEMPLOS].
 *******
 *******  argc
 *******  int value representing the number of command line arguments
 *******  passed in, including the name of the program itself.
 *******
 *******  argv
 *******  char array representing the arguments, index 0 is the
 *******  program name.
 *******
 *******  1- Return
 *******  exit status.
 *********************************************************************
 *********************************************************************
 */
int main(int argc, char **argv){

/*
 *********************************************************************
 *******  DECLARATION(S)
 *******
 *********************************************************************
 */

/*
 *
 */
  char                  cha_binary_string[20];

/*
 *  This is a variable bo be used by a timer. It is here for
 *  convenience.
 */
  clock_t               clo_before;

/*
 *  This is a variable bo be used by a timer. It is here for
 *  convenience.
 */
  clock_t               clo_difference;

/*
 *  For convenience, this is a pointer to an input file.
 */
  FILE                  *fil_ptr_file_input;

/*
 *  For convenience, this is a pointer to an output file.
 */
  FILE                  *fil_ptr_file_output;

/*
 *
 */
  int                   int_argc_receiver;

/*
 *  The int_file_close variable is used to receive fclose() returns.
 */
  int                   int_file_close;

/*
 *
 */
  int                   int_result;

/*
 *  Auxiliary variable for counting. It is here for convenience.
 */
  static unsigned int   sui_count;

/*
 *
 */
  unsigned long int     uli_natural_number;

/*
 *  Given in seconds.
 */
  unsigned long int     uli_time_elapsed;

/*
 *********************************************************************
 *******        INITIALIZATION(S)
 *******
 *********************************************************************
 */

  int_argc_receiver  = argc;
  sui_count          = 0;
  uli_natural_number = 1478;
  uli_time_elapsed   = 0;

/*
 *********************************************************************
 *******  BODY
 *******
 *********************************************************************
 */

  printf("\n"
         "Enter an integer base 10: \n"
         "\n");

  scanf("%d", &uli_natural_number);

  int_result = int_fun_from_decimal_convert(&cha_binary_string, uli_natural_number, 2);

  if (int_result == 0){

    printf("\n"
           "base 10: %d\n"
           "base 02: %s\n", uli_natural_number, cha_binary_string);

  }


/*
 *  Terminate execution successfully.
 */
  exit(EXIT_SUCCESS);

} /*  
   *  End of function main().
   */


