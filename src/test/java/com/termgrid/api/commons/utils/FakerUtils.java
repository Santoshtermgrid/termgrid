package com.termgrid.api.commons.utils;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.UUID;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        UUID uuid = UUID.randomUUID();
        return faker.name().firstName();
    }
    public static Faker Faker(){
        Faker faker = new Faker();
        UUID uuid = UUID.randomUUID();
        return faker;
    }
    public static String generateLastName(){
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return "Description " + faker.regexify("[ A-Za-z0-9_@./#&+-]{50}");
    }
    public static String generatePhoneNumber(){
        Faker faker = new Faker();
        UUID uuid = UUID.randomUUID();
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateEmail(){
        Faker faker = new Faker();
        UUID uuid = UUID.randomUUID();
        return faker.name().firstName()+uuid+"@termgrid.com";
    }
    public static String generateUUID(){
        Faker faker = new Faker();
        UUID uuid = UUID.randomUUID();
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTimeInMillis());
        return uuid.toString();
    }
}
