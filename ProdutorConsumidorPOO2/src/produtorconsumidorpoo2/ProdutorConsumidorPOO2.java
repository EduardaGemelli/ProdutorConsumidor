package produtorconsumidorpoo2;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutorConsumidorPOO2 {

    public static void main(String[] args) {
        Fila f = new Fila(10);
        Consumidor c = new Consumidor("consumidor", f);
        Produtor p = new Produtor("produtor", f);
        for(int i = 0; i < 20; i++){
            new Thread(c).start();
            new Thread(p).start();
        }
//        
//        ArrayList<Thread> arr = new ArrayList<Thread>();
//        Thread r1 = new Thread(c1).start();
//        Thread r2 = new Thread(p1).start();
        
//        arr.add(r1);
//        arr.add(r2);
//     
//        arr.forEach(a -> a.start());
//        arr.forEach(a -> {
//            try {
//                a.join();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
    }
    
}
