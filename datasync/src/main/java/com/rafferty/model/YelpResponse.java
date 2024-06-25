package com.rafferty.model;

import java.util.List;

public class YelpResponse {
    private List<Restaurant> businesses;
    public List<Restaurant> getBusinesses() {
        return businesses;
    }
    public void setBusinesses(List<Restaurant> businesses) {
        this.businesses = businesses;
    }

}
