package passenger;

public class PremiumPassenger extends Passenger {
    private String name;
    private int passengerNumber;
    private String passengerType;
    private double balance;

    public PremiumPassenger(String name, int passengerNumber, double balance) {
        super(name,passengerNumber,balance);
    }

    @Override
    public boolean signUpForActivity(double cost) {
        return true;
    }

}
