package view.console;

import controller.GameOwner;
import lombok.Data;
import model.characters.hero.DarkKnight;
import model.characters.hero.Hero;
import model.characters.hero.SpellHowler;
import model.characters.hero.TreasureHunter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
@Data
public class NewGame {
    private static Map<String, Class<? extends Hero>> list = new HashMap<>();


    private Class<? extends Hero> HeroType;
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
        System.out.println("SpellHowler           TreasureHunter          DarkKnight");
        System.out.print("Please choose Hero class: ");
        try {
            Scanner sc2 = new Scanner(System.in);
            if (sc2.hasNext("(SpellHowler|TreasureHunter|DarkKnight)")){
                HeroType = list.get(sc2.nextLine());
            } else {
                System.out.println("You entered wrong hero type");
                HeroTypeScanner();
            }
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
