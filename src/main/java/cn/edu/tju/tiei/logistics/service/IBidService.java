package cn.edu.tju.tiei.logistics.service;

import java.util.List;

import cn.edu.tju.tiei.logistics.model.Bid;

public interface IBidService {

    /**
     * Load all bids
     * @return
     */
    List<Bid> findAll();

    Bid findById(Integer id);

	void create(Bid bid);

	boolean isExist(Bid bid);

	void update(Bid bid);

	void deleteById(Integer id);

	void deleteAll();

}