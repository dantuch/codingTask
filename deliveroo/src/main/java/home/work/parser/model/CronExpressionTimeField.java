package home.work.parser.model;

public enum CronExpressionTimeField {

    MINUTE(0, 59),
    HOUR(0,23),
    DAY_OF_MONTH(1,31),
    MONTH(1,12),
    DAY_OF_WEEK(1,7);

    CronExpressionTimeField(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    private int minValue;
    private int maxValue;

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getName() {
        return name().toLowerCase().replace('_', ' ');
    }

}
