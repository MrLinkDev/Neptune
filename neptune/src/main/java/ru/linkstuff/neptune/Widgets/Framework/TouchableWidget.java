package ru.linkstuff.neptune.Widgets.Framework;


import ru.linkstuff.neptune.Framework.Android.Input;

public interface TouchableWidget extends Widget{
    public int onClick(Input.TouchEvent event);

    public void setClickable(boolean isClickable);
    public boolean isClickable();
}
