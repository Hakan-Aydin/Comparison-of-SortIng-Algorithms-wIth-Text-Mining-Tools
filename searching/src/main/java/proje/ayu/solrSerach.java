package proje.ayu;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;


public class solrSerach
{

    static long startTime =0, endTime=0;

    public static void main(String[] args) throws SolrServerException, IOException
    {
      // Connect to server
     System.out.println("**Start**");
     startTime = System.nanoTime();
     HttpSolrClient solr=new HttpSolrClient.Builder("http://localhost:8983/solr/films").build();
     
     SolrQuery query = new SolrQuery();
     query.setQuery("Drama"); 
     query.setStart(0);
     query.setRows(569);
     query.set("fl", "name","directed_by");
     query.set("df", "genre");
     query.setSort("id", ORDER.asc);
     //query.

        
       
     QueryResponse response = solr.query(query);
    
      SolrDocumentList results = response.getResults();
      
      for (int i = 0; i < results.size(); ++i) {
        System.out.println(results.get(i));
       }
     
      System.out.println("****End****");
      endTime = System.nanoTime();
      System.out.println(((endTime - startTime)/1000000)+" ms"); 
   }
}