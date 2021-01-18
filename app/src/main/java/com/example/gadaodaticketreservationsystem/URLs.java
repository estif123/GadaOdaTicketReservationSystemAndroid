package com.example.gadaodaticketreservationsystem;

public class URLs {
    public static final String URL_SCHEDULE = "http://10.140.11.145/GedaOdaTicketReservationSystem/Customer/schedule.php";
//    public static final String URL_RESERVATION = "http://10.140.11.145/GedaOdaTicketReservationSystem/Customer/reservation.php";
    private static final String ROOT_URL = "http://10.140.11.145/GedaOdaTicketReservationSystem/Customer/register.php?apicall=";
    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN= ROOT_URL + "login";
    public static final String URL_RESERVATION= ROOT_URL + "reservation";
    public static final String URL_CANCELL_RESERVATION= ROOT_URL + "cancell_reservation";
    public static final String URL_DELETE_RESERVATION= ROOT_URL + "delete_reservation";

}