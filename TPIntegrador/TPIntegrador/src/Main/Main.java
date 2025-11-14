package Main;
import Service.AuthService;

public class Main {


    public static void main(String[] args) {
        AuthService auth = new AuthService();
        if (auth.loginInteractivo())
            AppMenu.main(args); 
    }
    
}