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
        }
        
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
                    JOptionPane.showInputDialog(null, "ingrese un valor mayor a 0");
                }else if(dineros % 1000 != 0){

                 JOptionPane.showInputDialog(null,"Solo se permiten numeros redondos de 1000 en 1000");

                } else {

                    fichas = dineros/1000;

                    verd = true;
                }
            }catch(Exception e){
                JOptionPane.showInputDialog(null, "Entrada invalida");
            }   
        }
        return fichas;
    }

    


}