# set-timeout-unirest-resttemplate
Project ini running di port **8081**

Ada 2 parameter yang perlu di set untuk mengimplement timeout:  

- **ConnectTimeout**  
Ini adalah waktu untuk sebuah request sampai di API/server tujuan.  
Bisa timeout dikarenakan jaringan yg lambat.

- **ReadTimeout**  
Ini adalah waktu untuk menunggu response dari API tujuan.  
Dengan kata lain adalah waktu sebuah api melakukan proses dan me-return sebuah response.  
Bisa timeout dikarenakan proses di API/server tujuan memakan waktu yang sangat lama.



## Rest Template  
```
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    requestFactory.setConnectTimeout(5000); // milisecond, 5 detik
    requestFactory.setReadTimeout(5000); // milisecond, 5 detik
    
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(requestFactory);
    response = restTemplate.getForObject("http://localhost:8080/greeting", String.class);
```  
perlu menggunakan **requestFactory** untuk setting/config timeout.  
lalu set **requestFactory** ke rest template:  
    **restTemplate.setRequestFactory(requestFactory);**

## Unirest  
```
    Unirest.setTimeouts(5000, 5000);
    response = Unirest.get("http://localhost:8080/greeting").asString();
```  
Unirest.setTimeouts(_ConnectTimeout_, _SocketTimeout_);  
**SocketTimeout** sama pengertian nya dengan **ReadTimeout**  

## Sample Code  
sample code ada di https://github.com/dher/set-timeout-unirest-resttemplate/blob/master/src/main/java/com/example/restservice/CobaTimeout.java
