import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Convertidor de fichas hecho");

        int fichas = convertirfichas();
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

