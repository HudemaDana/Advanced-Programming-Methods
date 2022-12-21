package Model;

import Model.Abstraction.Item;

public class Bicycle implements Item {
    private String color;
    private int id;
    public Bicycle(String BicycleColor){
        color=BicycleColor;
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public void setId(int Id){
        id=Id;
    }
    @Override
    public String getColor(){
        return color;
    };

    @Override
    public void setColor(String newColor){
        color = newColor;
    };

    @Override
    public String DisplayObject(){
        return String.valueOf(id)+" Bicycle    "+color;
    }
}
