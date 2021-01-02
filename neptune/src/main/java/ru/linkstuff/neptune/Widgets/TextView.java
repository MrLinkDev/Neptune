package ru.linkstuff.neptune.Widgets;

import androidx.annotation.Nullable;

import ru.linkstuff.neptune.OpenGL.Texture;
import ru.linkstuff.neptune.Widgets.Framework.Font;
import ru.linkstuff.neptune.Widgets.Framework.Symbol;
import ru.linkstuff.neptune.Widgets.Framework.SymbolParams;
import ru.linkstuff.neptune.Widgets.Framework.Widget;

public class TextView implements Widget {
    public static final int ALIGNMENT_CENTER = 0;
    public static final int ALIGNMENT_LEFT = 1;
    public static final int ALIGNMENT_RIGHT = 2;

    public final int DEFAULT_CHAR_HEIGHT = 18;
    public final int DEFAULT_CHAR_SPACE = 4;

    private float x, y;
    private float width, height;
    private float WIDTH, HEIGHT;

    private float scale = 1;

    private boolean visible = true;
    private boolean oneLine = true;

    private int maxLines = 1;

    private Symbol[] symbols;
    private int alignment;

    public TextView(float x, float y, float width, float height, @Nullable String text){
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        WIDTH = width;
        HEIGHT = height;

        alignment = ALIGNMENT_CENTER;
        if (text != null) textToSymbols(text);
    }

    public TextView(float x, float y, float width, float height, @Nullable String text, int alignment) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        WIDTH = width;
        HEIGHT = height;

        this.alignment = alignment;
        if (text != null) textToSymbols(text);
    }

    private void textToSymbols(String text){
        char[] cachedText = text.toLowerCase().toCharArray();
        symbols = new Symbol[text.length()];

        float multiplier;
        float symbolX = 0;
        float symbolY = 0;

        if (oneLine){
            float textWidth = 0;

            for (int i = 0; i < cachedText.length; ++i){
                symbols[i] = new Symbol();
                symbols[i].id = SymbolParams.getSymbolId(cachedText[i]);
                textWidth += SymbolParams.getSymbolWidth(symbols[i].id);
                if (i != cachedText.length - 1) textWidth += DEFAULT_CHAR_SPACE;
            }

            cachedText = null;
            multiplier = height / SymbolParams.getSymbolHeight();
            if (textWidth * multiplier > width) multiplier = width / textWidth;

            switch (alignment){
                case ALIGNMENT_CENTER:
                    symbolX = x - textWidth * multiplier / 2;
                    break;
                case ALIGNMENT_LEFT:
                    symbolX = x - width / 2;
                    break;
                case ALIGNMENT_RIGHT:
                    symbolX = x + width / 2 - textWidth * multiplier;
                    break;
            }

            for (Symbol symbol : symbols) {
                symbolX += SymbolParams.getSymbolWidth(symbol.id) * multiplier / 2;

                symbol.x = symbolX;
                symbol.y = y;
                symbol.height = SymbolParams.getSymbolHeight() * multiplier;
                symbol.width = SymbolParams.getSymbolWidth(symbol.id) * multiplier;

                symbolX += symbol.width / 2 + DEFAULT_CHAR_SPACE * multiplier;
            }
        } else {
            multiplier = height / SymbolParams.getSymbolHeight();
            float textWidth = 0;
            int linesCount = 1;
            int lines = 1;

            for (int i = 0; i < cachedText.length; ++i){
                symbols[i] = new Symbol();
                symbols[i].id = SymbolParams.getSymbolId(cachedText[i]);
            }

            for (int i = 0; i < cachedText.length; ++i){
                textWidth += SymbolParams.getSymbolWidth(symbols[i].id) * multiplier;
                if (i != cachedText.length - 1) {
                    textWidth += DEFAULT_CHAR_SPACE * multiplier;
                    if ((textWidth + SymbolParams.getSymbolWidth(symbols[i + 1].id) * multiplier) > width){
                        if (linesCount == maxLines){
                            multiplier /= ++lines;
                            linesCount = 1;
                            textWidth = 0;
                            i = -1;

                            continue;
                        }
                        ++linesCount;
                        textWidth = 0;
                    }
                }
            }

            cachedText = null;

            symbolX = x - width / 2;
            symbolY = y + (height - SymbolParams.getSymbolHeight() * multiplier) / 2;

            for (int i = 0; i < symbols.length; ++i) {
                symbolX += SymbolParams.getSymbolWidth(symbols[i].id) * multiplier / 2;

                symbols[i].x = symbolX;
                symbols[i].y = symbolY;
                symbols[i].height = SymbolParams.getSymbolHeight() * multiplier;
                symbols[i].width = SymbolParams.getSymbolWidth(symbols[i].id) * multiplier;

                symbolX += symbols[i].width / 2 + DEFAULT_CHAR_SPACE * multiplier;
                if (i != symbols.length - 1 && (symbolX + SymbolParams.getSymbolWidth(symbols[i + 1].id) * multiplier) > x + width / 2){
                    symbolX = x - width / 2;
                    symbolY -= SymbolParams.getSymbolHeight() * multiplier;
                }
            }
        }
    }

    private void updateSymbols(){
        float multiplier;
        float symbolX = 0;
        float symbolY = 0;

        if (oneLine){
            float textWidth = 0;

            for (int i = 0; i < symbols.length; ++i){
                textWidth += SymbolParams.getSymbolWidth(symbols[i].id);
                if (i != symbols.length - 1) textWidth += DEFAULT_CHAR_SPACE;
            }

            multiplier = height / SymbolParams.getSymbolHeight();
            if (textWidth * multiplier > width) multiplier = width / textWidth;

            switch (alignment){
                case ALIGNMENT_CENTER:
                    symbolX = x - textWidth * multiplier / 2;
                    break;
                case ALIGNMENT_LEFT:
                    symbolX = x - width / 2;
                    break;
                case ALIGNMENT_RIGHT:
                    symbolX = x + width / 2 - textWidth * multiplier;
                    break;
            }

            for (Symbol symbol : symbols) {
                symbolX += SymbolParams.getSymbolWidth(symbol.id) * multiplier / 2;

                symbol.x = symbolX;
                symbol.y = y;
                symbol.height = SymbolParams.getSymbolHeight() * multiplier;
                symbol.width = SymbolParams.getSymbolWidth(symbol.id) * multiplier;

                symbolX += symbol.width / 2 + DEFAULT_CHAR_SPACE * multiplier;
            }
        } else {
            multiplier = height / SymbolParams.getSymbolHeight();
            float textWidth = 0;
            int linesCount = 1;
            int lines = 1;

            for (int i = 0; i < symbols.length; ++i){
                textWidth += SymbolParams.getSymbolWidth(symbols[i].id) * multiplier;
                if (i != symbols.length - 1) {
                    textWidth += DEFAULT_CHAR_SPACE * multiplier;
                    if ((textWidth + SymbolParams.getSymbolWidth(symbols[i + 1].id) * multiplier) > width){
                        if (linesCount == maxLines){
                            multiplier /= ++lines;
                            linesCount = 1;
                            textWidth = 0;
                            i = -1;

                            continue;
                        }
                        ++linesCount;
                        textWidth = 0;
                    }
                }
            }

            symbolX = x - width / 2;
            symbolY = y + (height - SymbolParams.getSymbolHeight() * multiplier) / 2;

            for (int i = 0; i < symbols.length; ++i) {
                symbolX += SymbolParams.getSymbolWidth(symbols[i].id) * multiplier / 2;

                symbols[i].x = symbolX;
                symbols[i].y = symbolY;
                symbols[i].height = SymbolParams.getSymbolHeight() * multiplier;
                symbols[i].width = SymbolParams.getSymbolWidth(symbols[i].id) * multiplier;

                symbolX += symbols[i].width / 2 + DEFAULT_CHAR_SPACE * multiplier;
                if (i != symbols.length - 1 && (symbolX + SymbolParams.getSymbolWidth(symbols[i + 1].id) * multiplier) > x + width / 2){
                    symbolX = x - width / 2;
                    symbolY -= SymbolParams.getSymbolHeight() * multiplier;
                }
            }
        }
    }

    public void setText(String text){
        textToSymbols(text);
    }

    public void setAlignment(int alignment){
        this.alignment = alignment;

        updateSymbols();
    }

    public void setMaxLines(int maxLines){
        this.maxLines = maxLines;
        oneLine = maxLines == 1;

        updateSymbols();
    }

    public void setOneLine(boolean oneLine){
        this.oneLine = oneLine;
        if (oneLine) maxLines = 1;

        updateSymbols();
    }

    public void draw(){
        if (visible) Font.draw(symbols);
    }

    @Deprecated
    @Override
    public void draw(Texture texture) {
        //Deprecated method
    }

    @Override
    public void setCoords(float x, float y) {
        float dX = x - this.x;
        float dY = y - this.y;

        this.x = x;
        this.y = y;

        for (Symbol symbol : symbols){
            symbol.x += dX;
            symbol.y += dY;
        }
    }

    @Override
    public void addCoords(float dX, float dY) {
        x += dX;
        y += dY;

        for (Symbol symbol : symbols){
            symbol.x += dX;
            symbol.y += dY;
        }
    }

    @Override
    public void setVisibility(boolean isVisible) {
        visible = isVisible;
    }

    @Override
    public void setScale(float scale) {
        width = WIDTH * scale;
        height = HEIGHT * scale;

        updateSymbols();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }
}
