package com.example.Food_app.utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class utils {

    public static Map<String, String> getAuthoritiesForUsers(){
        HashMap<String, String> authoritiesMap = new HashMap<>();

        List<String> customerAuthorities = Arrays.asList("CUSTOMER_SELF_INFO","CUSTOMER_ORDER_AUTHORITY");
        List<String> adminAuthorities = Arrays.asList("CUSTOMER_INFO_BY_ADMIN","CREATE_ADMIN_AUTHORITY","ADMIN_ITEM_AUTHORITY","ADMIN_ORDER_AUTHORITY","ADMIN_RESTAURANT_AUTHORITY","ADMIN_BILL_AUTHORITY");

        String customerAuthoritiesAsString = String.join("::", customerAuthorities);
        String adminAuthoritiesAsString = String.join("::", adminAuthorities);
        authoritiesMap.put("CUSTOMER_USER", customerAuthoritiesAsString);
        authoritiesMap.put("ADMIN_USER", adminAuthoritiesAsString);
        return authoritiesMap;
    }

}
