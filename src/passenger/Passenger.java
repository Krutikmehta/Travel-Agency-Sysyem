package passenger;

public abstract  class Passenger{
    private String name;
    private int passengerNumber;
    private double balance;

    public Passenger(String name, int passengerNumber, double balance) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }


    public double getBalance() {
        return balance;
    }

    public abstract boolean  signUpForActivity(double cost) ;

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
