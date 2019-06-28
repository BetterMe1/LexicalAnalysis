package LexicalAnalysis;

import java.util.HashMap;

public class STDControl {
    private String[] keyTable = {"auto", "break", "case", "char", "const", "continue",
            "default", "do", "double", "else", "enum", "extern",
            "float", "for", "goto", "if", "int", "long", "return", "short", "signed", "sizeof", "static",
            "struct", "switch", "typedef", "union", "unsigned", "void",
            "volatile", "while" };
    private String[] charTable = { "=", "+","-", "*", "/"};
    private char[] DepartTable = { '(',')',',','{','}'};
    private char[] charT = {  '=', '+','-', '*','/'};
    private String tempString = "";
    private char ch;//存放最新读进的源程序字符
    private int index;
    private String[] store = new String[50];
    private int storeIndex = 0;
    private int num = 0;
    private HashMap<String, String> hashmap = new HashMap<>();
    private HashMap<String, Integer> hashmap1 = new HashMap<>();

    public STDControl( String tString) {
        tempString = tString;
        index = 0;
        hashmap . put( "auto" , "$auto" );
        hashmap.put( "break","$break" );
        hashmap . put( "case", "$case" );
        hashmap . put( "const" , "$const" );
        hashmap . put( "continue" , "$continue" );
        hashmap . put( "default" , "$default" );
        hashmap . put( "do" , "$do" );
        hashmap . put( "double" , "$double" );
        hashmap . put( "else" , "$else" );
        hashmap . put( "enum" , "$enum" );
        hashmap . put( "extern" , "$extern" );
        hashmap . put( "float" , "$float" );
        hashmap . put( "for" , "$for" );
        hashmap . put( "goto" , "$goto" );
        hashmap . put( "if" , "$if" );
        hashmap . put( "int" , "$int" );
        hashmap . put( "long" , "$long" );
        hashmap . put( "return" , "$return" );
        hashmap . put( "short" , "$short" );
        hashmap . put( "signed" , "$signed" );
        hashmap . put( "sizeof" , "$sizeof" );
        hashmap . put( "static" , "$static" );
        hashmap . put( "struct" , "$struct" );
        hashmap . put( "switch" , "$switch" );
        hashmap . put( "typedef" , "$typedef" );
        hashmap . put( "union" , "$union" );
        hashmap . put( "unsigned" , "$unsigned" );
        hashmap . put( "void" , "$void" );
        hashmap . put( "volatile" , "$volatile" );
        hashmap . put( "while" , "$while" );
        hashmap.put( "标识符:","$ID" );
        hashmap.put( "数字:", "$ASSIGN" );
        hashmap . put( "=" , " $ASSIGN" );
        hashmap. put( "+","$PLUS" );
        hashmap. put( "-","$SUB" );
        hashmap . put( "*","$STAR" );
        hashmap. put( "**", "$POWER" );
        hashmap . put( "," , "$COMMA" );
        hashmap. put( "(", "$LPAR" );
        hashmap.put( ")","$RPAR" );
        hashmap. put( "{", "$LPAR" );
        hashmap.put( "}","$RPAR" );

        hashmap1 . put( "auto" , 1 );
        hashmap1.put( "break",2 );
        hashmap1 . put( "case", 3 );
        hashmap1 . put( "const" , 4);
        hashmap1 . put( "continue" , 5 );
        hashmap1 . put( "default" , 6 );
        hashmap1 . put( "do" , 7 );
        hashmap1 . put( "double" ,8);
        hashmap1 . put( "else" , 9 );
        hashmap1 . put( "enum" , 10 );
        hashmap1 . put( "extern" ,11 );
        hashmap1. put( "float" , 12 );
        hashmap1 . put( "for" , 13 );
        hashmap1 . put( "goto" , 14 );
        hashmap1 . put( "if" ,15 );
        hashmap1 . put( "int" ,16 );
        hashmap1 . put( "long" ,17 );
        hashmap1 . put( "return" , 18 );
        hashmap1 . put( "short" , 19 );
        hashmap1 . put( "signed" ,20);
        hashmap1 . put( "sizeof" , 21 );
        hashmap1 . put( "static" , 22 );
        hashmap1 . put( "struct" , 23);
        hashmap1 . put( "switch" , 24 );
        hashmap1. put( "typedef" , 25);
        hashmap1 . put( "union" ,26 );
        hashmap1 . put( "unsigned" , 27 );
        hashmap1 . put( "void" , 28 );
        hashmap1 . put( "volatile" ,29 );
        hashmap1 . put( "while" , 30 );
        hashmap1. put( "标识符:",31 );
        hashmap1. put( "数字:",32);
        hashmap1.put( "=",33 );
        hashmap1. put( "+",34 );
        hashmap1. put( "-",35 );
        hashmap1. put( "*",36 );
        hashmap1. put( "/",37 );
        hashmap1.put( ",",38);
        hashmap1. put( "(",39 );
        hashmap1. put( ")",40 );
        hashmap1. put( "(",41 );
        hashmap1. put( ")",42 );
        hashmap1.put("{",43);
        hashmap1.put("}",44);
    }

    public void setString( String s ){
        tempString = s;
    }
    //判断是否为字母
    private boolean IsLetter( char tChar ){
        if( (tChar >= 'a' && tChar <='z') || ( tChar >= 'A' && tChar <= 'Z' ))
            return true ;
        return false;
    }
    //判断是否为数字
    private boolean IsDigit( char tChar ){
        if( tChar >='0' && tChar <= '9')
            return true ;
        return false;
    }
    //判断是否是运算符
    private boolean IsSymbol( char tChar ){
        for( int i = 0; i < charT.length; i++ ){
            if( tChar == charT[i] )
                return true;
        }
        return false ;
    }
    //判断是否为分隔符
    private boolean IsDepart( char tChar ){
        for( int i = 0; i < DepartTable.length; i++ ){
            if( tChar == DepartTable[i] )
                return true;
        }
        return false;
    }
    //判断是否为关键字
    private int checkKey( String a ){
        for( int i = 0; i < keyTable.length; i++ ){
            if( a.equals( keyTable[i] ))
                return i;
        }
        return -1;
    }
    public int getLength(){
        praseString();
        for( int i = 0; i < storeIndex; i++ ) {
            if (store[0].equals(null)) {
                System.out.println(store[i]);
            } else{
                num++;
            }
        }
        return num;
    }
    public void getStore( String[] a){
        for(int i=0;i<num;i++){
            a[i] = store[i];
        }
    }
    private void praseString(){
        String temp = "";
        String storeTemp = "";
        while( index < tempString. length()) {
            ch = tempString.charAt(index);
            if (IsLetter(ch)) {//如果是a到z
                while (IsLetter(ch) || IsDigit(ch) ) {
                    temp = temp + ch;//继续读下一个
                    index++;
                    if(index < tempString. length()){
                        ch = tempString.charAt(index);
                    }else{
                        break;
                    }
                }
                int keyNum = checkKey(temp);//查看标识符是不是关键字
                if (keyNum < 0) {
                    int a = (int) hashmap1.get("标识符:");
                    String s = hashmap.get("标识符:");
                    storeTemp = " 标识符: " + temp + "  " + s + "  " + a;
                    store[storeIndex] = storeTemp;//存起来
                    storeIndex++;
                    temp = "";
                } else {
                    System.out.println(temp);
                    int a = (int) hashmap1.get(temp);
                    String s = hashmap.get(temp);
                    storeTemp = "关键字" + temp + "  " + s + "  " + a;//否则是关键字
                    store[storeIndex] = storeTemp;//存起来
                    storeIndex++;
                    temp = "";
                }
            } else if (IsDigit(ch)) {//如果是数宇
                boolean flag = true;
                while (IsDigit(ch)) {//是数字一直读
                    temp = temp + ch;
                    index++;
                    ch = tempString.charAt(index);// IFi=0 D0 i=i+1
                    if (IsLetter(ch)) {
                        temp = temp + ch;
                        index++;
                        System.out.println(temp + " 错误标识符！！");
                        temp = "";
                        flag = false;
                    }
                }
                if(flag) {
                    int a = (int)hashmap1.get("数字:");
                    String s = hashmap.get("数字:");
                    storeTemp = "数字:"+temp+"  "+s+"  "+a;
                    store[storeIndex] = storeTemp;
                    storeIndex++;
                    temp="";
                }
            }else if( IsSymbol(ch)){
                temp = temp + ch;
                index++;
                if( tempString.charAt( index ) == '*' ){
                    temp = temp + ch;
                    index++;
                }
                int a = (int)hashmap1.get(temp);
                String s = hashmap.get(temp);
                storeTemp = "运算符"+temp+"  "+s+"  "+a;
                store[ storeIndex] = storeTemp ;
                storeIndex++;
                temp = "";
            }else if(IsDepart(ch)){
                temp = temp + ch;
                index++;
                System.out.println(temp);
                int a = (int)hashmap1.get(temp);
                System.out.println(a);
                String s = hashmap.get(temp);
                storeTemp = "分隔符:"+temp+"  "+s+"  "+a;
                store[ storeIndex] = storeTemp ;
                storeIndex++;
                temp = "";
            }else if(ch == ' '){
                index++;
                temp = "";
            }else{
                temp = "";
                index++;
            }
        }
    }
}
