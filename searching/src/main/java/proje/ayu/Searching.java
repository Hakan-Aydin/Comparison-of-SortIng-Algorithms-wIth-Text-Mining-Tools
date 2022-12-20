package proje.ayu;

import java.io.File;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class Searching 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println("**Start**");
        startTime = System.nanoTime();

        //genre {kategori} dizilerini oluşturur
        readJSONArray2();
        endTime = System.nanoTime();
        System.out.println("readJSONArray2 için geçen süre: "+((endTime - startTime)/1000000)+" ms");
        System.out.println("************************");
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

        

    }

    static String[] genreArray= new String [212];
    static int[] genreArrayCount= new int [212];

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

