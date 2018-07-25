package cn.edu.tju.tiei.logistics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tju.tiei.logistics.dao.BidMapper;
import cn.edu.tju.tiei.logistics.model.Bid;
import cn.edu.tju.tiei.logistics.service.IBidService;

@Service("BidService")
public class BidServiceImpl implements IBidService {

	@Resource
	private BidMapper BidMapper;

	public BidMapper getBidMapper() {
		return BidMapper;
	}

	@Autowired
	public void setBidMapper(BidMapper BidMapper) {
		this.BidMapper = BidMapper;
	}

	public List<Bid> findAll() {
		return BidMapper.selectByExample(null);
	}

	public Bid findById(Integer id) {
		return BidMapper.selectByPrimaryKey(id);
	}

	public void create(Bid bid) {
		BidMapper.insert(bid);
	}

	public boolean isExist(Bid bid) {
		return BidMapper.selectByPrimaryKey(bid.getId()) != null;
	}

	public void update(Bid bid) {
		BidMapper.updateByPrimaryKey(bid);
	}

	public void deleteById(Integer id) {
		BidMapper.deleteByPrimaryKey(id);
	}

	public void deleteAll() {
		BidMapper.deleteByExample(null);
	}

}
