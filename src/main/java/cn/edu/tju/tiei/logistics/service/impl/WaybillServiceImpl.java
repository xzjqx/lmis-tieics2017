package cn.edu.tju.tiei.logistics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tju.tiei.logistics.dao.WaybillMapper;
import cn.edu.tju.tiei.logistics.model.Waybill;
import cn.edu.tju.tiei.logistics.service.IWaybillService;

@Service("WaybillService")
public class WaybillServiceImpl implements IWaybillService {

	@Resource
	private WaybillMapper WaybillMapper;

	public WaybillMapper getWaybillMapper() {
		return WaybillMapper;
	}

	@Autowired
	public void setWaybillMapper(WaybillMapper WaybillMapper) {
		this.WaybillMapper = WaybillMapper;
	}

	public List<Waybill> findAll() {
		return WaybillMapper.selectByExample(null);
	}

	public Waybill findById(String id) {
		return WaybillMapper.selectByPrimaryKey(id);
	}

	public void create(Waybill waybill) {
		WaybillMapper.insert(waybill);
	}

	public boolean isExist(Waybill waybill) {
		return WaybillMapper.selectByPrimaryKey(waybill.getId()) != null;
	}

	public void update(Waybill waybill) {
		WaybillMapper.updateByPrimaryKey(waybill);
	}

	public void deleteById(String id) {
		WaybillMapper.deleteByPrimaryKey(id);
	}

	public void deleteAll() {
		WaybillMapper.deleteByExample(null);
	}

}
