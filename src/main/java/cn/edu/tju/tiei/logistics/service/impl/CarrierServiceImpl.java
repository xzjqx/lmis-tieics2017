package cn.edu.tju.tiei.logistics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tju.tiei.logistics.dao.CarrierMapper;
import cn.edu.tju.tiei.logistics.model.Carrier;
import cn.edu.tju.tiei.logistics.service.ICarrierService;

@Service("CarrierService")
public class CarrierServiceImpl implements ICarrierService {

	@Resource
	private CarrierMapper CarrierMapper;

	public CarrierMapper getCarrierMapper() {
		return CarrierMapper;
	}

	@Autowired
	public void setCarrierMapper(CarrierMapper CarrierMapper) {
		this.CarrierMapper = CarrierMapper;
	}

	public List<Carrier> findAll() {
		return CarrierMapper.selectByExample(null);
	}

	public Carrier findById(String id) {
		return CarrierMapper.selectByPrimaryKey(id);
	}

	public void create(Carrier carrier) {
		CarrierMapper.insert(carrier);
	}

	public boolean isExist(Carrier carrier) {
		return CarrierMapper.selectByPrimaryKey(carrier.getId()) != null;
	}

	public void update(Carrier carrier) {
		CarrierMapper.updateByPrimaryKey(carrier);
	}

	public void deleteById(String id) {
		CarrierMapper.deleteByPrimaryKey(id);
	}

	public void deleteAll() {
		CarrierMapper.deleteByExample(null);
	}

}
