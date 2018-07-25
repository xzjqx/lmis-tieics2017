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

import cn.edu.tju.tiei.logistics.model.Bid;
import cn.edu.tju.tiei.logistics.service.IBidService;

@RestController
public class BidRestController {

	@Autowired
	private IBidService bidService;
	
	/**
	 * Retrieve all bids
	 * @return
	 */
    @RequestMapping(value = "/bids", method = RequestMethod.GET)
    public ResponseEntity<List<Bid>> listAll() {
        List<Bid> bids = bidService.findAll();
        if (bids.isEmpty()) {
            return new ResponseEntity<List<Bid>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Bid>>(bids, HttpStatus.OK);
    }
    
    /**
     * Retrieve a single bid
     * @param id
     * @return
     */
    @RequestMapping(value = "/bids/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> get(@PathVariable("id") Integer id) {
    	Bid bid = bidService.findById(id);
        if (bid == null) {
            return new ResponseEntity<Bid>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Bid>(bid, HttpStatus.OK);
    }
    
    /**
     * Create a bid
     * @param bid
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/bids", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Bid bid, UriComponentsBuilder ucBuilder) {
        if (bidService.isExist(bid)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        bidService.create(bid);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/bid/{id}").buildAndExpand(bid.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    /**
     * Update a bid
     * @param id
     * @param bid
     * @return
     */
    @RequestMapping(value = "/bids/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Bid> update(@PathVariable("id") Integer id, @RequestBody Bid bid) {
        Bid oldBid = bidService.findById(id);
        if (oldBid == null) {
            return new ResponseEntity<Bid>(HttpStatus.NOT_FOUND);
        }
        oldBid.setBidTime(bid.getBidTime());
        oldBid.setCarrierId(bid.getCarrierId());
        oldBid.setPrice(bid.getPrice());
        oldBid.setWaybillId(bid.getWaybillId());
        bidService.update(oldBid);
        return new ResponseEntity<Bid>(oldBid, HttpStatus.OK);
    }
    
    /**
     * Delete a bid
     * @param id
     * @return
     */
    @RequestMapping(value = "/bids/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Bid> delete(@PathVariable("id") Integer id) {
        Bid bid = bidService.findById(id);
        if (bid == null) {
             return new ResponseEntity<Bid>(HttpStatus.NOT_FOUND);
        }
        bidService.deleteById(id);
        return new ResponseEntity<Bid>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Delete all bids
     * @return
     */
    @RequestMapping(value = "/bids", method = RequestMethod.DELETE)
    public ResponseEntity<Bid> deleteAll() {
        bidService.deleteAll();
        return new ResponseEntity<Bid>(HttpStatus.NO_CONTENT);
    }

}
