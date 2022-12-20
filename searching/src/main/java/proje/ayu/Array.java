package proje.ayu;



public class Array {

    static String[] films= new String [10];

    static String[] genreArray= new String [10];
    static boolean anahtar=true;
    static int arrayCount=0;
    
    public static void main(String[] args) {

        films[0]="Ali";
        films[1]="Ay";
        films[2]="Ali";
        films[3]="Ak";
        for (int i = 0; i < films.length; i++) {
            for (int j = 0; j < genreArray.length; j++) {
    
                if(genreArray[j]==films[i]){
                    anahtar=false;
                    break;
                }    
                
            
            }

            if(anahtar) {
                genreArray[arrayCount]=films[i];
                arrayCount++;
                

            }
            anahtar=true;          
        }


        for (int j = 0; j < genreArray.length; j++) 
            System.out.println(genreArray[j]);

    
           
    }
 
}
