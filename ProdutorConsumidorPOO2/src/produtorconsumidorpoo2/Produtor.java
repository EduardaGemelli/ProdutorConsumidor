package produtorconsumidorpoo2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Produtor implements Runnable {
    private String nome;
    private Fila fila;
    private String estado;
//    Object predicado_produtor = new Object();

    public Produtor() {  }


    public Produtor(String nome, Fila fila) {
        this.nome = nome;
        this.fila = fila;
    }

    public String getNome() {  return nome;  }
    public void setNome(String nome) { this.nome = nome; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public void esperar() throws InterruptedException{
        estado = "esperando";
        System.out.println(nome + " " + estado + " - fila cheia.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void produzir() throws InterruptedException{
        estado = "produzindo";
        System.out.println(nome + " " + estado);
            Produto p = new Produto();
            fila.addProduto(p);
       
    }
    
    @Override
    public void run() {
    while(true){
            System.out.println(nome + " " + estado);
            if(!fila.filaCheia()){
                try {
                    produzir();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                estado = "dormindo";
                System.out.println(nome + " " + estado);
                fila.dormir_produtor();
            }
        }}
    
//    public void dormir_produtor() throws InterruptedException{
//      predicado_produtor.wait();               // wait faz ele dormir, e quando chamar notify ele acorda 
//    }
//    
//    public void acordar_produtor(){
//      predicado_produtor.notifyAll();   
//      //quando chama notify ele escolhe um da lista que está dormindo, e acorda só um, por isso chama o ALL aí acorda todos
//    }
    
  
}
