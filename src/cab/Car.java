import java.util.*;

class Car { private String make; private String model; private String regNo; private int deposit; private int rate;

    public Car(String newMake, String newModel, String newRegNo,
                    int newDeposit, int newRate) {
            make = newMake;
            model = newModel;
            regNo = newRegNo;
            deposit = newDeposit;
            rate = newRate;
    }

    public String getMake() {
            return make;
    }

    public String getModel() {
            return model;
    }

    public String getRegNo() {
            return regNo;
    }

    public int getDeposit() {
            return deposit;
    }

    public int getRate() {
            return rate;
    }


 
    public static void main(String args[]) {
    ArrayList<Car> carlist = new ArrayList<Car>();
    carlist.add(new Car("Toyota", "Altis", "SJC2456X", 100, 60));
    carlist.add(new Car("Toyota", "Vios", "SJG9523B", 100, 50));
    carlist.add(new Car("Nissan", "Latio", "SJB7412B", 100, 50));
    carlist.add(new Car("Murano", "SJC8761M", "Nissan", 300, 150));

    carlist.add(new Car("Honda", "Jazz", "SJB4875N", 100, 60)); 
    carlist.add(new Car("Honda", "Civic", "SJD73269C", 120, 70)); 
    carlist.add(new Car("Honda", "Stream", "SJL5169J", 120, 70)); 
    carlist.add(new Car("Honda", "Odyssey", "SJB3468E", 200, 150)); 
    carlist.add(new Car("Subaru", "WRX", "SJB8234L", 300, 200));
    carlist.add(new Car("Subaru", "Imprezza", "SJE8234K", 150, 80));
    Scanner input = new Scanner(System.in);
    System.out.print("Enter model to rent: ");
    String model = input.nextLine();
    for(Car s : carlist){
        if (model.equals(s.getModel())) {
            System.out.println("Model " + model + " is available");
            System.out.print("Enter number of days: ");
            int days = input.nextInt();
            System.out.println("****Details****");
            int cost = (days * s.getRate()) + s.getDeposit();
            System.out.println("Deposit DailyRate Duration TotalCost"); 
            System.out.println(s.getDeposit() + " " + s.getRate()+ " " + days + " " + cost);
            System.out.print("Proceed to rent?( y/n ): ");
            String dec = input.next();
            if (dec.equals("y")) {
                System.out.println("Enter Customer Name: "); 
                String name = input.next(); 
                System.out.println("Enter IC Number: ");
                int num = input.nextInt();
                System.out.println("***Receipt****");
                System.out.println("Name ICNo Car RegNo Duration TCost");
                System.out.println(name + " " + num + " " + model + " " + s.getRegNo() + " " + days + " "+cost);
                System.out.println("Serving Next Customer ");
                } 
                else if (dec.equals("n")) {
                    System.out.println("Serving Next Customer: ");
                    } 
                    }
                    }
                    } 
}  