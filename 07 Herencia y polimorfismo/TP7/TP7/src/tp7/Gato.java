package tp7;

public class Gato extends Animal {
    
    @Override
    public void hacerSonido() {
        System.out.println("Miau.");
    }

    @Override
    public void describirAnimal() {
        System.out.println("Soy un gato.");        
    }
    
    
}
