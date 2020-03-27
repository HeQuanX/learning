package cn.crabapples.demo;//package Mr.He.demo;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//
//import javax.naming.directory.SearchResult;
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * TODO ElasticSearch演示
// *
// * @author Mr.He
// * 12/14/19
// * e-mail crabapples.cn@gmail.com
// * qq 294046317
// * pc-name root
// */
//public class ElasticSearchUtils {
//    public static void main(String[] args) throws IOException {
////        # 这里默认配置一个es的集群名字
//
////        Settings settings = Settings.builder().put("cluster.name", "linux-crabapples1").build();
////        TransportClient client = new PreBuiltTransportClient(settings)
////                .addTransportAddress(new TransportAddress(InetAddress.getByName("linux.crabapples.cn"), 9300));
//        Map data = new HashMap<>();
//        data.put("test", "test log");
//        data.put("time", System.currentTimeMillis());
//
////        IndexResponse response = client.prepareIndex("test", "log", UUID.randomUUID().toString()).setSource(data).get();
//
////        System.out.println("id: "+response.getId());
//        String host = "linux.crabapples.cn";
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder( new HttpHost(host, 9200, "http")));
//        SearchRequest searchRequest = new SearchRequest();
//        RequestOptions requestOptions = new RequestOptions(RequestOptions.Builder);
//        client.search(searchRequest,requestOptions);
//    }
//}
