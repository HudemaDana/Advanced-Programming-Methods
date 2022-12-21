public class Dog extends Animal {
    public Dog(){}
    @Override
    public void Sound()
    {
        System.out.println("Ham Ham");
    }
    public static void VerifyAge(int age) throws AnimalException {
        if(getAverageAge()!=age){
            throw new AnimalException("Invalid age");
        }
        else{
            System.out.print("Good age");
        }
    }
}
