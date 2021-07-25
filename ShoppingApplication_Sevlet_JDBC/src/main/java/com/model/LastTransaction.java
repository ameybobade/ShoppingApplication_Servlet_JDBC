package com.model;

public class LastTransaction {
        private int CustId;
        private int BillNo;
        private double Amount;
		public LastTransaction(int custId, int billNo, double amount) {
			super();
			CustId = custId;
			BillNo = billNo;
			Amount = amount;
		}
		
		public int getCustId() {
			return CustId;
		}
		public void setCustId(int custId) {
			CustId = custId;
		}
		public int getBillNo() {
			return BillNo;
		}
		public void setBillNo(int billNo) {
			BillNo = billNo;
		}
		public double getAmount() {
			return Amount;
		}
		public void setAmount(double amount) {
			Amount = amount;
		}
        
}
