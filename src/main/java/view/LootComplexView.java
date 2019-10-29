package view;

import model.GameContext;

public interface LootComplexView<I> extends View {
	void render(I payload, GameContext context);
}
