public class SingleRoom implements RoomPricing {
    public int type() { return LegacyRoomTypes.SINGLE; }
    public Money monthlyBase() { return new Money(14000.0); }
}