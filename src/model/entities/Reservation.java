package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    public Reservation(Integer roomNumber, Date checkin, Date checkout) {
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

    public String updateDates(Date checkin, Date checkout) {

        Date now = new Date();
        if (checkin.before(now) || checkout.before(now))
            return "Reservation dates for update must be future dates";

        if (!checkin.before(checkout))
            return "Check-out date must be after check-in date";

        this.checkin = checkin;
        this.checkout = checkout;
        return null;
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
