package cs241parsePlay;

import java.util.*;
import java.math.*;
import java.io.*;


/** A sample main class demonstrating the use of the Lexer.
 *  This main class just outputs each line in the input, followed by
 *  the tokens returned by the lexer for that line.
 *
 *  If a filename (or path) is supplied as the first argument, eg
 *       java Asm   src/sumOneToFive.asm
 *  this program will read from that file; if no argument is present,
 *  this program will read from standard input, whether typed directly
 *  on the keyboard or redirected at the command line from a file, as by
 *       java Asm < src/sumOneToFive.asm
 *
 *  Requires Java version 1.5
 *
 *  Minor modifications by JCBeatty, Jan 2009.
 *
 *  A very quick summary of Java language features used in this program that
 *  few graduates of CS 134 will have encountered follow.
 *
 *  (1) The definition of multiple classes in a single file. This is generally a bad idea:
 *      (a) many java tools, such as Sun's java compiler javac, locate the source for a
 *      class by looking for a file whose name matches the class name; (b) it is typically
 *      more difficulty to work with a program, especially a large program, that's defined
 *      in a single file. It is convenient for CS 241, however, because electronic submission
 *      of a program requires submitting only a single file.
 *
 *  (2) "enums" - that is, "enumerated types". In CS 134, you learned to create symbolic constants
 *      by using statements such as "static final int meaningOfTheUniverse = 42". Roughly speaking,
 *      enums are a way of asking the java compiler to create a set of such symbolic constants
 *      having distinct values. The enumerated type Kind defined in this file is a good example.
 *      However, enums are actually a special kind of class and can have constructors and methods.
 *      The enumerated type State defined much later in this file illustrates this.
 *
 *  (3) "Parametric types." This is a huge topic; Java's implementation has many warts and
 *      confusing corner cases. However, their only appearance here is in the declaration of an
 *      ArrayList of Tokens in the scan method below. [The Java class ArrayList (actually
 *      java.util.ArrayList) is Sun's version of the CS 134 ListArray class; the "List" you
 *      see here is also imported from java.util, and is analogous to (but not the same as)
 *      the List interface defined in CS 134.] So 
 *          List<Token> ret = new ArrayList<Token>();
 *      allows the compiler to take care of ensuring that you only put Tokens into ret,
 *      and to automatically cast objects you retrieve from ret into Tokens so that you don't
 *      have to.
 *
 *  (4) Nested classes and interfaces. There are examples of both below, in the definition of the
 *      class Lexer: State, Chars, AllChars and Transition are nexted class definitions; and Chars
 *      is a nested interface definition. Defining these *inside* the definition of Lexer means that
 *      they can only be used by code within Lexer, which is arguably good design if they're not
 *      *intended* to be used elsewhere.
 *
 *  (5) System.exit(0) and System.exit(1). Not surprisingly, calls to System.exit(...) cause the
 *      program to cease execution. When you run a program from a command line shell, the integer
 *      value returned by such an exit is made available to the shell. By convention, unix programs
 *      that quit normally return 0; programs that quit because they have encountered a fatal error
 *      return a non-zero value - often a non-zero error code specifying more-or-less precisely :-)
 *      exactly what error was encountered.
 *
 *  Regarding note (1): if you decide you'd REALLY prefer that each class live in its own file,
 *  it's straightforward to write a simple shell script to merge those files into a single file
 *  for submission, although care must be taken to eliminate multiple identical import statements,
 *  and Asm may be the only public class (as is already true in this file.) For an example of how
 *  to do this, see
 *                   http://jcbServer.cs.uwaterloo.ca/cs241/code/a03/merge.bash
**/
public class Asm {

    // Execution starts here when the program is run from the command line by typing one of...
    //     java Asm < something.asm > something.mips
    //     java Asm   something.asm > something.mips
    public static final void main( String[] args ) {
    	PrintWriter out;
    	String what;
    	// Args contains the sequence of blank-delimited tokens supplied after the name of the class
        // containing main when a java program is executed from the command line.
        if( args.length == 0 )
            new Asm().runFile( System.in );// System.in is an InputStream
        else if( args.length == 1 ){
            Asm.exe( args[0] );
        } else if( args.length == 2 ){
            try {
	        	out = new PrintWriter(new File(args[1]));
	        	
	        	//TODO: Shouldn't I print line by line or something more efficient?
	        	what = Asm.exe( args[0] ) ;
	        	out.write(what);
	        	
	        	out.close();
            } catch ( Exception e) {
            	System.err.println("Something bad happened with the output file!");
            	e.printStackTrace();
            }
        }
    }

    // Called either from main(...) or from JUnit test_...(...) methods in TestCase subclasses.
    public static String exe( String inputFilePath ) {
        String out;
    	try {
            FileInputStream inStream = new FileInputStream( inputFilePath );
            out = new Asm().run( new Scanner(inStream) );
            
            try {
            	inStream.close();
            }catch (Exception e) {
            	System.err.println("Could not close file.\n");
            	e.printStackTrace();
            }
            
            return out;
        } catch( FileNotFoundException e ) {
            throw new Error( "Could not open file \"" + inputFilePath + "\" for reading." );
        }
    }

    
    
    // input should be either System.in or a FileInputStream attached to an input file (something.asm).
    public String runFile( InputStream input ) {
        return run(new Scanner(input));
    }
    
    // input should be either System.in or a FileInputStream attached to an input file (something.asm).
    public String runString( String input ) {
        return run(new Scanner(input));
    }
    
 // input should be either System.in or a FileInputStream attached to an input file (something.asm).
    private String run( Scanner in ) {

        Lexer   lexer = new Lexer();

        String out = "";
        
        //Beginning of file:
        //out += "BOF bof" + "\n";
        while( in.hasNext() ) {
            
            String token = in.next();

            // Scan the line into an array of tokens.
            Token[] tokens;
            tokens = lexer.scan( token );

            // Print the input line, followed by the tokens produced for it by the scanner.
            //System.err.println( line );
            for( int i = 0; i < tokens.length; i++ ) {
            	if( i == 0) {
            		if(tokens[0].kind == Kind.COMMENT) {
            			//System.err.println(in.nextLine());
            			in.nextLine();
            			break;
            		} else if(tokens[0].kind == null) {
            			System.err.println("ERROR: " + token);
            		}
            	}
                
            	//System.err.println( "  Token: " + tokens[i] );
            	out += tokens[i].kind + " " + tokens[i].lexeme + "\n";
            }
        }
        
      //End of file:
       // out += "EOF eof" + "\n";
        
        System.out.println(out);
        System.out.flush();

        // Main ignores the value returned, but the "OK" is useful if you decide to to JUnit testing;
        // run should return either a string containing "ERROR" or a string containing "OK", depending
        // on whether or not your assembler finds an error in the file it's assembly. Of course, that
        // leaves open the question of whether the MIPS code generated for a program w/o syntax errors
        // is semantically correct. You can automate testing that, too, but it takes more work since
        // you have to run the resulting *.mips file via java cs241.twoints and check its output...
        return( out );
    }
}

/** The various kinds of tokens (ie values of Token.kind). */
enum Kind {
    ID,             // Opcode or identifier (use of a label)
    NUM,            // Decimal integer
    LPAREN,         // Hexadecimal integer
    RPAREN,       // Register number
    LBRACE,         // (
    RBRACE,         // )
    RETURN,
    IF,
    ELSE,
    WHILE,
    PRINTLN,
    BECOMES,
    WAIN,
    INT,
    EQ,
    NE,
    LT,
    GT,
    LE,
    GE,
    PLUS,
    MINUS,
    STAR,
    SLASH,
    PCT,
    COMMA,
    SEMI,
    COMMENT;
}

/** The representation of a token. */
class Token {
    
    public Kind   kind;   // The kind of token.
    public String lexeme; // String representation of the actual token in the source code.

    public Token( Kind kind, String lexeme ) {
        this.kind   = kind;
        this.lexeme = lexeme;
    }

    public String toString() {
        return kind + " {" + lexeme + "}";
    }

    /** Returns an integer representation of the token. For tokens of kind
     *  INT (decimal integer constant) and HEXINT (hexadecimal integer
     *  constant), returns the integer constant. For tokens of kind
     *  REGISTER, returns the register number.
     */
    public int toInt() {
        if(      kind == Kind.INT      ) return parseLiteral( lexeme,              10, 32 );
        else {
            System.err.println( "ERROR in to-int conversion." );
            System.exit(1);
            return 0;
        }
    }
    
    private int parseLiteral( String s, int base, int bits ) {
        BigInteger x = new BigInteger( s, base );
        if( x.signum() > 0 ) {
            if( x.bitLength() > bits ) {
                System.err.println( "ERROR in parsing: constant out of range: " + s );
                System.exit(1);
            }
        } else if( x.signum() < 0 ) {
            if( x.negate().bitLength() > bits-1
                    && x.negate().subtract(new BigInteger("1")).bitLength() > bits-1 ) {
                System.err.println( "ERROR in parsing: constant out of range: " + s );
                System.exit(1);
            }
        }
        return (int) (x.longValue() & ((1L << bits) - 1));
    }
}

// Lexer -- implements a DFA that partitions an input line into a list of tokens.
// DFAs will be discussed Lectures 10, 11 and 12 and Assignment 5.
class Lexer {

    public Lexer() {
        
        CharSet whitespace    = new Chars( "\t\n\r " );
        CharSet letters       = new Chars( "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"           );
        CharSet lettersDigits = new Chars( "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" );
        CharSet digits        = new Chars( "0123456789"                                                     );
        CharSet hexDigits     = new Chars( "0123456789ABCDEFabcdef"                                         );
        CharSet oneToNine     = new Chars( "123456789"                                                      );
        CharSet all           = new AllChars();

        /** The handling of whitespace is tricky. There are two things you should figure out:
         *  (a) how and why all of the characters following // are swallowed up w/o returning a token;
         *  (b) how the appearance of one or more whitespace characters causes this Lexer to cease
         *      building up an ID or keyword, which is then appended to the list of tokens found in
         *      the line, and start scanning for another token.      
        **/

        table = new Transition[] {
                
                new Transition( State.START,    new Chars("0"), State.ZERO       ),
                new Transition( State.START,    oneToNine,      State.NUM        ),
                new Transition( State.NUM,      digits,         State.NUM        ),
                
                new Transition( State.START,    new Chars("("), State.LPAREN  ),
                new Transition( State.START,    new Chars(")"), State.RPAREN ),
                
                new Transition( State.START,    new Chars("{"), State.LBRACE  ),
                new Transition( State.START,    new Chars("}"), State.RBRACE  ),
                
                new Transition( State.START,    new Chars("r"), State.R			),
                new Transition( State.R,	    new Chars("e"), State.RE		),
                new Transition( State.RE,	    new Chars("t"), State.RET		),
                new Transition( State.RET,	    new Chars("u"), State.RETU		),
                new Transition( State.RETU,	    new Chars("r"), State.RETUR		),
                new Transition( State.RETUR,    new Chars("n"), State.RETURN	),
                
                new Transition( State.START,    new Chars("i"), State.I			),
                new Transition( State.I,	    new Chars("f"), State.IF		),
                new Transition( State.I,	    new Chars("n"), State.IN		),
                new Transition( State.IN,	    new Chars("t"), State.INT		),
                
                new Transition( State.START,    new Chars("e"), State.E			),
                new Transition( State.E,	    new Chars("l"), State.EL		),
                new Transition( State.EL,	    new Chars("s"), State.ELS		),
                new Transition( State.ELS,	    new Chars("e"), State.ELSE		),
                
                new Transition( State.START,    new Chars("w"), State.W			),
                new Transition( State.W,	    new Chars("h"), State.WH		),
                new Transition( State.WH,	    new Chars("i"), State.WHI		),
                new Transition( State.WHI,	    new Chars("l"), State.WHIL		),
                new Transition( State.WHIL,	    new Chars("e"), State.WHILE		),
                new Transition( State.W,	    new Chars("a"), State.WA		),
                new Transition( State.WA,	    new Chars("i"), State.WAI		),
                new Transition( State.WAI,	    new Chars("n"), State.WAIN		),
                
                new Transition( State.START,    new Chars("p"), State.P			),
                new Transition( State.P,	    new Chars("r"), State.PR		),
                new Transition( State.PR,	    new Chars("i"), State.PRI		),
                new Transition( State.PRI,	    new Chars("n"), State.PRIN		),
                new Transition( State.PRIN,	    new Chars("t"), State.PRINT		),
                new Transition( State.PRINT,    new Chars("l"), State.PRINTL	),
                new Transition( State.PRINTL,   new Chars("n"), State.PRINTLN	),
                
                new Transition( State.START,    new Chars("="), State.BECOMES   ),
                
                new Transition( State.BECOMES,  new Chars("="), State.EQ	    ),
                
                new Transition( State.START,    new Chars("!"), State.EXCLA   ),
                new Transition( State.EXCLA,    new Chars("="), State.NE   ),
                
                new Transition( State.START,    new Chars("<"), State.LT   ),
                new Transition( State.START,    new Chars(">"), State.GT   ),
                
                new Transition( State.LT,	    new Chars("="), State.LE   ),
                new Transition( State.GT,	    new Chars("="), State.GE   ),
                
                new Transition( State.START,    new Chars("+"), State.PLUS   ),
                new Transition( State.START,    new Chars("-"), State.MINUS   ),
                new Transition( State.START,    new Chars("*"), State.STAR   ),
                new Transition( State.START,    new Chars("/"), State.SLASH   ),
                new Transition( State.START,    new Chars("%"), State.PCT   ),
                
                new Transition( State.START,    new Chars(","), State.COMMA   ),
                new Transition( State.START,    new Chars(";"), State.SEMI    ),
                
                new Transition( State.SLASH,    new Chars("/"), State.COMMENT),
                new Transition( State.COMMENT,   all, 			State.COMMENT),
                //only // are allowed.
                
                //Cheap way to collect the IDs...
                new Transition( State.START,    letters, State.ID    ),
                new Transition( State.ID,    lettersDigits, State.ID    ),
                
                new Transition( State.R,	    lettersDigits, State.ID		),
                new Transition( State.RE,	    lettersDigits, State.ID		),
                new Transition( State.RET,	    lettersDigits, State.ID		),
                new Transition( State.RETU,	    lettersDigits, State.ID		),
                new Transition( State.RETUR,    lettersDigits, State.ID		),
                new Transition( State.RETURN,    lettersDigits, State.ID	),
                
                new Transition( State.I,	    lettersDigits, State.ID		),
                new Transition( State.IN,	    lettersDigits, State.ID		),
                new Transition( State.INT,	    lettersDigits, State.ID		),
                new Transition( State.IF,	    lettersDigits, State.ID		),
                
                new Transition( State.E,	    lettersDigits, State.ID		),
                new Transition( State.EL,	    lettersDigits, State.ID		),
                new Transition( State.ELS,	    lettersDigits, State.ID		),
                new Transition( State.ELSE,	    lettersDigits, State.ID		),
                
                new Transition( State.W,	    lettersDigits, State.ID		),
                new Transition( State.WH,	    lettersDigits, State.ID		),
                new Transition( State.WHI,	    lettersDigits, State.ID		),
                new Transition( State.WHIL,	    lettersDigits, State.ID		),
                new Transition( State.WHILE,	lettersDigits, State.ID		),
                new Transition( State.WA,	    lettersDigits, State.ID		),
                new Transition( State.WAI,	    lettersDigits, State.ID		),
                new Transition( State.WAIN,	    lettersDigits, State.ID		),
                
                new Transition( State.P,	    lettersDigits, State.ID		),
                new Transition( State.PR,	    lettersDigits, State.ID		),
                new Transition( State.PRI,	    lettersDigits, State.ID		),
                new Transition( State.PRIN,	    lettersDigits, State.ID		),
                new Transition( State.PRINT,    lettersDigits, State.ID		),
                new Transition( State.PRINTL,   lettersDigits, State.ID		),
                new Transition( State.PRINTLN,   lettersDigits, State.ID		)
                
                
                //TODO: add: ++, --, for loops?, other function and other types...
                
                
        };
    }

    /** Partitions the line passed in as input into an array of tokens.
     *  The array of tokens is returned.
     */
    public Token[] scan( String input ) {

        List<Token> ret = new ArrayList<Token>();

        if( input.length() == 0 ) return new Token[0];
        int   i          = 0;
        int   startIndex = 0;
        State state      = State.START;

        while( true ) {

            Transition trans = null;

            if( i < input.length() ) trans = findTransition( state, input.charAt(i) );
            
            
          //TODO: special no 2 token stuck together rules to implement
            if( trans == null ) {
                // No more transitions possible
                if( ! state.isFinal() ) {
                    System.err.println( "ERROR in lexing after reading " + input.substring(0,i) );
                    System.exit(1);
                }
                //if( state.kind != Kind.WHITESPACE ) {
                    ret.add( new Token(state.kind,input.substring(startIndex,i)) );
                //}
                startIndex = i;
                state      = State.START;
                if( i >= input.length() ) break;
            } else {
                state      = trans.toState;
                i++;
            }
        }
        
        return ret.toArray( new Token[ret.size()] );
    }

    ///////////////////////////////////////////////////////////////
    // END OF PUBLIC METHODS
    ///////////////////////////////////////////////////////////////

    private Transition findTransition( State state, char c ) {
        for( int j = 0; j < table.length; j++ ) {
            Transition trans = table[j];
            if( trans.fromState == state && trans.chars.contains(c) ) {
                return trans;
            }
        }
        return null;
    }

    // Final states or those whose kind (of token) is not null, except for WHITESPACE (a special case).
    private static enum State {
        START(      null            ),
        //REGISTER(   Kind.REGISTER   ),
        ID(			Kind.ID			),
        
        ZERO(		Kind.NUM		),
        NUM(		Kind.NUM		),
        LPAREN(		Kind.LPAREN		),
        RPAREN(		Kind.RPAREN		),
        LBRACE(		Kind.LBRACE		),
        RBRACE(		Kind.RBRACE		),
        
        R(			Kind.ID			),
        RE(			Kind.ID			),
        RET(			Kind.ID			),
        RETU(			Kind.ID			),
        RETUR(			Kind.ID			),
        RETURN(			Kind.RETURN		),
        
        I(				Kind.ID			),
        IF(				Kind.IF			),
        IN(				Kind.ID			),
        INT(			Kind.INT		),
        
        E(			Kind.ID				),
        EL(			Kind.ID				),
        ELS(			Kind.ID			),
        ELSE(			Kind.ELSE		),
        
   
        W(			Kind.ID				),
        WH(			Kind.ID				),
        WHI(			Kind.ID			),
        WHIL(			Kind.ID			),
        WHILE(			Kind.WHILE		),
        WA(				Kind.ID			),
        WAI(			Kind.ID			),
        WAIN(			Kind.WAIN		),
        
        P(			Kind.ID			),
        PR(			Kind.ID			),
        PRI(			Kind.ID			),
        PRIN(			Kind.ID			),
        PRINT(			Kind.ID			),
        PRINTL(			Kind.ID			),
        PRINTLN(		Kind.PRINTLN		),
        
        BECOMES(	Kind.BECOMES			),
        
        EQ(			Kind.EQ			),
        
        EXCLA(		null		), //TODO: put a NOT(!) on the boolean expr.
        NE(			Kind.NE		),
        
        LT(		Kind.LT		),
        GT(		Kind.GT		),
        
        LE(		Kind.LE		),
        GE(		Kind.GE		),
        
        PLUS(	Kind.PLUS	),
        MINUS(	Kind.MINUS	),
        STAR(	Kind.STAR	),
        SLASH(	Kind.SLASH	),
        PCT(	Kind.PCT	),
        
        COMMENT(	Kind.COMMENT),
        
        COMMA(	Kind.COMMA	),
        SEMI(	Kind.SEMI	);

        
        
        //TODO: Comments, ++, --
        Kind kind;

        State( Kind kind ) {
            this.kind = kind;
        }

        boolean isFinal() {
            return kind != null;
        }
    }

    private interface CharSet {
        public boolean contains( char newC );
    }

    private class Chars implements CharSet {
        private String chars;
        public  Chars( String chars ) { this.chars = chars; }
        public  boolean contains( char newC ) {
            return chars.indexOf(newC) >= 0;
        }
    }

    private class AllChars implements CharSet {
        public boolean contains( char newC ) {
            return true;
        }
    }

    private class Transition {
        State   fromState;
        CharSet chars;
        State   toState;
        Transition( State fromState, CharSet chars, State toState ) {
            this.fromState = fromState;
            this.chars     = chars;
            this.toState   = toState;
        }
    }
    
    private Transition[] table;
}


