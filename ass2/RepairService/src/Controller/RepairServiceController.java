package Controller;

import Exceptions.FilterByRepairCostException;
import Exceptions.IndexException;
import Exceptions.RepairCostException;
import Model.Abstraction.Item;
import Repository.Abstraction.IRepo;

public class RepairServiceController {
    private IRepo _repo;

    public RepairServiceController(IRepo repo){
        _repo = repo;
    }

    public Item[] getAllItems(){
        return _repo.GetItems();
    }

    public int getSize(){
        return _repo.getSize();
    }
    public void addItem(Item newItem) throws RepairCostException {
        if(newItem.getColor()==null){
            throw new RepairCostException("Invalid price for your vehicle");
        }
        else{
            _repo.AddItem(newItem);
        }
    }

    public void removeItem(int index) throws IndexException {
        if(index > _repo.getSize() || index < 0){
            throw new IndexException("Invalid index");
        }
        else{
            _repo.RemoveItem(index);
        }
    }

    public Item[] filterByColor() throws FilterByRepairCostException {
        Item[] newArray = new Item[10];
        int newLength = 0;

        for(Item i: _repo.GetItems()){
            if(i==null){
                break;
            }
            else{
                if(i.getColor().toLowerCase().equals("red"))
                {
                    newArray[newLength++] = i;
                }
            }
        }

        if(newLength != 0){
            return newArray;
        }
        else{
            throw new FilterByRepairCostException("No item with repair cost greater than 1000 found");
        }
    }
}
