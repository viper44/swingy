package controller;

public class ExitController extends AbstractController {

    @Override
    public void process() {
        System.exit(1);
    }
}
