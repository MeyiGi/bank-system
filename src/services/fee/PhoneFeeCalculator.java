package services.fee;

public class PhoneFeeCalculator extends FeeCalculator {
    private final double percent;

    /**
     * @param percent процент от суммы (например, 1.5 для 1.5%)
     */
    public PhoneFeeCalculator(double percent) {
        this.percent = percent;
    }

    @Override
    public double calculateFee(int amount) {
        return amount * percent / 100.0;
    }
}