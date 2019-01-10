package model.entities;

import model.entities.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    public Reservation(Integer roomNumber, Date checkin, Date checkout) throws DomainException {

        if (!checkout.after(checkin))
            throw new DomainException("Error in reservation: Check-out date must be after check-in date");

        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return String.format("Room: %d, check-in: %s, check-out: %s, nights: %d",
                this.roomNumber,
                sdf.format(this.checkin),
                sdf.format(this.checkout),
                this.duration());
    }

    public Long duration() {

        long diff = this.checkout.getTime() - this.checkin.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkin, Date checkout) throws DomainException {

        Date now = new Date();
        if (checkin.before(now) || checkout.before(now))
            throw new DomainException("Reservation dates for update must be future");
        if (!checkin.before(checkout))
            throw new DomainException("Check-out date must be after check-in date");

        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }
}
