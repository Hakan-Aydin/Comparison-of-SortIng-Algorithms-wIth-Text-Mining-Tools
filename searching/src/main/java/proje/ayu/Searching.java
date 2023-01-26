package proje.ayu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.analysis.function.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Searching 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println("**Start**");
        startTime = System.nanoTime();

        filmsJSONArray();

        endTime = System.nanoTime();
        System.out.println("filmsJSONArray dizisini oluşturmak için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        System.out.println("************************");
        
        


        startTime = System.nanoTime();
        filmsGenreSearch2("Drama","Sports","and");

        endTime = System.nanoTime();
        System.out.println("aranan Drama türünde olan film bilgileri için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        System.out.println("************************");        

/* 
        startTime = System./nanoTime();
        filmsDirectorSearch("David Yates");

        endTime = System.nanoTime();
        System.out.println("aranan yönetmenin film bilgileri için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        System.out.println("************************"); 
        startTime = System./nanoTime();      

        //genre {kategori} dizilerini oluşturur
        readJSONArray2();
        endTime = System.nanoTime();
        System.out.println("readJSONArray2 için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        System.out.println("************************");
        /*
        startTime = System.nanoTime();
        genrePrint();
        endTime = System.nanoTime();
        System.out.println("println için geçen süre: "+((endTime - startTime)/1000000)+" ms");
       
        System.out.println("************************");
        startTime = System.nanoTime();
        
        maxGenre();
        endTime = System.nanoTime();
        System.out.println("maxGenre için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        System.out.println("************************");
        startTime = System.nanoTime();

        genreSort();
        endTime = System.nanoTime();
        System.out.println("genreSort için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        /* 
        System.out.println("************************");
        startTime = System.nanoTime();        
        genrePrint(); 
        endTime = System.nanoTime();
        System.out.println("genreSort için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        System.out.println("************************");
        startTime = System.nanoTime();            
        minGenre();
        endTime = System.nanoTime();
        System.out.println("minGenre için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        System.out.println("****End****");

       */ 

    }

    static String[] genreArray= new String [212];
    static int[] genreArrayCount= new int [212];

    static ArrayList<JSONObject> filmsArrays = new ArrayList<JSONObject>(); //json films objesini jsonarray'e çevir

    static int typeCount=0;
    static int searchingCount=0;
    static long startTime =0, endTime=0;

    

    //JSON içinden film kategorileri bir diziye aktarır. Kategorilerin içerdiği film sayılarını aynı index numaraası ile başka bir dizide tutar.
    private static void readJSONArray2() throws Exception {

        File file = new File("src/films2.json");
        String content = FileUtils.readFileToString(file, "utf-8");
    
        // Convert JSON string to JSONObject
        JSONObject filmsJsonObject = new JSONObject(content);  
        JSONArray films = filmsJsonObject.getJSONArray("films");
        JSONArray genre;

        for (int i = 0; i < films.length(); i++) {   // 2-> 
            genre = films.getJSONObject(i).getJSONArray("genre");   // films json dizisinden i. sıradaki objeyi içinde genre dizi elemanını alır
            for (int j = 0; j < genre.length(); j++) {  //10-> genre.length()
                String type = (String) genre.get(j);
                if(serachInArray(type)){
                    genreArray[typeCount]=type;
                    genreArrayCount[typeCount]=1;
                    typeCount++;    
                } 
            }    
        }

    }
 //JSON içinden film kategorileri bir diziye aktarır. Kategorilerin içerdiği film sayılarını aynı index numaraası ile başka bir dizide tutar.
 private static void filmsJSONArray() throws Exception {

    File file = new File("src/films2.json");
    String content = FileUtils.readFileToString(file, "utf-8");

    JSONObject myjson = new JSONObject(content);
    JSONArray the_json_array = myjson.getJSONArray("films");

    int size = the_json_array.length();
    
    for (int i = 0; i < size; i++) {
        JSONObject another_json_object = the_json_array.getJSONObject(i);
            //Blah blah blah...
            filmsArrays.add(another_json_object);
    }

    //Finally
    JSONObject[] jsons = new JSONObject[filmsArrays.size()];
    filmsArrays.toArray(jsons);
    
}

private static void filmsGenreSearch(String genre){
    int say=0;
    for(int i=0;i<filmsArrays.size();i++){
        //System.out.println(filmsArrays.get(i).getJSONArray("directed_by").length()); 
        for(int j=0;j<filmsArrays.get(i).getJSONArray("genre").length();j++)
            if(filmsArrays.get(i).getJSONArray("genre").get(j).equals(genre)){
                System.out.println(filmsArrays.get(i));
                say++;
                
            }
    }
    System.out.println(genre + " film türünde film sayısı:"+say);
}
private static void filmsGenreSearch2(String genre1, String genre2, String op){
    int say=0;
    if(op=="or"){
        for(int i=0;i<filmsArrays.size();i++){
            //System.out.println(filmsArrays.get(i).getJSONArray("directed_by").length());    
            for(int j=0;j<filmsArrays.get(i).getJSONArray("genre").length();j++)
                if(filmsArrays.get(i).getJSONArray("genre").get(j).equals(genre1) || filmsArrays.get(i).getJSONArray("genre").get(j).equals(genre2)){
                    System.out.println(filmsArrays.get(i));
                    say++;               
                }
                
        }
    }else if(op=="and"){
       
        for(int i=0;i<filmsArrays.size();i++){
            Boolean isGenre1=false,isGenre2=false;
            //System.out.println(filmsArrays.get(i).getJSONArray("directed_by").length());    
            for(int j=0;j<filmsArrays.get(i).getJSONArray("genre").length();j++){
                
                if(filmsArrays.get(i).getJSONArray("genre").get(j).equals(genre1)){
                   
                    isGenre1=true;                                
                }
                if(filmsArrays.get(i).getJSONArray("genre").get(j).equals(genre2)){
                    
                    isGenre2=true;                                
                } 
                if(isGenre1.equals(true) && isGenre2.equals(true)){
                    System.out.println(filmsArrays.get(i));
                    say++;
                }
            }

                
        }
    }
    System.out.println(genre1+ "," +genre2+ ","+op + " film türünde film sayısı:"+say);
}

private static void filmsDirectorSearch(String director){
    System.out.println(filmsArrays.size());
    for(int i=0;i<filmsArrays.size();i++){
        //System.out.println(filmsArrays.get(i).getJSONArray("directed_by").length());
        for(int j=0;j<filmsArrays.get(i).getJSONArray("directed_by").length();j++)
            if(filmsArrays.get(i).getJSONArray("directed_by").get(j).equals(director));
                //System.out.println(filmsArrays.get(i));
    }
}



    //Kategori dizisi içinde JSON'dan gelen kategorinin olup olmadığına bakar. Varsa kategori sayacını bir arttırır
    public static boolean serachInArray(String type) {
        boolean result=true;
        for (int k = 0; k < genreArray.length; k++) {
            if(genreArray[k]!=null){
                if(genreArray[k].equals(type)){
                    genreArrayCount[k]++;
                    result=false;
                    break;
                } 

            }
        }
        return result; 
    }

    //Kategori dizisinde en çok olan kategorileri, sayısı ile ekrana yazar
    public static void maxGenre(){
        int maxGenreIndex=0;
        int maxGenreCount=genreArrayCount[maxGenreIndex];

        for (int j = 0; j < genreArray.length; j++) 
            if(genreArrayCount[j]>genreArrayCount[maxGenreIndex]){
                maxGenreIndex=j;
                maxGenreCount=genreArrayCount[maxGenreIndex];  
            }
        for (int j = 0; j < genreArray.length; j++)
            if(genreArrayCount[j]==maxGenreCount)
                System.out.println(genreArray[j] + "-"+ genreArrayCount[j]);
    }

    //Kategori dizisinde en az olan kategorileri, sayısı ile ekrana yazar
    public static void minGenre(){
        int minGenreIndex=0;
        int minGenreCount=genreArrayCount[minGenreIndex];

        for (int j = 0; j < genreArray.length; j++) 
            if(genreArrayCount[j]<genreArrayCount[minGenreIndex]){
                minGenreIndex=j;
                minGenreCount=genreArrayCount[minGenreIndex];  
            }
        for (int j = 0; j < genreArray.length; j++)
            if(genreArrayCount[j]==minGenreCount)
                System.out.println(genreArray[j] + "-"+ genreArrayCount[j]);
    }


    //Film Kategori dizisini ençoktan en aza sırala. 
    public static void genreSort() {
        for (int j = 0; j < genreArrayCount.length; j++) 
            for (int k = 0; k < genreArrayCount.length; k++)
                if(genreArrayCount[j]>genreArrayCount[k]){
                    int cacheCount=genreArrayCount[j];
                    genreArrayCount[j]=genreArrayCount[k];
                    genreArrayCount[k]=cacheCount;

                    String cacheGenre=genreArray[j];
                    genreArray[j]=genreArray[k];
                    genreArray[k]=cacheGenre;
                }        

    }

    //Film Kategori dizisini Ekrana Yaz.
    public static void genrePrint() {
        for (int j = 0; j < genreArray.length; j++) 
            System.out.println(j+"-"+genreArray[j] + "-"+ genreArrayCount[j]);        
    }

    //JSON dosyadan dizi objeyi ekrana yazdırmak
    private static void readJSONArray() throws Exception {
        
        File file = new File("src/ornek.json");
        String content = FileUtils.readFileToString(file, "utf-8");
    
        // Convert JSON string to JSONObject
        JSONObject filmsJsonObject = new JSONObject(content);  
        JSONArray genre = filmsJsonObject.getJSONArray("genre");

        for (int i = 0; i < genre.length(); i++) {
            String type = (String) genre.get(i);
            System.out.println(type);
        }
        
        // Or convert the JSONArray to Java List
        List<Object> types = genre.toList();
        for (Object type : types) {
            System.out.println((String)type);
        }
    }

    ////JSON dosyadan bir objeyi ekrana yazdırmak
    private static void readJSONObject() throws Exception {
        
        File file = new File("src/ornek.json");
        String content = FileUtils.readFileToString(file, "utf-8");
    
        // Convert JSON string to JSONObject
        JSONObject ornekJsonObject = new JSONObject(content);  
        String name = ornekJsonObject.getString("name");
        int age = ornekJsonObject.getInt("age");
        System.out.println(name + " " + age);
    }



}

