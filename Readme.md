<!--
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----  MARKDOWN FORMATTING README TEXT FILE
----
----  File name:     Readme.md.
----  Author:        Marcio Barbado, Jr.
----  Contact:       <marcio.barbado@bdslabs.com.br>.
----  Place:         São Paulo, Brasil.
----  Copyright (c): 2024.
----  License:       [LICENSE_NAME].
----
----  [LICENSE_SHORT_TEXT].
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
-->

<!--
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --  STRUCTURE OF THIS FILE'S CONTENT
  --
  --  In the structure represented below, (CC) denotes a comment and
  --  code block, and (CO) denotes a comment-only block.
  --
  --  USAGE
  --    ABSTRACT
  --    INTRODUCTION
  --    DEPENDENCY(IES)
  --    INSTALLATION AND EXECUTION
  --    DIRECTORY STRUCTURE
  --    HISTORY AND RELEASE NOTES
  --    CONTRIBUTION
  --    REFERENCE(S)
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --------------------------------------------------------------------
-->

<!--
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --  USAGE
  --
  --  .
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  -->

# README FOR SNC

<!--
----------------------------------------------------------------------
----------------------------------------------------------------------
----  ABSTRACT
----
----------------------------------------------------------------------
----------------------------------------------------------------------
-->
> This is the SNC readme file.
> <img src='./lib/gnome-netstatus-tx.png' align='right'>

<!--
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --  INTRODUCTION
  --
  --  Description.
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  -->
## INTRODUCTION
As of 2024, Simple Network Communicator (SNC) stands as a didactic project for UNIX Berkeley Sockets in C, Java, and Python as well. In all of its versions, the application seeks to be minimal. Thus, communication is elegantly implemented in a peer-to-peer fashion.

<!--
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --  DEPENDENCY(IES)
  --
  --  Requirements.
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  -->
## DEPENDENCY(IES)
This section covers dependencies of many types, e.g., packages, programming languages, and environments. It should also mention versions.

<!--
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --  INSTALLATION AND EXECUTION
  --
  --  Includes basic tests.
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  -->
## INSTALLATION AND EXECUTION

<!--
  --------------------------------------------------------------------
  --  INSTALLATION
  --
  --------------------------------------------------------------------
  -->
### INSTALLATION

<!--
  --------------------------------------------------------------------
  --  TESTING
  --
  --------------------------------------------------------------------
  -->
### TESTING

<!--
  --------------------------------------------------------------------
  --  EXECUTION
  --
  --  Once properly installed, this section should explain how to
  --  basically execute the artifact.
  --
  --  If this readme is associated with a publication with relevant
  --  claims, this section should explain the necessary steps to prove
  --  it.
  --------------------------------------------------------------------
  -->
### EXECUTION

<!--
  --------------------------------------------------------------------
  --  USAGE EXAMPLES
  --
  --------------------------------------------------------------------
  -->
### USAGE EXAMPLES

<!--
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --  DIRECTORY STRUCTURE
  --
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  -->
## DIRECTORY STRUCTURE

### DIRECTORY: .ignore/

### DIRECTORY: doc/
Directory "doc/" shelters the project's documentation.

### DIRECTORY: lib/
The lib/ directory shelters content being used somewhere in this directory, as long as the referred content do not belong to the src/ directory. Examples of content are software code (binaries, sources and/or scripts) and PNG files.

### DIRECTORY: sw/
The sw/ directory shelters both binary and text executable files which do not belong to the lib/ directory, e.g., the sw/exe/ subdirectory, previously named sw/bin/, and the sw/src/ subdirectory, which shelters sources. Also, the install.sh file do not belong to this directory.

As for the Java implementation, there are two classes only: SimpleNetworkCommunicator, which holds the main() method, and Peer. And that is it. The main class is br.com.bdslabs.snc.dev.SimpleNetworkCommunicator.

### FILE: .gitignore

### FILE: .project

### FILE: .relations.txt
The .relations.txt file, sometimes present, resembles what software engineering calls a traceability matrix. It relates this project with others.

### FILE: .rsyncignore

### FILE: install.sh

<!--
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --  HISTORY AND RELEASE NOTES
  --
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  -->
## HISTORY AND RELEASE NOTES
In 2024, SNC was updated with C and Python implementations.

<!--
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  --  CONTRIBUTION
  --
  --------------------------------------------------------------------
  --------------------------------------------------------------------
  -->
## CONTRIBUTION

<!--
----------------------------------------------------------------------
----------------------------------------------------------------------
----  REFERENCE(S)
----
----------------------------------------------------------------------
----------------------------------------------------------------------
-->
## REFERENCE(S)
