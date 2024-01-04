package com.example.Raw.Gym.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name="rawgym")
public class RawGym {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Nonnull
	private Long id;
	
	@Column
	@Nonnull
	private String name;
	
	@Column
	@Nonnull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column
	@Nonnull
	private Long coins;
	
	public RawGym() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getCoins() {
		return coins;
	}

	public void setCoins(Long coins) {
		this.coins = coins;
	}
	
}
