import java.util.concurrent.*;

class TicketBookingSystem {
    private final boolean[] seats = new boolean[10]; // 10 seats
    private final Object lock = new Object();

    public void bookSeat(String passenger, int seatNumber) {
        synchronized (lock) {
            if (seatNumber < 0 || seatNumber >= seats.length) {
                System.out.println(passenger + " - Invalid seat number!");
                return;
            }
            if (seats[seatNumber]) {
                System.out.println(passenger + " - Seat " + seatNumber + " already booked!");
            } else {
                seats[seatNumber] = true;
                System.out.println(passenger + " successfully booked seat " + seatNumber);
            }
        }
    }
}

class Passenger extends Thread {
    private final TicketBookingSystem system;
    private final String name;
    private final int seatNumber;

    public Passenger(TicketBookingSystem system, String name, int seatNumber, int priority) {
        this.system = system;
        this.name = name;
        this.seatNumber = seatNumber;
        setPriority(priority); // VIP passengers get high priority
    }

    @Override
    public void run() {
        system.bookSeat(name, seatNumber);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Passenger vip1 = new Passenger(system, "VIP1", 2, Thread.MAX_PRIORITY);
        Passenger vip2 = new Passenger(system, "VIP2", 3, Thread.MAX_PRIORITY);
        Passenger user1 = new Passenger(system, "User1", 2, Thread.NORM_PRIORITY);
        Passenger user2 = new Passenger(system, "User2", 4, Thread.NORM_PRIORITY);
        Passenger user3 = new Passenger(system, "User3", 3, Thread.MIN_PRIORITY);

        executor.execute(vip1);
        executor.execute(vip2);
        executor.execute(user1);
        executor.execute(user2);
        executor.execute(user3);

        executor.shutdown();
    }
}
