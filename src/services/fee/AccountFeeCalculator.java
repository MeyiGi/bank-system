package services.fee;

public class AccountFeeCalculator extends FeeCalculator {
    private final double fixedFee;

    /**
     * @param fixedFee фиксированная сумма комиссии
     */
    public AccountFeeCalculator(double fixedFee) {
        this.fixedFee = fixedFee;
    }

    @Override
    public double calculateFee(int amount) {
        return fixedFee;
    }
}