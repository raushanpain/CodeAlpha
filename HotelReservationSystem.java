import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }
}

class Booking {
    String guestName;
    Room room;
    boolean isPaid;

    public Booking(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
        this.isPaid = false;
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeRooms();

        while (true) {
            System.out.println("\n1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Process Payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    processPayment(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Deluxe", 150.00));
        rooms.add(new Room(102, "Suite", 300.00));
        rooms.add(new Room(103, "Standard", 100.00));
    }

    static void viewAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room " + room.roomNumber + " (" + room.category + ") - $" + room.price);
            }
        }
    }

    static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                room.isAvailable = false;
                Booking booking = new Booking(guestName, room);
                bookings.add(booking);
                System.out.println("Reservation successful for " + guestName);
                return;
            }
        }
        System.out.println("Room not available or does not exist.");
    }

    static void viewBookings() {
        System.out.println("Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking.guestName + " - Room " + booking.room.roomNumber + " (" + booking.room.category + ") - Paid: " + (booking.isPaid ? "Yes" : "No"));
        }
    }

    static void processPayment(Scanner scanner) {
        System.out.print("Enter your name: ");
        String guestName = scanner.nextLine();
        
        for (Booking booking : bookings) {
            if (booking.guestName.equalsIgnoreCase(guestName) && !booking.isPaid) {
                booking.isPaid = true;
                System.out.println("Payment processed for " + guestName);
                return;
            }
        }
        System.out.println("No pending payments found for this guest.");
    }
}
