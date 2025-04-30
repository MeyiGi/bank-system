package services.fee;

public class InnFeeCalculator extends FeeCalculator {
    private final int minFee;

    /**
     * @param minFee минимальная сумма комиссии
     */
    public InnFeeCalculator(int minFee) {
        this.minFee = minFee;
    }

    @Override
    public double calculateFee(int amount) {
        return Math.min(minFee, amount);
    }
}