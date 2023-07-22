package passenger;

public class GoldPassenger extends Passenger {

    public GoldPassenger(String name, int passengerNumber, double balance) {
        super(name,passengerNumber,balance);
    }

    @Override
    public boolean signUpForActivity(double cost) {
        if(getBalance() >= cost*0.9){
            setBalance(getBalance() - cost*0.9);
            return true;
        }
        return false;
    }

}
