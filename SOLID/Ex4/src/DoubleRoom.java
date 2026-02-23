public class DoubleRoom implements RoomPricing {
    public int type() { return LegacyRoomTypes.DOUBLE; }
    public Money monthlyBase() { return new Money(15000.0); }
}