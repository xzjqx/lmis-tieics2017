package cn.edu.tju.tiei.logistics.service;

import java.util.List;

import cn.edu.tju.tiei.logistics.model.Shipper;

public interface IShipperService {

    /**
     * Load all shippers
     * @return
     */
    List<Shipper> findAll();

    Shipper findById(String id);

	void create(Shipper shipper);

	boolean isExist(Shipper shipper);

	void update(Shipper shipper);

	void deleteById(String id);

	void deleteAll();

}