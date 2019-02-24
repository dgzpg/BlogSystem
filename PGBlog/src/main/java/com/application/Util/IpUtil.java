package com.application.Util;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

public class IpUtil 
{    
	// 创建 GeoLite2 数据库 
	 
	public  static String getIp(String ip) throws Exception
	{
	 File database = new File("src/main/resources/static/sql/GeoLite2-City.mmdb"); 
	 // 读取数据库内容 
	 DatabaseReader reader = new DatabaseReader.Builder(database).build(); 
	 InetAddress ipAddress = InetAddress.getByName(ip); 

	 // 获取查询结果 
	 CityResponse response = reader.city(ipAddress); 

	 // 获取国家信息
	 Country country = response.getCountry();
	// System.out.println(country.getIsoCode()); // 'CN'
	 //System.out.println(country.getName()); // 'China'
	 String nation=country.getNames().get("zh-CN");
	System.out.println(nation); // '中国'

	 // 获取省份
	 Subdivision subdivision = response.getMostSpecificSubdivision();
	// System.out.println(subdivision.getName()); // 'Guangxi Zhuangzu Zizhiqu'
	 //System.out.println(subdivision.getIsoCode()); // '45'
	 String province=subdivision.getNames().get("zh-CN");
	 System.out.println(province); // '广西壮族自治区'

	 // 获取城市
	 City city = response.getCity();
	// System.out.println(city.getName()); // 'Nanning'
	 //Postal postal = response.getPostal();
	 //System.out.println(postal.getCode()); // 'null'
	 String cityName=city.getNames().get("zh-CN");
	 System.out.println(city); // '南宁'
	// Location location = response.getLocation();
	/* System.out.println(location.getLatitude()); // 22.8167
	 System.out.println(location.getLongitude()); // 108.3167
*/
	return nation+" "+province+" "+cityName;
	// return null;
	}
}
		
				
			
					
					
				
			
			
			
		
	


