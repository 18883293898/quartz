/**
 *
 */
package com.hiynn.project.redis.entity;

import java.io.Serializable;

public class BaseRedisObject implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *
	 */
	private String pkProperty = "";
    /**
	 * SortedSet排序用的属性
	 */
	private double score = 0.0d;
	@Override
	public int hashCode(){
		return this.pkProperty.hashCode();
	}
	public String getPkProperty() {
		return pkProperty;
	}
	public void setPkProperty(String pkProperty) {
		this.pkProperty = pkProperty;
	}
	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

}
