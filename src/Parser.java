//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 16 "Parser.y"
import java.io.*;
//#line 19 "Parser.java"




public class Parser
             extends ParserImpl
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ASSIGN=257;
public final static short OR=258;
public final static short AND=259;
public final static short NOT=260;
public final static short EQ=261;
public final static short NE=262;
public final static short LE=263;
public final static short LT=264;
public final static short GE=265;
public final static short GT=266;
public final static short ADD=267;
public final static short SUB=268;
public final static short MUL=269;
public final static short DIV=270;
public final static short MOD=271;
public final static short IDENT=272;
public final static short INT_LIT=273;
public final static short BOOL_LIT=274;
public final static short BOOL=275;
public final static short INT=276;
public final static short FUNC=277;
public final static short IF=278;
public final static short THEN=279;
public final static short ELSE=280;
public final static short WHILE=281;
public final static short PRINT=282;
public final static short RETURN=283;
public final static short CALL=284;
public final static short BEGIN=285;
public final static short END=286;
public final static short LPAREN=287;
public final static short RPAREN=288;
public final static short VAR=289;
public final static short SEMI=290;
public final static short COMMA=291;
public final static short FUNCRET=292;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    7,    6,   22,    3,    8,    8,
    9,    9,   10,    6,    7,    7,    4,    4,    5,   13,
   13,   13,   13,   14,   14,   14,   14,   14,   14,   15,
   16,   17,   23,    4,    5,   11,   18,   19,   20,   11,
   11,   12,   12,   21,   21,   21,   21,   21,   21,
};
final static short yylen[] = {                            2,
    1,    2,    0,    1,    1,    1,    0,   12,    1,    0,
    3,    1,    3,    1,    1,    1,    1,    0,    4,    2,
    0,    2,    0,    1,    1,    1,    1,    1,    1,    4,
    3,    3,    0,    3,    4,    0,    7,    5,    4,    1,
    0,    3,    1,    3,    3,    3,    1,    1,    5,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    2,    4,    0,    0,    0,    0,    0,
   12,   16,    5,    0,    6,    0,    0,   13,    0,   11,
    0,   18,    0,    0,   33,   21,    0,   34,    0,    0,
    0,    0,    0,    0,    0,   18,    8,   20,   24,   25,
   26,   27,   28,   29,   19,    0,    0,    0,   47,   48,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   31,   32,    0,   30,    0,    0,    0,   46,
    0,   44,   39,    0,   38,    0,    0,    0,    0,   49,
    0,   37,    0,
};
final static short yydgoto[] = {                          1,
    2,    4,    5,   23,   25,   14,   15,    9,   10,   11,
   76,   77,   29,   38,   39,   40,   41,   42,   43,   44,
   53,   26,   28,
};
final static short yysindex[] = {                         0,
    0, -248, -234,    0,    0, -238, -236, -269, -247, -239,
    0,    0,    0, -228,    0, -229, -236,    0, -269,    0,
 -230,    0, -233, -269,    0,    0, -208,    0, -224, -225,
 -190, -219, -211, -237, -237,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -237, -237, -237,    0,    0,
 -197, -237, -258, -257, -233, -253, -246, -245, -210, -243,
 -237, -237,    0,    0, -212,    0, -176, -176, -237,    0,
 -187,    0,    0, -201,    0, -199, -204, -242, -176,    0,
 -237,    0, -242,
};
final static short yyrindex[] = {                         0,
    0,   88,    0,    0,    0,    0, -196,    0,    0, -189,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -200,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -188,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -185,    0,
 -260,    0,    0,    0,    0,    0, -184, -286,    0,    0,
    0,    0, -268,
};
final static short yygindex[] = {                         0,
    0,    0,    0,   55,    0,   76,   82,    0,    0,   91,
    0,    0,   56,  -28,    0,    0,    0,    0,    0,    0,
  -35,    0,    0,
};
final static int YYTABLESIZE=111;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         54,
   45,   43,   61,   61,   43,   12,   13,   61,   62,   62,
   56,   57,   58,   62,   61,   61,   60,   61,   61,   42,
   62,   62,   42,   62,   62,   71,   72,   45,    3,   45,
   45,   63,   64,   78,   49,   50,   66,    6,   74,   75,
   16,   67,   68,   18,   70,   83,   51,   31,    7,   52,
   82,   17,    8,   32,   22,   24,   33,   34,   35,   31,
   36,   37,   19,   30,   45,   32,   46,   47,   33,   34,
   35,    7,   36,   73,   59,   48,   69,    7,   79,   62,
    7,    7,    7,   17,    7,    7,   81,    1,   80,   17,
   55,   10,   17,   17,   17,   31,   17,   17,    9,   27,
   21,   32,   36,   40,   33,   34,   35,   20,   36,    0,
   65,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         35,
  261,  288,  261,  261,  291,  275,  276,  261,  267,  267,
   46,   47,   48,  267,  261,  261,   52,  261,  261,  288,
  267,  267,  291,  267,  267,   61,   62,  288,  277,  290,
  291,  290,  290,   69,  272,  273,  290,  272,   67,   68,
  288,  288,  288,  272,  288,   81,  284,  272,  287,  287,
   79,  291,  289,  278,  285,  289,  281,  282,  283,  272,
  285,  286,  292,  272,  290,  278,  257,  287,  281,  282,
  283,  272,  285,  286,  272,  287,  287,  278,  280,  267,
  281,  282,  283,  272,  285,  286,  291,    0,  288,  278,
   36,  288,  281,  282,  283,  272,  285,  286,  288,   24,
   19,  278,  288,  288,  281,  282,  283,   17,  285,   -1,
   55,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=292;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"ASSIGN","OR","AND","NOT","EQ","NE","LE","LT","GE","GT","ADD",
"SUB","MUL","DIV","MOD","IDENT","INT_LIT","BOOL_LIT","BOOL","INT","FUNC","IF",
"THEN","ELSE","WHILE","PRINT","RETURN","CALL","BEGIN","END","LPAREN","RPAREN",
"VAR","SEMI","COMMA","FUNCRET",
};
final static String yyrule[] = {
"$accept : program",
"program : decl_list",
"decl_list : decl_list decl",
"decl_list :",
"decl : fun_decl",
"prim_type : INT",
"type_spec : prim_type",
"$$1 :",
"fun_decl : FUNC IDENT LPAREN params RPAREN FUNCRET prim_type BEGIN local_decls $$1 stmt_list END",
"params : param_list",
"params :",
"param_list : param_list COMMA param",
"param_list : param",
"param : VAR type_spec IDENT",
"type_spec : prim_type",
"prim_type : INT",
"prim_type : BOOL",
"local_decls : local_decls",
"local_decls :",
"local_decl : VAR type_spec IDENT SEMI",
"stmt_list : stmt_list stmt",
"stmt_list :",
"stmt_list : stmt_list stmt",
"stmt_list :",
"stmt : assign_stmt",
"stmt : print_stmt",
"stmt : return_stmt",
"stmt : if_stmt",
"stmt : while_stmt",
"stmt : compound_stmt",
"assign_stmt : IDENT ASSIGN expr SEMI",
"print_stmt : PRINT expr SEMI",
"return_stmt : RETURN expr SEMI",
"$$2 :",
"local_decls : local_decls local_decl $$2",
"local_decl : VAR type_spec IDENT SEMI",
"args :",
"if_stmt : IF LPAREN expr RPAREN stmt ELSE stmt",
"while_stmt : WHILE LPAREN expr RPAREN stmt",
"compound_stmt : BEGIN local_decls stmt_list END",
"args : arg_list",
"args :",
"arg_list : arg_list COMMA expr",
"arg_list : expr",
"expr : expr ADD expr",
"expr : expr EQ expr",
"expr : LPAREN expr RPAREN",
"expr : IDENT",
"expr : INT_LIT",
"expr : CALL IDENT LPAREN args RPAREN",
};

//#line 159 "Parser.y"
    private Lexer lexer;
    private Token last_token;

    private int yylex () {
        int yyl_return = -1;
        try {
            yylval = new ParserVal(0);
            yyl_return = lexer.yylex();
            last_token = (Token)yylval.obj;
        }
        catch (IOException e) {
            System.out.println("IO error :"+e);
        }
        return yyl_return;
    }


    public void yyerror (String error) {
        //System.out.println ("Error message for " + lexer.lineno+":"+lexer.column +" by Parser.yyerror(): " + error);
        int last_token_lineno = 0;
        int last_token_column = 0;
        System.out.println ("Error message by Parser.yyerror() at near " + last_token_lineno+":"+last_token_column + ": " + error);
    }


    public Parser(Reader r, boolean yydebug) {
        this.lexer   = new Lexer(r, this);
        this.yydebug = yydebug;
    }
//#line 326 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
throws Exception
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 48 "Parser.y"
{  }
break;
case 2:
//#line 51 "Parser.y"
{ }
break;
case 3:
//#line 52 "Parser.y"
{  }
break;
case 4:
//#line 55 "Parser.y"
{ }
break;
case 5:
//#line 58 "Parser.y"
{  }
break;
case 6:
//#line 61 "Parser.y"
{  }
break;
case 7:
//#line 64 "Parser.y"
{ }
break;
case 8:
//#line 65 "Parser.y"
{ }
break;
case 9:
//#line 68 "Parser.y"
{ }
break;
case 10:
//#line 69 "Parser.y"
{ }
break;
case 11:
//#line 72 "Parser.y"
{}
break;
case 12:
//#line 73 "Parser.y"
{}
break;
case 13:
//#line 76 "Parser.y"
{}
break;
case 14:
//#line 79 "Parser.y"
{}
break;
case 15:
//#line 82 "Parser.y"
{}
break;
case 16:
//#line 83 "Parser.y"
{}
break;
case 17:
//#line 86 "Parser.y"
{}
break;
case 18:
//#line 87 "Parser.y"
{}
break;
case 19:
//#line 90 "Parser.y"
{}
break;
case 20:
//#line 93 "Parser.y"
{}
break;
case 21:
//#line 94 "Parser.y"
{}
break;
case 22:
//#line 100 "Parser.y"
{          }
break;
case 23:
//#line 101 "Parser.y"
{ }
break;
case 24:
//#line 104 "Parser.y"
{}
break;
case 25:
//#line 105 "Parser.y"
{}
break;
case 26:
//#line 106 "Parser.y"
{}
break;
case 27:
//#line 107 "Parser.y"
{}
break;
case 28:
//#line 108 "Parser.y"
{}
break;
case 29:
//#line 109 "Parser.y"
{}
break;
case 30:
//#line 114 "Parser.y"
{}
break;
case 31:
//#line 117 "Parser.y"
{}
break;
case 32:
//#line 124 "Parser.y"
{}
break;
case 33:
//#line 127 "Parser.y"
{}
break;
case 34:
//#line 128 "Parser.y"
{}
break;
case 35:
//#line 130 "Parser.y"
{}
break;
case 36:
//#line 133 "Parser.y"
{ }
break;
case 37:
//#line 136 "Parser.y"
{}
break;
case 38:
//#line 138 "Parser.y"
{}
break;
case 39:
//#line 140 "Parser.y"
{ }
break;
case 40:
//#line 142 "Parser.y"
{ }
break;
case 41:
//#line 143 "Parser.y"
{  }
break;
case 42:
//#line 146 "Parser.y"
{  }
break;
case 43:
//#line 147 "Parser.y"
{     }
break;
case 44:
//#line 150 "Parser.y"
{  }
break;
case 45:
//#line 151 "Parser.y"
{   }
break;
case 46:
//#line 152 "Parser.y"
{ }
break;
case 47:
//#line 153 "Parser.y"
{       }
break;
case 48:
//#line 154 "Parser.y"
{   }
break;
case 49:
//#line 155 "Parser.y"
{ }
break;
//#line 671 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
