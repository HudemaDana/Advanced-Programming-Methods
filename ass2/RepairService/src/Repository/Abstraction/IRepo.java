package Repository.Abstraction;

import Model.Abstraction.Item;

public interface IRepo {
    public Item[] GetItems();
    public int getSize();
    public void AddItem(Item newItem);
    public void RemoveItem(int index);



}
