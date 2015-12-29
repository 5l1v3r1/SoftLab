package arraydemo.crud;

/**
 * Array is a basic data structure in which the elements
 * are stored in a fixed order
 */

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.startGame();
    }

    private void startGame(){
        Scoreboard scoreboard = new Scoreboard(10);

        // add scores
        scoreboard.add(new GameEntry("Ram",110));
        scoreboard.add(new GameEntry("Manoj",102));
        scoreboard.add(new GameEntry("Hikmat",150));
        scoreboard.add(new GameEntry("Jone",230));
        scoreboard.add(new GameEntry("Jone",230));

        System.out.println("After Addition: " + scoreboard.toString());

        // remove scores
        System.out.println(scoreboard.remove(1));
        System.out.println(scoreboard.remove(0));
        System.out.println(scoreboard.remove(0));
        System.out.println(scoreboard.remove(0));

        System.out.printf("After Removal: " + scoreboard.toString());
    }
}
