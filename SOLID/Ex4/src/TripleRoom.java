public class TripleRoom implements RoomPricing {
    public int type() { return LegacyRoomTypes.TRIPLE; }
    public Money monthlyBase() { return new Money(12000.0); }
}