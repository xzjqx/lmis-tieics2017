package cn.edu.tju.tiei.logistics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tju.tiei.logistics.dao.ShipperMapper;
import cn.edu.tju.tiei.logistics.model.Shipper;
import cn.edu.tju.tiei.logistics.service.IShipperService;

@Service("ShipperService")
public class ShipperServiceImpl implements IShipperService {

	@Resource
	private ShipperMapper ShipperMapper;

	public ShipperMapper getShipperMapper() {
		return ShipperMapper;
	}

	@Autowired
	public void setShipperMapper(ShipperMapper ShipperMapper) {
		this.ShipperMapper = ShipperMapper;
	}

	public List<Shipper> findAll() {
		return ShipperMapper.selectByExample(null);
	}

	public Shipper findById(String id) {
		return ShipperMapper.selectByPrimaryKey(id);
	}

	public void create(Shipper shipper) {
		ShipperMapper.insert(shipper);
	}

	public boolean isExist(Shipper shipper) {
		return ShipperMapper.selectByPrimaryKey(shipper.getId()) != null;
	}

	public void update(Shipper shipper) {
		ShipperMapper.updateByPrimaryKey(shipper);
	}

	public void deleteById(String id) {
		ShipperMapper.deleteByPrimaryKey(id);
	}

	public void deleteAll() {
		ShipperMapper.deleteByExample(null);
	}

}
