package view;

public interface ComplexView<I> extends View {

    void render(I payload);
}
