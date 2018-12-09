package view.console;

import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import view.MoveViewInterface;


public class MoveView implements MoveViewInterface {
    static UnaryOperator<Integer> getMapSize = i-> (i - 1) * 5 + 10 - (i % 2);

   MoveView(){

   }

    public String drawMap(int level){
       Integer size = getMapSize.apply(level);
        System.out.print("     ");
        for (int i = 0; i < size; i++) {
            System.out.print('_');
        }
        System.out.println();
        for (int y = 1; y < size + 1; y++) {
            System.out.print("    |");
            for (int x = 1; x < size + 1; x++) {
//                if(GameOwner.getOwner().getHero().getCoordinates().getX() == x
//                        && GameOwner.getOwner().getHero().getCoordinates().getY() == y){
//                    System.out.print("H");
//                } else {
//                    System.out.print("*");
//                }
            }
            System.out.println("|");
        }
        System.out.print("     ");
        for (int i = 0; i < size; i++) {
            System.out.print('¯');
        }
        System.out.println();
        System.out.println("          ↑");
        System.out.println("        North");
        System.out.print("← West     ");
        System.out.println("   East →");
        System.out.println("        South");
        System.out.println("          ↓");

        return daWaeGetter();
    }

    private String daWaeGetter() {
        System.out.println("To enter main menu type menu");
        System.out.println("do you know da wae?");
        System.out.print("Please choose da wae (North, South, West, East): " );
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNext(Pattern.compile(("\\s*(North|South|East|West|Menu)\\s*"), Pattern.CASE_INSENSITIVE)))
       {
           System.out.println("You enter wrong way");
           System.out.println("To enter main menu type menu");
           sc.next();
           System.out.print("Please choose da wae (North, South, West, East): " );
       }
        return sc.nextLine();
    }
}
