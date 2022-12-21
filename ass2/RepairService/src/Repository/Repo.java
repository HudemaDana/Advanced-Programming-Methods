package Repository;
import Model.Abstraction.Item;
import Repository.Abstraction.IRepo;

public class Repo implements IRepo {

    private Item[] listOfVehicles;
    private int length = 0;

    public Repo(){

        listOfVehicles = new Item[10];
    }
    public Item[] GetItems(){
        return listOfVehicles;
    };
    public int getSize(){
        return length;
    }
    public void AddItem(Item newItem){
        newItem.setId(length);
        listOfVehicles[length++] = newItem;
    }

    public void RemoveItem(int index) {
            for(int i=index;i<length;i++){
                listOfVehicles[i] = listOfVehicles[i+1];
            }
            length--;
    }
}
