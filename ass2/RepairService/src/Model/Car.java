package Model;
import Model.Abstraction.Item;

public class Car implements Item {
    private String color;
    private int id;

    public Car(String CarColor){
        color=CarColor;
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
        return String.valueOf(id)+" Car        "+color;
    }


}
