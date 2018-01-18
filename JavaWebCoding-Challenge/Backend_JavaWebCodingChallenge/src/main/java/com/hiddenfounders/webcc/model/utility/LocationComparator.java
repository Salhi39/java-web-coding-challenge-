package com.hiddenfounders.webcc.model.utility;

import com.hiddenfounders.webcc.model.Shop;

import java.util.Comparator;

/**
 * @author Mohamed SALHI
 * @date Created on 1/18/18 - 1:20 AM
 * @package com.hiddenfounders.webcc.model.utility
 */


public class LocationComparator implements Comparator {

    Location userLocation;

    public LocationComparator(Location userLocation) {
        this.userLocation = userLocation;
    }


    @Override
    public int compare(Object o1, Object o2) {
        Shop shop1 = (Shop) o1;
        Shop shop2 = (Shop) o2;

        double[] coordinates1 = shop1.getLocation().getCoordinates();
        double[] coordinates2 = shop2.getLocation().getCoordinates();

        Double distance1 = distanceBetweenTwoLocationsInKm(
                userLocation.getCoordinates(),
                coordinates1);
        Double distance2 = distanceBetweenTwoLocationsInKm(
                userLocation.getCoordinates(),
                coordinates2);

        if(distance1 > distance2)
            return -1;
        if(distance1 < distance2)
            return 1;

        return 0;
    }



    public Location getUserLocation() {
        return userLocation;
    }

    public LocationComparator setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
        return this;
    }


    private static Double distanceBetweenTwoLocationsInKm(double[] coordinates1, double[] coordinates2) {
        if (coordinates1.length < 2 || coordinates2.length < 2)
            return null;

        Double earthRadius = 6371.0;
        Double diffBetweenLatitudeRadians = Math.toRadians(coordinates2[1] - coordinates1[1]);
        Double diffBetweenLongitudeRadians = Math.toRadians(coordinates1[0] - coordinates1[0]);
        Double latitudeOneInRadians = Math.toRadians(coordinates1[1]);
        Double latitudeTwoInRadians = Math.toRadians(coordinates2[1]);
        Double a = Math.sin(diffBetweenLatitudeRadians / 2) * Math.sin(diffBetweenLatitudeRadians / 2) + Math.cos(latitudeOneInRadians) * Math.cos(latitudeTwoInRadians) * Math.sin(diffBetweenLongitudeRadians / 2)
                * Math.sin(diffBetweenLongitudeRadians / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (earthRadius * c);
    }


}
