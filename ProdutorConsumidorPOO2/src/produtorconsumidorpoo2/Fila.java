package produtorconsumidorpoo2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fila {
    private int tamanho;
    private BlockingDeque<Produto> fila;
    private final Object predicado_consumidor = new Object();
    private final Object predicado_produtor = new Object();

    public Fila(int tamanho) {
        this.tamanho = tamanho;
        fila = new LinkedBlockingDeque<>(tamanho);
    }

    public int getTamanho() { return tamanho; }
    public void setTamanho(int tamanho) { this.tamanho = tamanho; }
    
    //consumidor
    public void dormir_consumidor() {
        synchronized(predicado_consumidor){
            try {
                predicado_consumidor.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Fila.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void acordar_consumidor(){
        synchronized(predicado_consumidor){
            predicado_consumidor.notifyAll();  
        }
    }
    
    //produtor
    public void dormir_produtor() {
        synchronized(predicado_produtor){
            try {  
                predicado_produtor.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Fila.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void acordar_produtor(){
        synchronized(predicado_produtor){
            predicado_produtor.notifyAll(); 
        }
    }
    
    public boolean filaCheia(){
        return fila.size() == tamanho;      
    }
    public boolean filaVazia(){
        return fila.isEmpty();
    }
    
    public synchronized Produto removerProduto(){
        return fila.poll();
    }
    
    public synchronized void addProduto(Produto p) throws InterruptedException{
        fila.put(p);
        acordar_consumidor();
    }
    
     public Produto pegarProduto(){
         Produto temp = fila.poll();
         acordar_produtor();
         return temp;
    }

    
//    //queue (fila) stack (pilha)  
//   //boolean vazio (isFull) e cheio (isEmpty)  -> cheio: return tamanhoLista.size() == max; vazia: return fila.isEmpty();
    
//    //addProduto -> produtor chama        -> fila.addProduto(nº)
//    //retirarProduto -> consumidor chama  -> fila.retirarProduto(nº)     -> poll método de remover
    
    //aguardar_produtor
    //aguardar_consumidor
    
    
    
//    //object predicado_produtor = new object();
//    //object predicado_consumidor = new object();
//    
//    
//    //produtor
//    //public void dormir_produtor(){
//    //  predicado_produtores.WAIT();               -> quando chamar notify ele acorda 
//    //}
//    
//    //public void acordar_produtor(){
//    //  predicado_produtores.NOTIFYALL();       -> quando chama notify ele escolhe um da lista que está dormindo, e acorda só um, por isso chama o ALL
//    //}
//    // ------------------------------------
//    //consumidor
//    //public void dormir_consumidor(){
//    //  predicado_consumidor.WAIT();               
//    //}
//    
//    //public void acordar_consumidor(){
//    //  predicado_consumidor.NOTIFYALL();      
//    //}
}
