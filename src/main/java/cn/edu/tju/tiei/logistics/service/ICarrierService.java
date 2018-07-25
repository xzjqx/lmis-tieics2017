package cn.edu.tju.tiei.logistics.service;

import java.util.List;

import cn.edu.tju.tiei.logistics.model.Carrier;

public interface ICarrierService {

    /**
     * Load all carriers
     * @return
     */
    List<Carrier> findAll();

    Carrier findById(String id);

	void create(Carrier carrier);

	boolean isExist(Carrier carrier);

	void update(Carrier carrier);

	void deleteById(String id);

	void deleteAll();

}