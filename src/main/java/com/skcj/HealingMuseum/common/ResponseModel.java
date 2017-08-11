package com.skcj.HealingMuseum.common;

public class ResponseModel {
	private String TrxCd;
	private String resCd = "0000";
	private String resMsg = "OK";
	private String lastAppver;
	private Object result;
	
	public String getTrxCd() {
		return TrxCd;
	}
	public void setTrxCd(String trxCd) {
		TrxCd = trxCd;
	}

	public String getResCd() {
		return resCd;
	}
	public void setResCd(String resCd) {
		this.resCd = resCd;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getLastAppver() {
		return lastAppver;
	}
	public void setLastAppver(String lastAppver) {
		this.lastAppver = lastAppver;
	}
}