package cn.edu.tju.tiei.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cn.edu.tju.tiei.logistics.model.Waybill;
import cn.edu.tju.tiei.logistics.service.IWaybillService;

@RestController
public class WaybillRestController {

	@Autowired
	private IWaybillService waybillService;
	
	/**
	 * Retrieve all waybills
	 * @return
	 */
    @RequestMapping(value = "/waybills", method = RequestMethod.GET)
    public ResponseEntity<List<Waybill>> listAll() {
        List<Waybill> waybills = waybillService.findAll();
        if (waybills.isEmpty()) {
            return new ResponseEntity<List<Waybill>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Waybill>>(waybills, HttpStatus.OK);
    }
    
    /**
     * Retrieve a single waybill
     * @param id
     * @return
     */
    @RequestMapping(value = "/waybills/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Waybill> get(@PathVariable("id") String id) {
    	Waybill waybill = waybillService.findById(id);
        if (waybill == null) {
            return new ResponseEntity<Waybill>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Waybill>(waybill, HttpStatus.OK);
    }
    
    /**
     * Create a waybill
     * @param waybill
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/waybills", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Waybill waybill, UriComponentsBuilder ucBuilder) {
        if (waybillService.isExist(waybill)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        waybillService.create(waybill);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/waybill/{id}").buildAndExpand(waybill.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    /**
     * Update a waybill
     * @param id
     * @param waybill
     * @return
     */
    @RequestMapping(value = "/waybills/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Waybill> update(@PathVariable("id") String id, @RequestBody Waybill waybill) {
        Waybill oldWaybill = waybillService.findById(id);
        if (oldWaybill == null) {
            return new ResponseEntity<Waybill>(HttpStatus.NOT_FOUND);
        }
        oldWaybill.setCarrierId(waybill.getCarrierId());
        oldWaybill.setFreight(waybill.getFreight());
        oldWaybill.setGoodsName(waybill.getGoodsName());
        oldWaybill.setLoadingAddress(waybill.getLoadingAddress());
        oldWaybill.setOrderTime(waybill.getOrderTime());
        oldWaybill.setShipperId(waybill.getShipperId());
        oldWaybill.setUnloadingAddress(waybill.getUnloadingAddress());
        waybillService.update(oldWaybill);
        return new ResponseEntity<Waybill>(oldWaybill, HttpStatus.OK);
    }
    
    /**
     * Delete a waybill
     * @param id
     * @return
     */
    @RequestMapping(value = "/waybills/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Waybill> delete(@PathVariable("id") String id) {
        Waybill waybill = waybillService.findById(id);
        if (waybill == null) {
             return new ResponseEntity<Waybill>(HttpStatus.NOT_FOUND);
        }
        waybillService.deleteById(id);
        return new ResponseEntity<Waybill>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Delete all waybills
     * @return
     */
    @RequestMapping(value = "/waybills", method = RequestMethod.DELETE)
    public ResponseEntity<Waybill> deleteAll() {
        waybillService.deleteAll();
        return new ResponseEntity<Waybill>(HttpStatus.NO_CONTENT);
    }

}
