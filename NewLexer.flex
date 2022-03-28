package Jflextest;
import static Jflextest.Token.*;
%%
%class NewLexer
%type Token


white=[ \n\t\r]

D = [0-9]+

entero = {D}
flotante = {D}"."{D}
exponente = ({entero}|{flotante})[E][+-]?{entero}


%{
	public String lexeme;
%}
%%


"="	{return igual;}
"+"	{return suma;}
"-"	{return menos;}
"*"	{return multi;}
"("	{return PI;}
")"	{return PD;}
"a"	{return a;}

{entero} {lexeme=yytext(); return entero; }
{flotante} {lexeme=yytext(); return flotante; }
{exponente} {lexeme=yytext(); return exponente; }

{white} {/*ignore*/}



. {return ERROR;}