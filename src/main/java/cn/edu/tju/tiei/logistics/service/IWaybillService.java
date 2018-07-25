package cn.edu.tju.tiei.logistics.service;

import java.util.List;

import cn.edu.tju.tiei.logistics.model.Waybill;

public interface IWaybillService {

    /**
     * Load all waybills
     * @return
     */
    List<Waybill> findAll();

    Waybill findById(String id);

	void create(Waybill waybill);

	boolean isExist(Waybill waybill);

	void update(Waybill waybill);

	void deleteById(String id);

	void deleteAll();

}