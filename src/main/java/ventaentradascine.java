import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.*;
public class ventaentradascine {
    static Random azar= new Random();
    static Scanner teclado= new Scanner(System.in);
    static ArrayList<int[][]> salas = new ArrayList<int[][]>();
    static ArrayList <Integer>precios = new ArrayList<Integer>();
    static int numeroDeSalas;
    static int[] precioCombos = {1500,2700,3200,5100,6800};
    static int[] compras = new int[2];
    static String [] alfabeto = new String [] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};


    public static void main (String[]args){
        numeroDeSalas=leer_Numero_de_salas();
        hacerCine();
        boolean salir=false;
        venta();
        venta();
    }

    public static int leer_Numero_de_salas(){
        boolean estado= false;
        int numero=-1;
        while (estado==false){
            numero=leerNumero(" el número de salas del cine");
            estado=validarNroSalas(numero);
            if(estado==false){ System.out.println("El número de salas debe ser mayor a 0.");}
        }
        return numero;
    }

    public static boolean validarNroSalas(int n){
        boolean e = false;
        if(n>0){e=true;}
        return e;
    }

    public static void hacerCine(){
        for(int i=0;i<numeroDeSalas;i++){
            int c= nroAlAzar(26);
            int f = nroAlAzar(100);
            int [][] sala = new int[f][c];
            int precioSala=ingresarPrecio();
            precios.add(precioSala);
            salas.add(sala);
        }
    }

    public static void venta(){
        int nrodeSala = elegirpelicula();
        int [][] salaSeleccionada = salas.get(nrodeSala);
        compras[0]=1;
        Integer i= precios.get(nrodeSala);
        int precio = i.intValue();
        mostarSala(salaSeleccionada);
        mostrarPrecio(nrodeSala);
        Butaca(salaSeleccionada);
        ventadeComestibles();
        hacerBoleta(precio);

    }

    public static void Butaca (int[][] salaSeleccionada){
        boolean disponible = false;
        int filas=-1, columnas=-1;
        while(disponible==false){
            filas= ingresarFila(salaSeleccionada)-1;
            columnas= ingresarColumnas(salaSeleccionada)-1;
            disponible=disponibilidadButaca(salaSeleccionada, filas, columnas);
            if (disponible==false){
                System.out.print("la butaca está ocupada");
            }
        }
        ocuparbutaca(salaSeleccionada,filas,columnas);

    }

    public static int ingresarColumnas(int [][] sala){
        boolean existe=false;
        String letra= "";
        int index = -1;
        while (existe==false){
            letra=leerletra();
            letra=letra.toLowerCase();
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            index=1+alphabet.indexOf(letra.charAt(0));
            if (index<=sala[1].length && index>=1){
                existe=true;
            }

        }

        return index;
    }

    public static String leerletra (){
        String letra="";
        while(letra.length()!=1){
            System.out.println("ingrese letra");
            letra="";
            letra=teclado.nextLine();
            if(letra.length()!=0){System.out.println("error");}
        }
      return letra;
    }

    public static boolean disponibilidadButaca(int[][] sala, int f, int c){
        boolean disponible = false;
        if(sala[f][c]==0){ disponible=true;}
        return disponible;
    }

    public static int ingresarFila(int[][] sala){
        boolean est=false;
        int max= sala.length;
        int f=0;
        while (est==false){
            f=leerNumero("ingrese la Fila de la butaca: 1-"+ max);
            est=validarFila(f,max);
            if (est==false){System.out.print("Ingrese un valor válido ");}
        }
        return f;
    }

    public static boolean validarFila(int f,int max){
        boolean e= false;
        if(f>=0 && f<max){e=true;}
        return e;
    }

    public static void mostarSala(int [][] sala){
        for(int a=0; a< sala[1].length; a++) {
            System.out.print(" ");
            System.out.print("  " + alfabeto[a] + "   ");
        }
        System.out.print("\n");
        for (int x=0; x < sala.length; x++) {
            for (int y = 0; y < sala[x].length; y++){ System.out.print(" | " + sala[x][y] + " | ");}
            System.out.println(x+1);
        }

    }

    public static int elegirpelicula(){
        boolean estado= false;
        int pelicula=-1;
        while(estado==false){
            pelicula=leerNumero("ingrese el numero de la pelicula que desee ver");
            estado=validarPelicula(pelicula);
            if (estado==false){System.out.println("Error! Ingresó un número inválido, vuelva a intentar");}
        }
        return pelicula-1;
    }

    public static boolean validarPelicula(int nro){
        boolean e= false;
        if(nro>=0 && nro<=numeroDeSalas){ e=true; }
        return e;
    }

    public static int leerNumero(String txt){
        System.out.println("ingrese"+txt);
        while (!teclado.hasNextInt()) {
            System.out.println("Error! debe ingresar un número");
            teclado.nextLine();
        }
        int number = teclado.nextInt();
        return number;
    }

    public static void ocuparbutaca(int [][] sala, int f, int c){
        sala[f][c]=1;
    }

    public static int nroAlAzar(int max){
        return azar.nextInt(max);
    }

    public static int ingresarPrecio(){
        return azar.nextInt(5000-2000);
    }

    public static void mostrarPrecio(int i){
        System.out.print("El Precio de una entrada en la sala"+(i+1)+"es de: $"+precios.get(i));
    }

    public static void hacerBoleta(int precioEntrada){

    }

    public static void ventadeComestibles(){
        System.out.println("Agrege un combo:");
        mostrarcombos();
        System.out.println("Ingrese el número de combo que desea ingresar");
        System.out.println("Ingrese 0 si no desea agregar combo");
        int combo = ingresarCombo();
        compras[1]= combo-1;
    }

    public static void mostrarcombos (){
        System.out.println("combo 1: bebida pequeña + palomitas pequeñas"+precioCombos [0]);
        System.out.println("combo 2: bebida pequeña + palomitas medianas"+precioCombos[1]);
        System.out.println("combo 3: 2 bebidas grandes + palomitas medianas"+precioCombos[2]);
        System.out.println("combo 4: 2 bebidas grandes + palomitas grandes"+precioCombos[3]);
        System.out.println("combo 5: 4 bebidas pequeñas + 2 palomitas grandes"+precioCombos[4]);

    }





}


