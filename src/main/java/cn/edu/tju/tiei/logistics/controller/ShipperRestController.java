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

import cn.edu.tju.tiei.logistics.model.Shipper;
import cn.edu.tju.tiei.logistics.service.IShipperService;

@RestController
public class ShipperRestController {

	@Autowired
	private IShipperService shipperService;
	
	/**
	 * Retrieve all shippers
	 * @return
	 */
    @RequestMapping(value = "/shippers", method = RequestMethod.GET)
    public ResponseEntity<List<Shipper>> listAll() {
        List<Shipper> shippers = shipperService.findAll();
        if (shippers.isEmpty()) {
            return new ResponseEntity<List<Shipper>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shipper>>(shippers, HttpStatus.OK);
    }
    
    /**
     * Retrieve a single shipper
     * @param id
     * @return
     */
    @RequestMapping(value = "/shippers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shipper> get(@PathVariable("id") String id) {
    	Shipper shipper = shipperService.findById(id);
        if (shipper == null) {
            return new ResponseEntity<Shipper>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Shipper>(shipper, HttpStatus.OK);
    }
    
    /**
     * Create a shipper
     * @param shipper
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/shippers", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Shipper shipper, UriComponentsBuilder ucBuilder) {
        if (shipperService.isExist(shipper)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        shipperService.create(shipper);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/shipper/{id}").buildAndExpand(shipper.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    /**
     * Update a shipper
     * @param id
     * @param shipper
     * @return
     */
    @RequestMapping(value = "/shippers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Shipper> update(@PathVariable("id") String id, @RequestBody Shipper shipper) {
        Shipper oldShipper = shipperService.findById(id);
        if (oldShipper == null) {
            return new ResponseEntity<Shipper>(HttpStatus.NOT_FOUND);
        }
        oldShipper.setAddress(shipper.getAddress());
        shipperService.update(oldShipper);
        return new ResponseEntity<Shipper>(oldShipper, HttpStatus.OK);
    }
    
    /**
     * Delete a shipper
     * @param id
     * @return
     */
    @RequestMapping(value = "/shippers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Shipper> delete(@PathVariable("id") String id) {
        Shipper shipper = shipperService.findById(id);
        if (shipper == null) {
             return new ResponseEntity<Shipper>(HttpStatus.NOT_FOUND);
        }
        shipperService.deleteById(id);
        return new ResponseEntity<Shipper>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Delete all shippers
     * @return
     */
    @RequestMapping(value = "/shippers", method = RequestMethod.DELETE)
    public ResponseEntity<Shipper> deleteAll() {
        shipperService.deleteAll();
        return new ResponseEntity<Shipper>(HttpStatus.NO_CONTENT);
    }

}
