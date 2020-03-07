// The purpose of this inteface is to add on to our Validator class
// The methods in this interace will be used in the validator class
interface Acceptable {
    boolean isNonEmptyString(String x);
    boolean isPositiveInput(double y);
    boolean isPositiveInput(int z);
}
