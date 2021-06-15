import com.fasterxml.jackson.core.JacksonException;
import com.zombiecastlerush.util.Game;

class Main {
    public static void main(String[] args){
        System.out.println("Zombie Castle Rush Demo");
        Game ZombieCastleRush = Game.getInstance();
        try {
            ZombieCastleRush.start();
        } catch (JacksonException je){
            System.out.println(je.getMessage());
        }
    }
}
