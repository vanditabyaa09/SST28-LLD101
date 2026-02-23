public class DeluxeRoom implements RoomPricing {
    public int type() { return LegacyRoomTypes.DELUXE; }
    public Money monthlyBase() { return new Money(16000.0); }
}