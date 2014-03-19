package com.iwakfu.model;

public class ItemStat {
	private String cate;
	private String stat_type;
	private String content;
	private int value;
	private boolean percent;
	private boolean air;
	private boolean water;
	private boolean earth;
	private boolean fire;
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getStat_type() {
		return stat_type;
	}
	public void setStat_type(String stat_type) {
		this.stat_type = stat_type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isPercent() {
		return percent;
	}
	public void setPercent(boolean percent) {
		this.percent = percent;
	}
	public boolean isAir() {
		return air;
	}
	public void setAir(boolean air) {
		this.air = air;
	}
	public boolean isWater() {
		return water;
	}
	public void setWater(boolean water) {
		this.water = water;
	}
	public boolean isEarth() {
		return earth;
	}
	public void setEarth(boolean earth) {
		this.earth = earth;
	}
	public boolean isFire() {
		return fire;
	}
	public void setFire(boolean fire) {
		this.fire = fire;
	}
	
}
