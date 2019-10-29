package controller;

public class ExitController extends AbstractController implements Supporter {
    @Override
    public int supProcess() {
         System.exit(1);
         return 1;
    }

    @Override
    public void process() {
        System.exit(1);
    }
}
