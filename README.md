# Logistics

## 1. Background and Project Description

###1.1 Background

​	We create a database named ‘logistics’, which is used by the logistics platform. The database include five tables: shipper, carrier, waybill, bid and usr. Requirement analysis:

- The shipper has the requirement to transport the goods. The attributes of the shipper are: id, name, address;
- The carrier is responsible for transporting the goods. The attributes of the carrier are: id, name, and point;
- The shipper pubilshes a waybill for the transportation of a batch of goods. The attributes of the waybill are: id, goods_good, loading_addr, unloading_addr, freight, order_time;
- A shipper can issue multiple waybills, one carrier can carry multiple waybills, but one waybill can only be issued by one shipper and carried by one carrier;
- The platform determines which carrier is to be carried by the freight bidding method (bid); for a waybill, several  carriers can quote the waybill; one carrier can quote several shipping waybills; each quotation must record the quotation time (bid_time) and the reported freight price (price); after the end of the quotation, the shipper selects a carrier to carry the waybill;
- The platform defines a user entity (usr) for account management purposes, whose attributes are the public attributes of the shipper and the carrier: id, name; the shipper and carrier entities are inherited from the user entity.

### 1.2 Project Description

​	‘Logistics’ is a Java Web project for teaching Database Systems and it is built by Eclipse Maven Project. The project is deployed by Spring + MyBatis: The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications - on any kind of deployment platform; MyBatis is a first class persistence framework with support for custom SQL, stored procedures and advanced mappings. 

​	In Database class, we had built the framework of ‘Logistics’ project. The process is as follows:

1)     Create a new Maven project in eclipse;

2)     Add related dependencies and plugins in pom.xml, such as Spring-Core and Mybatis Generator;

3)     Configure the connection to the database in the spring framework;

4)     Write mybatis-generator(generatorConfig.xml) code to auto-generate object-relational mapping files;

5)     Add services for the table ‘usr’ by using above object-relational mapping files.

​	In the rest of this project, we will add services and controllers for the other 4 tables and test the functionalities of the backend database by using Postman.

## 2. Requirement Specification

### 2.1 Database

#### 2.1.1 Use Case Diagram

![Fig. 1. The relationship of usr, shipper and carrier](C:\Users\xzjqx\Desktop\Study\Database\logistics\lmis-tieics2017\images\usr.jpg)



![Fig. 2. Waybill Management](C:\Users\xzjqx\Desktop\Study\Database\logistics\lmis-tieics2017\images\WaybillSystem.jpg)



![Fig. 3. Subsystem of Waybill Management](C:\Users\xzjqx\Desktop\Study\Database\logistics\lmis-tieics2017\images\WaybillSubSystem.jpg)

#### 2.1.2 Use Case Description

**Use Case:** bidding

**Primary Actor:** shipper, carrier

**Scop**e: a bidding system

**Level:** Manage waybills by bidding

**Brief:**

​	The shipper publishes a waybill for the transportation of a batch of goods. Several carriers can quote the waybill. After the end of the quotation, the shipper selects a carrier to carry the waybill.

**Stakeholders:** carrier

**Postconditions:**

- **Minimal Guarantees: ** A shipper publishes a waybill, and a carrier accepts the waybill.
- **Success Guarantees: ** A shipper publishes a waybill, several carriers quote the waybill. Finally, the shipper chooses the cheapest.

**Preconditions:**

​	Several shipper publish some waybills.

**Triggers:**

​	The shipper issues a shipping request to the platform.

**Basic flow:**

1. The platform collects waybill requests from each shipper and sends these waybills to carriers.
2. The carriers quote for the waybill they want.
3. The platform collects the price from each carrier for each waybill and sends them to the corresponding shipper.
4. The shipper receives all carriers’ quotations and chooses one of them to carry the goods.

### 2.2 Web Project

​	In ‘Logistics’ project, we hope to access and update database by using HTTP Request on the basis of Spring Framework. The design’s UML Use case diagram + Use case description are as follows(an example is ‘usr’ class). 

#### 2.1.1 Use Case Diagram

![Fig. 4. Use Case Diagram of ‘usr’ class](C:\Users\xzjqx\Desktop\Study\Database\logistics\lmis-tieics2017\images\usrUseCase.jpg)

#### 2.1.2 Use Case Description

​	The Use Cases above are similar, so I will make an example of ‘update’:

**Use Case:** update

**Primary Actor:** usr

**Scop**e: update the table ‘usr’

**Brief:**

​	The admin updates an existing table into a new one.

**Stakeholders:** usr

**Postconditions:** Update successfully.

**Preconditions:** A usr with some id needs to be updated.

**Triggers:**

​	The usr issues a update request to the platform.

## 3. Database Design

### 3.1 E-R Diagram

### 3.2 Relational Table Specification

Relation: usr

| Attribute | Description    | Data   type | Length | Is   NULL |
| --------- | -------------- | ----------- | ------ | --------- |
| id        | Id for a user  | VARCHAR     | 20     | No        |
| name      | Name of a user | VARCHAR     | 100    | No        |

Relation: shipper

| Attribute | Description          | Data   type | Length | Is   NULL |
| --------- | -------------------- | ----------- | ------ | --------- |
| id        | Id for a shipper     | VARCHAR     | 20     | No        |
| address   | Address of a shipper | VARCHAR     | 100    | No        |

Relation: carrier

| Attribute | Description        | Data   type | Length | Is   NULL |
| --------- | ------------------ | ----------- | ------ | --------- |
| id        | Id for a carrier   | VARCHAR     | 20     | No        |
| point     | Point of a carrier | SMALLINT    |        | No        |

Relation: waybill

| Attribute         | Description                    | Data   type | Length | Is   NULL |
| ----------------- | ------------------------------ | ----------- | :----- | --------- |
| id                | Id for a waybill               | CHAR        | 12     | No        |
| goods_name        | Goods name of a waybill        | VARCHAR     | 50     | No        |
| loading_address   | Loading address of a waybill   | VARCHAR     | 100    | No        |
| unloading_address | Unloading address of a waybill | VARCHAR     | 100    | No        |
| freight           | Freight of a waybill           | NUMERIC     | (9, 2) | No        |
| order_time        | Order time of a waybill        | TIMESTAMP   |        | No        |
| shipper_id        | Shipper Id of a waybill        | VARCHAR     | 20     | No        |
| carrier_id        | Carrier Id of a waybill        | VARCHAR     | 20     | No        |

Relation: bid

| Attribute  | Description         | Data   type | Length | Is   NULL |
| ---------- | ------------------- | ----------- | ------ | --------- |
| id         | Id for a bid        | SERIAL      |        | No        |
| waybill_id | Waybill Id of a bid | CHAR        | 12     | No        |
| carrier_id | Carrier Id of a bid | VARCHAR     | 20     | No        |
| bid_time   | Time of a bid       | TIMESTAMP   |        | No        |
| price      | Price of a bid      | NUMERIC     | (9, 2) | No        |



## 4. Development

​	In the Chapter 1, we said that the rest of the work is adding services and controllers for the other 4 tables and testing the functionalities of the backend database. Next, I will take the ‘shipper’ as an example to illustrate the process of adding services and controllers. 

### 4.1 Service

​       For ‘shipper’ class, there are seven functions in services, just like ‘usr’ class:

- findAll

  This function’s implement is in the file ‘ShipperServiceImpl.java’:

```java
public List<Shipper> findAll() {
        return ShipperMapper.selectByExample(null);
}
```

​	ShipperMapper is a class automatically generated by mybatis-generator. Thereinto, function ‘selectByExamplae’ is:

```java
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shipper
     *
     * @mbg.generated
     */
    List<Shipper> selectByExample(ShipperExample example);
```

​	It is observed that these codes are generated automatically. We can use them in the controller later. And The following functions are also in this form.

- findById

```java
    public Shipper findById(String id) {
        return ShipperMapper.selectByPrimaryKey(id);
    } 
```

- create

```java
    public void create(Shipper shipper) {
        ShipperMapper.insert(shipper);
    }
```

- isExist

```java
    public boolean isExist(Shipper shipper) {
        return ShipperMapper.selectByPrimaryKey(shipper.getId()) != null;
    }
```

- update

```java
    public void update(Shipper shipper) {
        ShipperMapper.updateByPrimaryKey(shipper);
    }
```

- deleteById

```java
    public void deleteById(String id) {
        ShipperMapper.deleteByPrimaryKey(id);
    }
```

- deleteAll

```java
    public void deleteAll() {
        ShipperMapper.deleteByExample(null);
    }
```

### 4.2 Controller

​       In controller layer, we hope to use services’ functions to access and update database by using HTTP Request(GET, POST, PUT, DELETE). Also use the ‘shipper’ as an example:

- Retrieve all shippers

```java
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
```

​       Its functionality is retrieving all shippers.

​       @RequestMapping is an annotation for mapping web requests onto methods in request-handling classes with flexible method signatures. In this function, it maps “/shippers” into a GET requests, and returns a response of information of the table ‘shipper’. The response could be used in the front-end operations.

- Retrieve a single shipper

```java
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
```

​       Unlike the last function, this function is retrieving a single shipper. The difference in implementation is that this function retrieves a shipper by Id. 

- Create a shipper

```java
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
```

​       This is a POST request, with a shipper as an param, creating a new term.

- Update a shipper

```java
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
```

​       This is a PUT request, with a id and a shipper as params, updating the old shipper(id) with the new one.

- Delete a shipper

```java
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
```

​       This is a DELETE request, with a id as an params, deleting the shipper with this id.

- Delete all shippers

```java
    /**
     * Delete all shippers
     * @return
     */
    @RequestMapping(value = "/shippers", method = RequestMethod.DELETE)
    public ResponseEntity<Shipper> deleteAll() {
        shipperService.deleteAll();
        return new ResponseEntity<Shipper>(HttpStatus.NO_CONTENT);
    }
```

​       This is also a DELETE request, which deletes the table ‘shipper’.