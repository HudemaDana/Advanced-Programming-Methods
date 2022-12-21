import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Animal a = new Dog();
        Animal c = new Animal();

        try {
            a.Sound();
            c.Sound();
            ((Dog) a).VerifyAge(30);
            ((Dog) a).VerifyAge(80);

        }
        catch(AnimalException e){
            System.err.println(e);
        }


        /*Scanner in = new Scanner(System.in);

        float sum=0;
        int nr=0;

        while(true){
            try{
                String s = in.nextLine();
                sum+=Integer.parseInt(s);
                nr++;
            }
            catch(Exception e) {
                break;
            }
        }

        System.out.print("Average is: "+ sum/nr);*/
    }
}