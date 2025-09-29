package tp7;

public class Perro extends Animal{
    

    @Override
    public void hacerSonido() {
        System.out.println("Guau.");
    }

    @Override
    public void describirAnimal() {
        System.out.println("Soy un perro.");        
    }
    
    
}
