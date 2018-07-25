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

import cn.edu.tju.tiei.logistics.model.Carrier;
import cn.edu.tju.tiei.logistics.service.ICarrierService;

@RestController
public class CarrierRestController {

	@Autowired
	private ICarrierService carrierService;
	
	/**
	 * Retrieve all carriers
	 * @return
	 */
    @RequestMapping(value = "/carriers", method = RequestMethod.GET)
    public ResponseEntity<List<Carrier>> listAll() {
        List<Carrier> carriers = carrierService.findAll();
        if (carriers.isEmpty()) {
            return new ResponseEntity<List<Carrier>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Carrier>>(carriers, HttpStatus.OK);
    }
    
    /**
     * Retrieve a single carrier
     * @param id
     * @return
     */
    @RequestMapping(value = "/carriers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carrier> get(@PathVariable("id") String id) {
    	Carrier carrier = carrierService.findById(id);
        if (carrier == null) {
            return new ResponseEntity<Carrier>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Carrier>(carrier, HttpStatus.OK);
    }
    
    /**
     * Create a carrier
     * @param carrier
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/carriers", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Carrier carrier, UriComponentsBuilder ucBuilder) {
        if (carrierService.isExist(carrier)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        carrierService.create(carrier);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/carrier/{id}").buildAndExpand(carrier.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    /**
     * Update a carrier
     * @param id
     * @param carrier
     * @return
     */
    @RequestMapping(value = "/carriers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Carrier> update(@PathVariable("id") String id, @RequestBody Carrier carrier) {
        Carrier oldCarrier = carrierService.findById(id);
        if (oldCarrier == null) {
            return new ResponseEntity<Carrier>(HttpStatus.NOT_FOUND);
        }
        oldCarrier.setPoint(carrier.getPoint());
        carrierService.update(oldCarrier);
        return new ResponseEntity<Carrier>(oldCarrier, HttpStatus.OK);
    }
    
    /**
     * Delete a carrier
     * @param id
     * @return
     */
    @RequestMapping(value = "/carriers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Carrier> delete(@PathVariable("id") String id) {
        Carrier carrier = carrierService.findById(id);
        if (carrier == null) {
             return new ResponseEntity<Carrier>(HttpStatus.NOT_FOUND);
        }
        carrierService.deleteById(id);
        return new ResponseEntity<Carrier>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Delete all carriers
     * @return
     */
    @RequestMapping(value = "/carriers", method = RequestMethod.DELETE)
    public ResponseEntity<Carrier> deleteAll() {
        carrierService.deleteAll();
        return new ResponseEntity<Carrier>(HttpStatus.NO_CONTENT);
    }

}
