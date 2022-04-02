import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		int[] arr =readFile("C:\\Users\\");
		if(arr == null)return;
		int size = arr.length;
		System.out.println();
		if(!check(arr, size)) {
			System.out.println("Invalid data");
		}
		maxLed(arr, size);
		
		
	}
	public static int[] readFile(String path){
		
		File file = null ;
		Scanner read = null;
	      try {
		 file = new File(path);
		 read = new Scanner(file);}catch (Exception e) {
			System.out.println("ERROR!!! File Not Exist");return null;
		}
	      int size = 0;
	      int arr[] = null;
	      try {
		      size =Integer.parseInt(read.nextLine());
		      arr = new int[size];
		      for(int i = 0 ; i < size ; i++) {
		    	  arr[i]=read.nextInt();
		    	  System.out.print((arr[i]+3840)+" ");
		      }
	      }catch (Exception e) {
			System.out.println("ERROR!!! , Invalid file input");return null;
		}
	      
	      return arr;

	}
	public static boolean check(int[] arr,int size) {
		/* we need to control the bits in the byte because we need 1 bit
		 * to represent array in hash(to check it) , if array element metions 1st time
		 * then the bit of this element(byte -> arr[i]/8 and bit -> (8-arr[i])%8 
		 * 'we put 8 not 7 becouse the data should be >= 1 ) will be 1
		 * and if the bit of element is 1 then we know its repetion and we return false
		 * if element > N or element <1 return false .
		 */
		int byteNumber = size /8 ;
		byte bitNumber = (byte) (size%8);
		byte[] hash ;
		if(size%8 == 0) hash= new byte[byteNumber];//suppose we have 8 element we need 1 byte to represent it(8/8)
		else hash= new byte[byteNumber+1];//suppose we have 5 element we need 1 byte to represent it(5/8+1)
		for(int i = 0 ; i < hash.length;i++)hash[i]=0;//assign zero to all elements of hash
		for(int i=0 ; i < size ; i++) {
			//if element > N or element <1 return false .
			if(arr[i] > size || arr[i] <1) {
				return false;
			}
			//get byte number and bit number of array element to check it
			byteNumber=(arr[i]-1)/8;
			bitNumber=(byte) (8-arr[i]%8);
			//check if the bit is 0 or one by shift left and bitwise AND operation with 0 byte 
			byte k = (byte) ((hash[byteNumber])&(1<<bitNumber));
			if(k == 0) {
				//if k == 0 then the byte does not metion befor and we need to make it 1
				//we make it 1 by shift left with bitwise OR with bit number
				hash[byteNumber]=(byte) (hash[byteNumber]|(1<<bitNumber));
			}else {
				return false ;
				}
			}
		return true ;
		}

	
	
	public static void maxLed(int[] arr ,int size ) {
		int[] optimal = new int[size];
		int[] indices = new int[size];
		for(int i =0;i<size;i++) {
			optimal[i]=1;
			indices[i]=i;
		}
		for(int i = 1 ; i < size ;i++) {
			for(int j = 0 ; j < i ;j++) {
				if(arr[i]>arr[j] && optimal[j] >= optimal[i]) {
					optimal[i]=optimal[j]+1;
					indices[i]=j;
				}
			}
		}
		int maxIndex = 0;
		for(int i=0 ; i < size ; i++) {
			if(optimal[i] > optimal[maxIndex])
				maxIndex=i;
		}
		print(arr,indices,maxIndex);
		
	}
	static void print(int[] arr , int[] indices ,int maxIndex){
		if(maxIndex==indices[maxIndex]){
			return;
		}
		for(int i=0 ; i < indices.length ; i++) {
		}
		print(arr,indices,indices[maxIndex]);
	}
	

}
