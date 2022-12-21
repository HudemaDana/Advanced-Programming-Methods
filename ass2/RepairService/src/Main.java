import Controller.RepairServiceController;
import Model.Abstraction.Item;
import Model.Car;
import Model.Motocycle;
import Model.Bicycle;
import Repository.Repo;
import View.RepairServiceView;

public class Main {
    public static void main(String[] args){

        Item a = new Car("red");
        Item b = new Bicycle("blue");
        Item c = new Motocycle("roz bonbon");

        Repo repository = new Repo();
        RepairServiceController controller = new RepairServiceController(repository);
        RepairServiceView view = new RepairServiceView(controller);

        repository.AddItem(a);
        repository.AddItem(b);
        repository.AddItem(c);

        view.run();
    }
}