public class Animal {
        private static int averageAge=80;

        public Animal(){}

        public static int getAverageAge(){
            return averageAge;
        }

        public static void setAverageAge(int newAge){
            averageAge = newAge;
        }

        public void Sound(){
            System.out.println("Any sound an animal could make");
        };
}
