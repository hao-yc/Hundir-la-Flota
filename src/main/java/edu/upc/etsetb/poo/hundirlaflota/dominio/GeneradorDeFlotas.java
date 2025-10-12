/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.hundirlaflota.dominio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 *
 * @author Juan Carlos Cruellas at Universidad Politécnica de Cataluña
 */
public class GeneradorDeFlotas {

    /**
     * Array invariable con los distintivos de los diferentes tipos de barco
     */
    private static final String[] ARRAY_TIPOS_BARCOS = {"P", "B", "S", "C", "L"};

    /**
     * Array invariable con los distintivos de las dos direcciones: horizontal
     * ("H") y vertical ("V")
     */
    private static final String[] ARRAY_DIRECCIONES = {"H", "V"};

    /**
     * Lista con los distintivos de los diferentes tipos de barcos
     */
    private static final List<String> TIPOS_BARCOS
            = Arrays.asList(GeneradorDeFlotas.ARRAY_TIPOS_BARCOS);

    /**
     * Lista con los distintivos de las dos direcciones
     */
    private static final List<String> DIRECCIONES
            = Arrays.asList(GeneradorDeFlotas.ARRAY_DIRECCIONES);

    /**
     * Constructor sin argumentos: no hace nada
     */
    public GeneradorDeFlotas() {}

    /**
     * Genera una flota determinista (ver descripción larga debajo).
     *
     * Genera la siguiente flota (siempre la misma)para el jugador pasado como
     * parámetro:
     * <ul>
     * <li>Lanchas en A6, D1, y J10</li>
     * <li>Cruceros en B10 dispuesto VERTICALMENTE, y en G9 dispuesto
     * HORIZONTALMENTE</li>
     * <li>Submarinos en B1 dispuesto HORIZONTALMENTE y en H1 dispuesto
     * HORIZONTALMENTE</li>
     * <li>Buque en G6 dispuesto VERTICALMENTE</li>
     * <li>Portaviones en E4 dispuesto HORIZONTALMENTE</li>
     * </ul>
     * Debe usar los métodos Barco::creaBbarco y Jugador::ponBarco.
     *
     * @param jugador el jugador para el que se crea la flota
     *
     * @throws PositionException si alguna posición en la que pretende dejarse
     * algún barco es incorrecta (no debería lanzarse si todos los métodos están
     * bien diseñados e implementador)
     */
    public void generaFlotaDeterminista(Jugador jugador) throws PositionException {
    Barco lancha1 = Barco.creaBarco("L", "Lancha1");
    Barco lancha2 = Barco.creaBarco("L", "Lancha2");
    Barco lancha3 = Barco.creaBarco("L", "Lancha3");
    Barco portaviones = Barco.creaBarco("P", "Portaviones");
    Barco buque = Barco.creaBarco("B", "Buque");
    Barco submarino1 = Barco.creaBarco("S", "Submarino1");
    Barco submarino2 = Barco.creaBarco("S", "Submariono2");
    Barco crucero1 = Barco.creaBarco("C", "Crucero1");
    Barco crucero2 = Barco.creaBarco("C", "Crucero2");
   
     jugador.ponBarco(lancha1, "A6", "H");
    jugador.ponBarco(lancha2, "D1", "H");
    jugador.ponBarco(lancha3, "J10", "H");
    jugador.ponBarco(portaviones, "E4", "H");
    jugador.ponBarco(buque, "G6", "V");
    jugador.ponBarco(submarino1, "B1", "H");
    jugador.ponBarco(submarino2, "H1", "H");
    jugador.ponBarco(crucero1, "B10", "V");
    jugador.ponBarco(crucero2, "G9", "H");
    
    }

    /**
     * Crea y coloca un barco del tipo y con el nombre pasados como argumento en
     * una posición seleccionada aleatoriamente con el argumento random, para el
     * jugador pasado como argumento (ver descripción detallada debajo).
     *
     * El método:
     * <ol>
     * <li>Debe crear el barco del tipo pasado como argumento con el nombre
     * pasado como argumento (invocando a Barco.crea_barco).</li>
     * <li>Debe generar una fila entre 0 y 9 y una columna entre 1 y 10 usando
     * el objeto random pasado como argumento (método nextInt).</li>
     * <li>Debe generar un entero entre 0 y 1. Si es 0, la dirección del barco
     * será horizontal. Si es 1, será vertical.</li>
     * <li>Debe construir la coordenada a partir de la fila y la columna
     * generadas en el segundo paso. Si la fila es 0, entonces la letra de la
     * posición será 'A', y así sucesivamente.</li>
     * <li>Finalmente debe colocar el barco en esa posición y en la dirección
     * obtenidas (método Jugador::ponBarco ).</li>
     * </ol>
     *
     * @param jugador el jugador para el que se creará y pondrá el barco
     *
     * @param tipoBarco el tipo del barco a crear ("L": lancha, "C": crucero,
     * "S": submarino, "B": buque, "P": portaviones)
     *
     * @param nombreBarco el nombre del barco
     *
     * @param random objeto Random para obtener enteros aleatoriamente
     */
    public void generaYPonBarco(Jugador jugador, String tipoBarco,String nombreBarco, Random random) {
    Barco barco = Barco.creaBarco(tipoBarco, nombreBarco);
    int i=0;
    boolean barcoColocado = false;
    while (!barcoColocado) {
        int fila = random.nextInt(10);
        int columna = random.nextInt(10) + 1;
        int direccion = random.nextInt(2);
        String letraFila = String.valueOf((char) ('A' + fila));
        String posicion = letraFila + columna;
        String direccionStr;
        if (direccion == 0) {
            direccionStr = "H";
        } else {
            direccionStr = "V";
        }       
        try {       
            
            jugador.ponBarco(barco, posicion, direccionStr);
            barcoColocado = true;
        } catch (PositionException e) {
        }
    }  
    }

    /**
     * Genera una flota aleatoria para el jugador pasado como argumento; debe
     * invocar al método generaYPonBarco() de esta misma clase.
     *
     * @param jugador el jugador para el que se generará la flota aleatoria
     */
    public void generaFlotaAleatoria(Jugador jugador) {
        
    Random random = new Random(System.currentTimeMillis());
    generaYPonBarco(jugador, "L", "Lancha2", random);
    generaYPonBarco(jugador, "L", "Lancha1", random);
    generaYPonBarco(jugador, "L", "Lancha3", random);
    generaYPonBarco(jugador, "P", "Portaviones", random);
    generaYPonBarco(jugador, "B", "Buque", random);
    generaYPonBarco(jugador, "S", "Submarino1", random);
    generaYPonBarco(jugador, "S", "Submarino2", random);
    generaYPonBarco(jugador, "C", "Crucero1", random);
    generaYPonBarco(jugador, "C", "Crucero2", random);

    }

    /**
     * Crea y deposita un barco del jugador pasado como argumento a partir de la
     * información contenida en la línea pasada como argumento (ver descripción
     * detallada debajo).
     *
     * Este método toma el argumento línea, la trocea en sus partes, comprueba
     * si su sintaxis es correcta y si es así crea un nuevo barco según las
     * indicaciones de esa línea. La línea debe responder al siguiente patrón:
     * <br>&lt;TIPO_BARCO&gt; &lt;POSICION&gt; &lt;DISPOSICION DEL BARCO&gt; 
     * &lt;NOMBRE DEL BARCO&gt;<br>
     * Donde:
     * <ul>
     * <li>&lt;TIPO_BARCO&gt; será una letra indicando el tipo de barco ("L": lancha,
     * "C": crucero, "S": submarino, "B": buque, "P": portaviones).</li>
     * <li>&lt;POSICION&gt; será la posición de la casilla a partir de la cual
     * comenzará a depositarse el barco (A6, por ejemplo).</li>
     * <li>&lt;DISPOSICION DEL BARCO&gt; será un caracter que indicará si el barco se
     * dispondrá horizontal (H) o verticalmente (V).</li>
     * <li>&lt;NOMBRE DEL BARCO&gt; será un string con el nombre del barco.</li>
     * </ul>
     * Ejemplo. La línea:
     * <br>L A6 V Lancha_1
     * <br>NOTA: Los 4 componentes están separados por un caracter blanco.
     * <br>Ordenaría la creación de una LANCHA (L) conel nombre Lancha_1, y su
     * colocación en VERTICAL a partir de la casilla de posición A6.
     *
     * @param jugador el jugador para el que se genera el barco
     * @param linea la línea con los datos
     * @param nLinea el número de orden que la línea ocupa en el fichero
     * 
     * @throws ArchivoFlotaException si la línea cumple alguna de las siguientes
     * condiciones:
     * <ol>
     * <li>La línea no puede trocearse en 4 partes.</li>
     * <li>El primer caracter no blanco de la línea NO se corresponde con ningún
     * indicativo de tipo de barco.</li>
     * <li>El valor de &lt;DISPOSICION DEL BARCO&gt; no es ni 'H' ni 'V'.</li>
     * </ol>
     * 
     * @throws PositionException si el barco debiera ocupar alguna posición 
     * incorrecta
     */
    public void generaBarcoDeLinea(Jugador jugador, String linea, int nLinea)throws ArchivoFlotaException, PositionException {
        String partes[]=linea.split(" ");
        if (partes.length != 4) {
        throw new ArchivoFlotaException("La línea no puede separarse en 4 partes.");
        }
        String tipoBarco = partes[0];
         if (!Arrays.asList(ARRAY_TIPOS_BARCOS).contains(tipoBarco)){ 
            throw new ArchivoFlotaException ("TipoBarco incorrecto");}
         
        String posicionStr = partes[1];    
        if(!Posicion.esCorrecta(posicionStr)){
            throw new PositionException("error en la posicion");
        }
        String direccionStr = partes[2];
        if (!Arrays.asList(ARRAY_DIRECCIONES).contains(direccionStr)){ 
            throw new ArchivoFlotaException ("La dirección del barco debe ser 'H' o 'V'");}
        
        String nombre = partes[3];
        Barco barcos = Barco.creaBarco(tipoBarco, nombre);
        jugador.ponBarco(barcos,posicionStr, direccionStr);
        
    }

    /**
     * Lee una flota de un archivo (ver definición completa debajo).
     *
     * Este método abre el archivo cuyo nombre se pasa en el argumento
     * path_fichero, lee una a una sus líneas e invoca al método
     * generaBarcoDeLinea de esta misma clase para que genere un barco según el
     * contenido de esa línea. Cuando ha acabado de leer todas las líneas y de
     * invocar con todas ellas al método anterior, el método cierra el archivo.
     *
     * @param jugador el jugador para el que se creará la flota
     *
     * @param pathFichero el nombre completo (pathname) del archivo de texto a
     * leer.
     *
     * @throws IOException si hay una operación de apertura o lectura de archivo
     * incorrecta.
     *
     * @throws ArchivoFlotaException si el archivo contiene algún error (ver 
     * la especificación del método generaBarcoDeLinea)
     *
     * @throws PositionException si alguna posición de algún barco es incorrecta
     */
    public void leeFlotaDeArchivo(Jugador jugador, String pathFichero)throws IOException, ArchivoFlotaException, PositionException {
    
   File archivo = new File(pathFichero);
    BufferedReader br = null;
    try {
        br = new BufferedReader(new FileReader(archivo));
        String linea;
        int nLinea=0;
        while ((linea = br.readLine()) != null) {
            nLinea++;
            generaBarcoDeLinea(jugador, linea,nLinea);
        }
    } catch (IOException e) {
        throw new ArchivoFlotaException("Error al leer el archivo");
    } 
    br.close();
    
    }
  
}
