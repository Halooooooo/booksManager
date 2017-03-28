package Bean.entity;

public class ManagerHomeInfo {
	private double totalIncome;// 总收入
	private double expressIncome;// 快递收入
	private int fineIncome;// 罚款 收入
	private double topUpIncome;// 充值收入
	private int userNumber;// 用户数量
	private int VIPNumber;// VIP用户数量
	private int notReturnBooks;// 未归还书数目
	private int outReturnBooks;// 超时书目
	private int totalBooks;// 图书馆总书
	private int totalApprove;// 审核条数
	public double getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}
	public double getExpressIncome() {
		return expressIncome;
	}
	public void setExpressIncome(double expressIncome) {
		this.expressIncome = expressIncome;
	}
	public int getFineIncome() {
		return fineIncome;
	}
	public void setFineIncome(int fineIncome) {
		this.fineIncome = fineIncome;
	}
	public double getTopUpIncome() {
		return topUpIncome;
	}
	public void setTopUpIncome(double topUpIncome) {
		this.topUpIncome = topUpIncome;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getVIPNumber() {
		return VIPNumber;
	}
	public void setVIPNumber(int vIPNumber) {
		VIPNumber = vIPNumber;
	}
	public int getNotReturnBooks() {
		return notReturnBooks;
	}
	public void setNotReturnBooks(int notReturnBooks) {
		this.notReturnBooks = notReturnBooks;
	}
	public int getOutReturnBooks() {
		return outReturnBooks;
	}
	public void setOutReturnBooks(int outReturnBooks) {
		this.outReturnBooks = outReturnBooks;
	}
	public int getTotalBooks() {
		return totalBooks;
	}
	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}
	public int getTotalApprove() {
		return totalApprove;
	}
	public void setTotalApprove(int totalApprove) {
		this.totalApprove = totalApprove;
	}
	@Override
	public String toString() {
		return "ManagerHomeInfo [totalIncome=" + totalIncome
				+ ", expressIncome=" + expressIncome + ", fineIncome="
				+ fineIncome + ", topUpIncome=" + topUpIncome + ", userNumber="
				+ userNumber + ", VIPNumber=" + VIPNumber + ", notReturnBooks="
				+ notReturnBooks + ", outReturnBooks=" + outReturnBooks
				+ ", totalBooks=" + totalBooks + ", totalApprove="
				+ totalApprove + "]";
	}

	
	
}
