package view.console;

import controller.GameOwner;
import lombok.Data;
import model.characters.hero.*;


import java.util.regex.Pattern;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
@Data
public class NewGame {
    private static Map<String, Class<? extends Hero>> list = new HashMap<>();


    private HeroClass HeroType;
    @NotNull
    @NotEmpty(message = "Please enter Hero name")
    @Size(max = 10, min = 3, message = "hero name is invalid")
    private String Name;
    public NewGame() {

    }

    public void StartNewGame(){
        HeroTypeScanner();
        NameScanner();
        GameOwner.getOwner().regHero();
    }

    private void NameScanner(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("The name must consist of letters and / or numbers (from 3 - 10 characters)");
            System.out.print("Please enter Hero name: ");
            this.Name = sc.nextLine();
        } catch (Exception e){

        }
        validation();
    }

    private void HeroTypeScanner() {
        System.out.println("SPELLHOWLER           TREASUREHUNTER          DARKKNIGHT");
        System.out.print("Please choose Hero class: ");
        try {
            Scanner sc2 = new Scanner(System.in);

            while(!sc2.hasNext(Pattern.compile("\\s*(SpellHowler|TreasureHunter|DarkKnight)\\s*", Pattern.CASE_INSENSITIVE))){
                System.out.println("You entered wrong hero type");
                sc2.nextLine();
                System.out.print("Please choose Hero class: ");
               //list.get(sc2.nextLine());
            }
            HeroType = HeroClass.valueOf(sc2.nextLine().toUpperCase());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void validation(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<NewGame>> constraintViolations = validator.validate(this);
        if (constraintViolations.size() > 0){
            for (ConstraintViolation<NewGame> violation : constraintViolations) {
                System.out.println(violation.getMessage());
                if (violation.getInvalidValue().equals(Name)){
                    NameScanner();
                }
            }
        }
    }

    static {
        list.put("SpellHowler", SpellHowler.class);
        list.put("TreasureHunter", TreasureHunter.class);
        list.put("DarkKnight", DarkKnight.class);
    }
}
