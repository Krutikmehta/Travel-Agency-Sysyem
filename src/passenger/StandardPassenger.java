package passenger;

public class StandardPassenger extends Passenger {

    public StandardPassenger(String name, int passengerNumber, double balance) {
        super(name,passengerNumber,balance);
    }

    @Override
    public boolean signUpForActivity(double cost) {
        if(getBalance() >= cost){
            setBalance(getBalance() - cost);
            return true;
        }
        return false;
    }

}
