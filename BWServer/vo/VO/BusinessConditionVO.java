package VO;

import java.io.Serializable;

import export.Exportable;
import export.annotation.ExportField;

public class BusinessConditionVO implements Serializable, Exportable<BusinessConditionVO> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ExportField(name = "折讓後總收", nameRow = 0, nameCol = 0)
	private double incomeAfterdiscount;// 折让后总收入

	@ExportField(name = "總折讓", nameRow = 0, nameCol = 1)
	private double SumOfDiscount;// 总折让

	@ExportField(name = "銷售收入", nameRow = 0, nameCol = 2)
	private double saleIncome; // 销售收入

	@ExportField(name = "商品報溢收入", nameRow = 0, nameCol = 3)
	private double commidityOverFlowIncome; // 商品报溢收入

	@ExportField(name = "成本調價收入", nameRow = 0, nameCol = 4)
	private double commidityCostChangeIncome; // 成本调价收入

	@ExportField(name = "進貨退貨差價收入", nameRow = 0, nameCol = 5)
	private double buySaleDifferenceIncome; // 进货退货差价收入: 賣50元, 退的時候只退30元

	@ExportField(name = "代金卷與際收差額收入", nameRow = 0, nameCol = 6)
	private double voucherActualDifferenceIncome; // 代金卷与实际收款差额收入:代金卷不找兌所賺的收入

	@ExportField(name = "總支出", nameRow = 0, nameCol = 0)
	private double sumOfCost;// 总支出

	@ExportField(name = "銷售成本", nameRow = 0, nameCol = 0)
	private double saleCost; // 销售成本;

	@ExportField(name = "商品報溢", nameRow = 0, nameCol = 0)
	private double commidityLostCost; // 商品报溢

	@ExportField(name = "商品贈出", nameRow = 0, nameCol = 0)
	private double commidityGiftCost; // 商品赠出

	public BusinessConditionVO(double saleIncome, double commidityOverFlowIncome, double commidityCostChangeIncome,
			double buySaleDifferenceIncome, double voucherActualDifferenceIncome, double saleCost,
			double commidityLostCost, double commidityGiftCost) {
		this.saleIncome = saleIncome;
		this.commidityOverFlowIncome = commidityOverFlowIncome;
		this.commidityCostChangeIncome = commidityCostChangeIncome;
		this.buySaleDifferenceIncome = buySaleDifferenceIncome;
		this.voucherActualDifferenceIncome = voucherActualDifferenceIncome;
		this.saleCost = saleCost;
		this.commidityLostCost = commidityCostChangeIncome;
		this.commidityGiftCost = commidityGiftCost;
	}

	public BusinessConditionVO(double incomeAfterdiscount, double sumOfDiscount, double saleIncome,
			double commidityOverFlowIncome, double commidityCostChangeIncome, double buySaleDifferenceIncome,
			double voucherActualDifferenceIncome, double saleCost, double commidityLostCost, double commidityGiftCost,
			double sumOfCost) {
		super();
		this.incomeAfterdiscount = incomeAfterdiscount;
		SumOfDiscount = sumOfDiscount;
		this.saleIncome = saleIncome;
		this.commidityOverFlowIncome = commidityOverFlowIncome;
		this.commidityCostChangeIncome = commidityCostChangeIncome;
		this.buySaleDifferenceIncome = buySaleDifferenceIncome;
		this.voucherActualDifferenceIncome = voucherActualDifferenceIncome;
		this.saleCost = saleCost;
		this.commidityLostCost = commidityLostCost;
		this.commidityGiftCost = commidityGiftCost;
		this.sumOfCost = sumOfCost;
	}

	public double getSumOfCost() {
		return sumOfCost;
	}

	public void setSumOfCost(double sumOfCost) {
		this.sumOfCost = sumOfCost;
	}

	public BusinessConditionVO() {

	}

	public double getIncomeAfterdiscount() {
		return incomeAfterdiscount;
	}

	public double getSumOfDiscount() {
		return SumOfDiscount;
	}

	public void setIncomeAfterdiscount(double incomeAfterdiscount) {
		this.incomeAfterdiscount = incomeAfterdiscount;
	}

	public void setSumOfDiscount(double sumOfDiscount) {
		SumOfDiscount = sumOfDiscount;
	}

	public double getBuySaleDifferenceIncome() {
		return buySaleDifferenceIncome;
	}

	public double getCommidityCostChangeIncome() {
		return commidityCostChangeIncome;
	}

	public double getCommidityGiftCost() {
		return commidityGiftCost;
	}

	public double getCommidityLostCost() {
		return commidityLostCost;
	}

	public double getCommidityOverFlowIncome() {
		return commidityOverFlowIncome;
	}

	public double getSaleCost() {
		return saleCost;
	}

	public double getSaleIncome() {
		return saleIncome;
	}

	public double getVoucherActualDifferenceIncome() {
		return voucherActualDifferenceIncome;
	}

	public void setBuySaleDifferenceIncome(double buySaleDifferenceIncome) {
		this.buySaleDifferenceIncome = buySaleDifferenceIncome;
	}

	public void setCommidityCostChangeIncome(double commidityCostChangeIncome) {
		this.commidityCostChangeIncome = commidityCostChangeIncome;
	}

	public void setCommidityGiftCost(double commidityGiftCost) {
		this.commidityGiftCost = commidityGiftCost;
	}

	public void setCommidityLostCost(double commidityLostCost) {
		this.commidityLostCost = commidityLostCost;
	}

	public void setCommidityOverFlowIncome(double commidityOverFlowIncome) {
		this.commidityOverFlowIncome = commidityOverFlowIncome;
	}

	public void setSaleCost(double saleCost) {
		this.saleCost = saleCost;
	}

	public void setSaleIncome(double saleIncome) {
		this.saleIncome = saleIncome;
	}

	public void setVoucherActualDifferenceIncome(double voucherActualDifferenceIncome) {
		this.voucherActualDifferenceIncome = voucherActualDifferenceIncome;
	}

	@Override
	public String toString() {
		return "BusinessConditionPO [saleIncome=" + saleIncome + ", commidityOverFlowIncome=" + commidityOverFlowIncome
				+ ", commidityCostChangeIncome=" + commidityCostChangeIncome + ", buySaleDifferenceIncome="
				+ buySaleDifferenceIncome + ", voucherActualDifferenceIncome=" + voucherActualDifferenceIncome
				+ ", saleCost=" + saleCost + ", commidityLostCost=" + commidityLostCost + ", commidityGiftCost="
				+ commidityGiftCost + "]";
	}
}
