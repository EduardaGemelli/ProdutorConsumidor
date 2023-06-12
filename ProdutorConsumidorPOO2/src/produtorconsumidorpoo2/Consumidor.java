package produtorconsumidorpoo2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable  {
    private String nome;
    private Fila fila;
    private String estado;
    //Object predicado_consumidor = new Object();

    public Consumidor() { }

    public Consumidor(String nome, Fila fila) {
        this.nome = nome;
        this.fila = fila;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome;  }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    @Override
    public void run() {
        while(true){
            System.out.println(nome + " " + estado);
            if(!fila.filaVazia()){
                fila.removerProduto();
            } else {
                estado = "dormindo";
                System.out.println(nome + " " + estado + " - fila vazia.");
                fila.dormir_consumidor();
            }
        }
    }
    
//    public void dormir_consumidor() throws InterruptedException{
//      predicado_consumidor.wait();
//    }
//    
//    public void acordar_consumidor(){
//      predicado_consumidor.notifyAll();
//    }
    
    public void esperar(){
        estado = "esperando";
        System.out.println(nome + " " + estado + " fila vazia.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
   
    
    
    
    
}
