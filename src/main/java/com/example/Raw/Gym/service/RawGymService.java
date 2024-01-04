package com.example.Raw.Gym.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Raw.Gym.model.RawGym;
import com.example.Raw.Gym.repo.IRawGymRepo;

@Service
public class RawGymService {
	
	@Autowired
	IRawGymRepo repo;
	public List<RawGym> getAllRawGymItems() {
		ArrayList<RawGym> rawgymList = new ArrayList<>();
		repo.findAll(Sort.by(Sort.Direction.DESC, "Coins")).forEach(rawgym -> rawgymList.add(rawgym));
		
		return rawgymList;
	}
	
	public RawGym getRawGymItemById(Long id) {
		return repo.findById(id).get();
	}
	
	public boolean updateRawGymCoins(Long id) {
		RawGym rawgym = getRawGymItemById(id);
		rawgym.setCoins(rawgym.getCoins() + (long) 10);
		rawgym.setDate(new Date());
		
		return saveOrUpdateRawGymItem(rawgym);
	}
	
	public boolean saveOrUpdateRawGymItem(RawGym rawgym) {
		RawGym updatedObj = repo.save(rawgym);
		
		if (getRawGymItemById(updatedObj.getId()) != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean deleteRawGymItem(Long id) {
		repo.deleteById(id);
		
		if (repo.findById(id).isEmpty()) {
			return true;
		}
		
		return false;
	}
}
