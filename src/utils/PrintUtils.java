package utils;

import travel.TravelPackage;

public interface PrintUtils {
    void printItinery(TravelPackage travelPackage);
    void printPassengers(TravelPackage travelPackage);
    void printPassengerDetails(TravelPackage travelPackage,Integer passengerNUmmber);
    void printUnfilledActivites(TravelPackage travelPackage);
}
