
public class PaymentProfile {
	
	public static String getInvoice() {
		return invoice;
	}

	public static void setInvoice(String invoice) {
		PaymentProfile.invoice = invoice;
	}

	public static double getPreviousBalance() {
		return previousBalance;
	}

	public static void setPreviousBalance(double previousBalance) {
		PaymentProfile.previousBalance = previousBalance;
	}

	public static double getTotalBalance() {
		return totalBalance;
	}

	public static void setTotalBalance(double totalBalance) {
		PaymentProfile.totalBalance = totalBalance;
	}

	private static String invoice = "";
	private static double previousBalance, totalBalance;

	PaymentProfile() {

	}
	
}