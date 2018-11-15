package VO;

import java.io.Serializable;
import java.util.Date;

import PO.VoucherPO;

public class VoucherVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date endTime;
	private double faceValue;
	private Date startTime;

	public VoucherVO() {
		super();
	}

	public VoucherVO(Date endTime, double faceValue, Date startTime) {
		this.endTime = endTime;
		this.faceValue = faceValue;
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}


	public double getFaceValue() {
		return faceValue;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setFaceValue(double faceValue) {
		this.faceValue = faceValue;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public VoucherPO toPO() {		
		VoucherPO v=new VoucherPO(); 
		v.setFaceValue(faceValue);
		v.setStartTime(startTime);
		v.setEndTime(endTime); 
		return v;
	}
}
