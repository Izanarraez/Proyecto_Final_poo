package entrada;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.ArrayList;

/**
 * Clase con utilidades para la entrada de datos desde teclado y fichero 
 * @author jvalvarez
 */
public class MyInput {
   // Lee una cadena de caracteres desde el teclado

    /**
     * M�todo que permite leer una cadena de caracteres del teclado
     * @return retorna una cadena de caracteres
     */
public static String readString() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in),1);
	String  string="";
	try {
		string = br.readLine(); }
	catch (IOException ex) {
		System.out.println(ex); }
	return string; }
// Lee un dato tipo int  desde el teclado

    /**
     * M�todo que permite leer un n�mero entero de simple precisi�n por teclado
     * @return retorna un n�mero entero de precisi�n simple
     */
public static int readInt(){
	try {
        return Integer.parseInt(readString());
    }catch (NumberFormatException ex) {
        System.out.println("La entrada es del tipo incorrecto");
        return readInt();
    }
     }
// Lee un dato tipo double  desde el teclado

    /**
     * M�todo que permite leer n�mero real por teclado.
     * @return retorna un n�mero de doble precisi�n.
     */
public static double readDouble() {
	return Double.parseDouble(readString()); }

    /**
     * M�todo que permite leer un n�mero entero por teclado.
     * @return retorna un n�mero entero comprendido entre -128 y 127 
     */
public static byte readByte() {
	return Byte.parseByte(readString()); }
// Lee un dato tipo short  desde el teclado

    /**
     * M�todo que permite leer un n�mero entero por teclado.
     * @return retorna un n�mero entero comprendido entre -32768 y 32767 
     */
public static short readShort() {
	return Short.parseShort(readString()); }
// Lee un dato tipo long desde el teclado

    /**
     * M�todo que permite leer un n�mero entero de doble precisi�n por teclado
     * @return retorna un n�mero entero de doble precisi�n.
     */
public static long readLong() {
	return Long.parseLong(readString()); }

//Lee un dato tipo float desde el teclado

    /**
      * M�todo que permite leer n�mero real por teclado
     * @return retorna un n�mero de precisi�n simple
     */
public static float readFloat() {
	return Float.parseFloat(readString()); }

    /**
     *
     * @param nombreFichero Es un String que contiene el path del fichero de texto. 
     * si el fichero est� en el raiz del proyecto, este String coincide con el nombre
     * del fichero con su extensi�n.
     * @return Un ArrayList de String con todas las palabras contenidas en el fichero de texto.
     */
    public static ArrayList <String> leeFichero(String nombreFichero){
    ArrayList <String> v= new ArrayList <String>();
    File fichero=null;FileReader fr=null;
    BufferedReader br=null;
    try{
        fichero=new File(nombreFichero);
        fr=new FileReader(fichero);br=new BufferedReader(fr);
        String linea;
        while ((linea=br.readLine())!=null){
        v.add(linea);}
    }catch (Exception e){
        e.printStackTrace();}
    finally {
        try {if (null!= fr){fr.close();
        br.close();}
        }catch (Exception e1){
            e1.printStackTrace();}
    }return v;
}

    /**
     * Serializa un objeto en el fichero indicado. El objeto pasado debe implementar {@link java.io.Serializable}.
     *
     * <ul>
     *   <li><b>Pre:</b> {@code a} debe ser un objeto serializable. </li>
     *   <li><b>Post:</b> Si la operación tiene éxito, el fichero contendrá la serializada
     *       del objeto. En caso de error se imprime un mensaje de error </li>
     * </ul>
     *
     * @param <A>           tipo del objeto a serializar (debe implementar {@link java.io.Serializable}).
     * @param a             objeto que será serializado.
     * @param nombreFichero fichero donde guardar la serialización.
     */
    public static <A> void serialize(A a, String nombreFichero) {
		System.out.println("Serializando...");
		try {
			FileOutputStream fos = new FileOutputStream(nombreFichero) ;
			ObjectOutputStream oos = new ObjectOutputStream(fos) ;
			oos.writeObject(a) ;
		} catch (Exception e) {
			System.err.println("Problem: "+e) ;
		}
    }

    /**
     * Deserializa un objeto desde el fichero indicado.
     *
     * <ul>
     *   <li><b>Pre:</b> {@code nombreFichero} debe apuntar a un fichero que contenga la serialización
     *       de un objeto compatible con el tipo que se obtiene del fichero.</li>
     *   <li><b>Post:</b> Devuelve el objeto deserializado si la operación es correcta, en caso de error se imprime un mensaje por la salida de error y el método devuelve {@code null}.</li>
     * </ul>
     *
     * @param <A> tipo de retorno esperado .
     * @param nombreFichero fichero con la serialización.
     * @return instancia de tipo {@code A}, o tipo {@code null} si falla el programa.
     */
    public static <A> A deserialize(String nombreFichero) {
		System.out.println("DeSerializing...");
		try {
			FileInputStream fis = new FileInputStream(nombreFichero) ;
			ObjectInputStream iis = new ObjectInputStream(fis) ;
			return (A) iis.readObject() ;
		} catch (Exception e) {
			System.err.println("Problem: "+e) ;
		}
		return null ;
	}
 
}
