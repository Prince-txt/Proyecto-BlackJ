import java.util.Random;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Convertidor de fichas hecho");

        Random random = new Random();

        int fichas = convertirfichas();

        boolean gamestart = true;

        while (gamestart && fichas > 0) {
            
            String[][] jugador = new String[10][2];
            String[][] Dealer = new String[10][2];

            int Cjugador = 0;
            int Cdealer = 0;

            int apuesta = pedir(fichas);

            Cjugador = repartir(jugador,Cjugador, random);
            Cjugador = repartir(jugador,Cjugador, random);

            Cdealer = repartir(Dealer, Cdealer, random);
            Cdealer = repartir(Dealer, Cdealer, random);
            
            boolean conti = true;

            while (conti) {
                mostrarmanos(jugador, Cjugador, "JUGADOR");

                int puntos = calcular(jugador, Cjugador);

                if (puntos > 21){

                    JOptionPane.showMessageDialog(null, "Tienes mas de 21");
                    
                    break;
                }

                int opcion = 0;

                try {
                    opcion = Integer.parseInt(JOptionPane.showInputDialog("1. Pedir carta" + "2. Quedarse"));

                    switch (opcion) {
                        case 1:
                            
                        Cjugador = repartir(jugador, Cjugador, random);
                            break;

                        case 2:
                        
                        conti = false;
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opcion invalida");
                    }
                }catch(Exception e){

                    JOptionPane.showMessageDialog(null,"Entrada invalida");
                }
            }
                

                while( calcular(Dealer, Cdealer) < 17 && calcular(jugador, Cjugador) <= 21){

                    Cdealer = repartir(Dealer, Cdealer, random);
                }

                mostrarmanos(Dealer, Cdealer,"Dealer");

                int Jpuntos = calcular(jugador,Cjugador);
                int Dpuntos = calcular(Dealer, Cdealer);

                if (Jpuntos > 21){

                    fichas -= apuesta;

                    JOptionPane.showMessageDialog(null, "Perdiste");
                }else if(Dpuntos > 21) {

                    fichas += apuesta;

                    JOptionPane.showMessageDialog(null, "Ganaste");
                }else if(Jpuntos > Dpuntos){

                    fichas += apuesta;
                    JOptionPane.showMessageDialog(null, "Ganaste");

                }else if(Jpuntos < Dpuntos){

                    fichas -= apuesta;
                    JOptionPane.showMessageDialog(null, "Perdiste");
                }else{
                    JOptionPane.showMessageDialog(null, "Empate");
                }

                JOptionPane.showMessageDialog(null, "Te quedan " + fichas + " fichas");

                int Fop = 0;
                boolean valido = false;

                while (!valido) {
                    
                    try {

                        Fop = Integer.parseInt(JOptionPane.showInputDialog("1.Seguir jugando " + "2. Salir"));

                        switch (Fop) {
                            case 1:
                                gamestart = true;
                                valido = true;
                                break;
                            case 2:
                                gamestart = false;
                                valido = true;

                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Opcion invalida");
                                break;
                        }
                    }catch (Exception e){

                        JOptionPane.showMessageDialog(null, "Por favor ingresar numeros validos");
                    }
                }
            }
             JOptionPane.showMessageDialog(null, "Juego terminado");
        }
    
    public static int calcular(String[][] mano, int cantidad){

        int total = 0;
        int ases = 0;

        for(int i = 0; i < cantidad; i++){

            String valor = mano[i][0];

            switch (valor) {
                case "A":
                    total += 11;

                    ases++;                    
                    break;
                case"J":
                case"Q":
                case"K":
                    total += 10;
                    break;
                 default:
                    total += Integer.parseInt(valor);
            }
        }

        while (total > 21 && ases > 0) {

            total -= 10;
            ases--;  
        }

        return total;
    }

    public static void mostrarmanos(String[][] mano, int cantidad, String nomnbre){
        
        String mensaje = nomnbre + "";
        
        for(int i = 0; i < cantidad; i++){

            for(int j = 0; j < 2; j++) {

                mensaje += mano[i][j] + " ";
            }
            mensaje += " ";
        }
        mensaje += " Puntos: " + calcular(mano, cantidad);

        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static int repartir(String[][] mano, int cantidad, Random random){

        String[] valores = {
            "A","2","3","4","5","6","7","8","9","10","J","Q","K"
        };

        String[] simbolo = {
            "Corazones", "Picas","Treboles","Diamantes"
        };

        mano[cantidad][0] = valores[random.nextInt(13)];
        mano[cantidad][1] = simbolo[random.nextInt(4)];

        cantidad++;

        return cantidad;

    }

    public static int pedir(int fichas){
        int apuesta = 0;
        boolean verd = false;

        while (!verd) {

            try{

                apuesta = Integer.parseInt(JOptionPane.showInputDialog("Tienes " + fichas + " fichas " + ", Ingrese su apuesta"));

                if (apuesta <= 0){

                    JOptionPane.showMessageDialog(null, "La apuesta debe ser mayor a 0");

                }else if(apuesta > fichas){

                     JOptionPane.showMessageDialog(null, "La apuesta es mayor a tu numero de fichas");
                }else{
                    
                    verd = true;

                }
            }catch(Exception e){

                JOptionPane.showMessageDialog(null, "Ingrese un dato valido");

            }
            
        }

        return apuesta;
    }

    

    public static int convertirfichas(){

        int fichas = 0;
        boolean verd = false;

        while (!verd) {
            try {   
                int dineros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese dinero en dolares para jugar. -el monto minimo es de 1000"));

                if (dineros <= 0) {
                    JOptionPane.showMessageDialog(null, "ingrese un valor mayor a 0");
                }else if(dineros % 1000 != 0){

                 JOptionPane.showMessageDialog(null,"Solo se permiten numeros redondos de 1000 en 1000");

                } else {

                    fichas = dineros/1000;

                    verd = true;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Entrada invalida");
            }   
        }
        return fichas;
    }

}