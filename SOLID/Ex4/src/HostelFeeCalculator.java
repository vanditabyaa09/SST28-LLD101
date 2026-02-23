import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final Map<Integer, RoomPricing> roomPricing = new HashMap<>();
    private final Map<AddOn, AddOnPrice> addOnPricing = new HashMap<>();

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;

        List<RoomPricing> rooms = List.of(
                new SingleRoom(),
                new DoubleRoom(),
                new TripleRoom(),
                new DeluxeRoom()
        );
        for (RoomPricing r : rooms) roomPricing.put(r.type(), r);

        List<AddOnPrice> addons = List.of(
                new MessAddOnPricing(),
                new LaundryAddOnPricing(),
                new GymAddOnPricing()
        );
        for (AddOnPrice a : addons) addOnPricing.put(a.supports(), a);
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        RoomPricing room = roomPricing.get(req.roomType);
        Money total = room.monthlyBase();

        for (AddOn a : req.addOns) {
            AddOnPrice p = addOnPricing.get(a);
            if (p != null) total = total.plus(p.monthlyFee());
        }
        return total;
    }
}