package de.ing.creditapplication.scoring.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditApplication implements Serializable {
	/*
	 * wandelt das Object in einen Bytestrom
	 */


	private static final long serialVersionUID = 8144682724082102640L;
	
	private String creditApplicationId;
	private double monthlyIncome;
	private double monthlyExpenditure;
	private double creditSum;

	public boolean scoringProcess() {
		return creditSum < (monthlyIncome-monthlyExpenditure) / 10.0;
	}
	

}
