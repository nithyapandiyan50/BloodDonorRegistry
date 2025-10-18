package com.registry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Donor {
    private static AtomicInteger counter = new AtomicInteger(1000);

    private int id;
    private String name;
    private int age;
    private String bloodGroup;
    private String location;
    private String hospital;
    private String doctor;
    private String contactNumber;
    private String donationDate;

    // Constructor for new donor
    public Donor(String name, int age, String bloodGroup, String location,
                 String hospital, String doctor, String contactNumber) {
        this.id = counter.getAndIncrement();
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.location = location;
        this.hospital = hospital;
        if (!doctor.toLowerCase().startsWith("dr.")) {
            this.doctor = "Dr. " + doctor;
        } else {
            this.doctor = doctor;
        }
        this.contactNumber = contactNumber;
        this.donationDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    // Constructor for CSV import
    public Donor(int id, String donationDate, String name, int age, String bloodGroup,
                 String location, String hospital, String doctor, String contactNumber) {
        this.id = id;
        this.donationDate = donationDate;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.location = location;
        this.hospital = hospital;
        if (!doctor.toLowerCase().startsWith("dr.")) {
            this.doctor = "Dr. " + doctor;
        } else {
            this.doctor = doctor;
        }
        this.contactNumber = contactNumber;
        counter.updateAndGet(x -> Math.max(x, id + 1));
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getBloodGroup() { return bloodGroup; }
    public String getLocation() { return location; }
    public String getHospital() { return hospital; }
    public String getDoctor() { return doctor; }
    public String getContactNumber() { return contactNumber; }
    public String getDonationDate() { return donationDate; }

    public void setHospital(String hospital) { this.hospital = hospital; }
    public void setDoctor(String doctor) {
        if (!doctor.toLowerCase().startsWith("dr.")) this.doctor = "Dr. " + doctor;
        else this.doctor = doctor;
    }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    @Override
    public String toString() {
        return id + "," + donationDate + "," + name + "," + age + "," + bloodGroup + "," +
                location + "," + hospital + "," + doctor + "," + contactNumber;
    }

    public String display() {
        return id + " | " + donationDate + " | " + name + " | " + age + " | " + bloodGroup +
                " | " + location + " | " + hospital + " | " + doctor + " | " + contactNumber;
    }
}