package cn.edu.tju.tiei.logistics.main;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.edu.tju.tiei.logistics.dao.BidMapper;
import cn.edu.tju.tiei.logistics.dao.CarrierMapper;
import cn.edu.tju.tiei.logistics.dao.ShipperMapper;
import cn.edu.tju.tiei.logistics.dao.UsrMapper;
import cn.edu.tju.tiei.logistics.dao.WaybillMapper;
import cn.edu.tju.tiei.logistics.model.Bid;
import cn.edu.tju.tiei.logistics.model.Carrier;
import cn.edu.tju.tiei.logistics.model.Shipper;
import cn.edu.tju.tiei.logistics.model.Usr;
import cn.edu.tju.tiei.logistics.model.Waybill;

public class Main {

	public static void main(String[] args) {
		
		String resource = "MapperConfig.xml";
		
		try {
			Reader inputStream = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			BidMapper bidMapper = sqlSession.getMapper(BidMapper.class);
			List<Bid> bids = bidMapper.selectByExample(null);
			
			for (Bid bid : bids) {
				System.out.println(bid);
			}
			
			CarrierMapper carrierMapper = sqlSession.getMapper(CarrierMapper.class);
			List<Carrier> carriers = carrierMapper.selectByExample(null);
			
			for (Carrier carrier : carriers) {
				System.out.println(carrier);
			}
			
			ShipperMapper shipperMapper = sqlSession.getMapper(ShipperMapper.class);
			List<Shipper> shippers = shipperMapper.selectByExample(null);
			
			for (Shipper shipper : shippers) {
				System.out.println(shipper);
			}
			
			UsrMapper usrMapper = sqlSession.getMapper(UsrMapper.class);
			List<Usr> usrs = usrMapper.selectByExample(null);
			
			for (Usr usr : usrs) {
				System.out.println(usr);
			}
			
			WaybillMapper waybillMapper = sqlSession.getMapper(WaybillMapper.class);
			List<Waybill> waybills = waybillMapper.selectByExample(null);
			
			for (Waybill waybill : waybills) {
				System.out.println(waybill);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
