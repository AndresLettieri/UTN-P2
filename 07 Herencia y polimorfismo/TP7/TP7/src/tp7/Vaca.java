package tp7;

public class Vaca extends Animal{
    
    @Override
    public void hacerSonido() {
        System.out.println("Muuu.");
    }

    @Override
    public void describirAnimal() {
        System.out.println("Soy una vaca.");        
    }
        
}
