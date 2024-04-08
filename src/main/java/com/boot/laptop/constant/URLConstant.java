package com.boot.laptop.constant;

public class URLConstant {

    public static final String ADD_LAPTOP_INTO_STORE = "/add-laptop";
    public static final String LAPTOP_COLLECTION = "/laptop-collection";
    public static final String LAPTOP_BY_LAPTOP_ID = LAPTOP_COLLECTION + "/{laptop_id}";
    public static final String LAPTOP_STORE = "/laptop-store";

    public static final String ADD_USER = "/add-user";
    public static final String USER = "/user";

    public static final String GET_ALL_USERS = "/user-collection";

    public static final String USER_BY_USER_ID = GET_ALL_USERS + "/{user_id}";

    public static final String LAPTOP_BY_LAPTOP_NAME = LAPTOP_COLLECTION + "/by-name" + "/{laptop-name}";


}
