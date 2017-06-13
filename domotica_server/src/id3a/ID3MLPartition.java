package id3a;

/*
* Author : Bhojal Gelda
*Date : 30th June 2014
* Purpose : ID3 Decision Tree Algorithm Implementation
* Input : Three files in the following order:
*   1) Data File (data.txt)
*   2) Partition File (partition.txt)
*   3) Output File (use any name for it)
* Output : The final decision tree after applying ID3
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ID3MLPartition {

public ArrayList<DataRecords> dataset = new ArrayList<DataRecords>();   // holds all the records from data.txt
public ArrayList<DataRecords> partition = new ArrayList<DataRecords>(); //holds all the original partition
public String symbols = "";  //holds symbols that denote partitions
int totalDataCount;
int attributeCount;
public static void main(String[] args) throws Exception {
    ID3MLPartition obj = new ID3MLPartition();
    //load data from data.txt
    obj.readDataFile("C:\\Users\\kyosh\\Desktop\\Tetes\\data1.txt");
    //read in all the partitions
    obj.readPartition("C:\\Users\\kyosh\\Desktop\\Tetes\\partition1.txt");

    obj.PerformPartition("C:\\Users\\kyosh\\Desktop\\Tetes\\out.txt");
}
public void PerformPartition(String output) throws Exception
{
	ArrayList<Double> gain = new ArrayList<Double>();
    HashMap<Integer, Double> power = new HashMap<Integer, Double>();
    ArrayList<Integer> feature = new ArrayList<Integer>();
    int sorted = 0;
    double tempgain = 0;
        /*for each partitions*/
        for(int i=0; i<partition.size(); i++)
        {
            DataRecords set = partition.get(i); //get all the nodes in a partition  
            //array of integers

            /*reset flag*/
            sorted = 0;

            int zerocount = 0;
            int onecount = 0;
            //for each tuple in the partition
            for(int j=0; j<set.getsize(); j++)
            {
                //get each tuple one by one from the partition
                DataRecords ent = dataset.get(Integer.parseInt(set.get(j))-1); 
                //count the output values, one and zeroes
                if(Integer.parseInt(ent.get(ent.getsize()-1)) == 0)
                {
                    zerocount++;
                }
                else if(Integer.parseInt(ent.get(ent.getsize()-1)) == 1)
                {
                    onecount++;
                }
            }
            //entropy of this partition
            double entropy = evalLog((double)zerocount/(double)(set.getsize())) + evalLog((double)onecount/(double)(set.getsize()));
            System.out.println("part ent:" +entropy);
            System.out.println("onecount" +onecount);
            System.out.println("zerocount" +zerocount);

            if(entropy == 0)
                {
                sorted = 1;

                for(int k=0;k<attributeCount-1;k++)
                {
                gain.add((double) 0);
                }}
            //if entropy not zero, calculate the conditional entropies wrt each attribute
            if(sorted != 1)
            {
                int att0 = 0;
                int att1 = 0;
                int att2 = 0;
                int zerocount0 = 0;
                int onecount0 = 0;
                int zerocount1 = 0;
                int onecount1 = 0;
                int zerocount2 =0;
                int onecount2=0;

                /*for all attributes except the target attribute*/
                for(int att=0; att<attributeCount-1; att++)
                {
                    att0 = 0;
                    att1 = 0;
                    att2 = 0;
                    zerocount0 = 0;//final variable 0 when attribute value 0
                    onecount0 = 0;//final variable 1 when attribute value 0
                    zerocount1 = 0;
                    onecount1 = 0;//final variable 0 when attribute value 1
                    zerocount2 = 0;//final variable 1 when attribute value 1
                    onecount2 = 0;
                    //for all the nodes in the partition
                    for(int j=0; j<set.getsize(); j++)  //set is all the nodes in the partition
                    {
                        DataRecords ent = dataset.get(Integer.parseInt(set.get(j))-1);  //get the tuple

                        if(Integer.parseInt(ent.get(att)) == 0)  // if the given attribute value is zero
                        {
                            att0++;
                            if(Integer.parseInt(ent.get(ent.getsize()-1)) == 0)  //if the target value is 0
                                zerocount0++;
                            else if(Integer.parseInt(ent.get(ent.getsize()-1)) == 1)//if the target value is 1
                                onecount0++;

                        }
                        else if(Integer.parseInt(ent.get(att)) == 1)  // if the given attribute value is one
                        {
                            att1++;
                            if(Integer.parseInt(ent.get(ent.getsize()-1)) == 0)//if the target attribute value is 0
                                zerocount1++;
                            else if(Integer.parseInt(ent.get(ent.getsize()-1)) == 1)//if the target value is 1
                                onecount1++;
                        }
                        else if(Integer.parseInt(ent.get(att)) == 2)  // if the given attribute value is two
                        {
                            att2++;
                            if(Integer.parseInt(ent.get(ent.getsize()-1)) == 0)//if the target attribute value is 0
                                zerocount2++;
                            else if(Integer.parseInt(ent.get(ent.getsize()-1)) == 1) //if the target attribute is 1
                                onecount2++;

                        }

                    }
                    System.out.print("att1" +att1);
                    double ent1=0,ent2=0, ent3=0;

                    if(att0 != 0)
                        {
                        ent1 = ((double)att0/(double)(att0 + att1 + att2))*(evalLog((double)zerocount0/(double)(zerocount0 + onecount0)) + evalLog((double)onecount0/(double)(zerocount0 + onecount0)));
                        }
                    else
                        {
                        ent1 = 0;
                        }

                    if(att1 != 0)
                        {
                        ent2 = ((double)att1/(double)(att0 + att1 + att2))*(evalLog((double)zerocount1/(double)(zerocount1 + onecount1)) + evalLog((double)onecount1/(double)(zerocount1 + onecount1)));
                        }
                    else
                        {
                        ent2 = 0;
                        }

                    if(att2 != 0)
                    {
                    ent3 = ((double)att2/(double)(att0 + att1 + att2))*(evalLog((double)zerocount2/(double)(zerocount2 + onecount2)) + evalLog((double)onecount2/(double)(zerocount2 + onecount2)));
                    }
                else
                    {
                    ent3 = 0;
                    }
                    System.out.println("conditional entropy:" +(ent1 + ent2 + ent3));
                    gain.add(entropy-(ent1 + ent2 + ent3));

            }//for all attributes loop end

                tempgain = gain.get(0);
                int flag = 0;
                for(int t=1; t<gain.size(); t++)
                {
                    if(tempgain < gain.get(t))
                    {
                        tempgain = gain.get(t);
                        feature.add(t+1);
                        flag = 1;
                    }
                }
                if(flag == 0)
                {
                    feature.add(1);
                }

                double temp = tempgain*((double)(set.getsize()-1)/(double)dataset.size());
                power.put(i, temp);



            } //if sorted loop end  
            else
            {
                power.put(i, 0.0);
            }   

        }   System.out.println(gain);   
        getMaxGain(gain);
} //end Partition
public void readDataFile(String file) throws Exception
{
	FileReader fr = new FileReader(file);
    BufferedReader buffer = new BufferedReader(fr);
    String line = new String();

    //first line count and attribute
    line = buffer.readLine();
    String[] vals = line.split(" ");

    if(vals.length != 2)
    {
        System.out.println("error in file");
        System.exit(0); 
    }

    totalDataCount = Integer.valueOf(vals[0]);
    attributeCount = Integer.valueOf(vals[1]);

    //all lines storing in content 
    for(int i=0; i<totalDataCount; i++)
    {
        line = buffer.readLine();

        String[] divide = line.split(" ");

        DataRecords content = new DataRecords();

        for(int j=0; j<attributeCount; j++)
        {
            content.add(Integer.valueOf(divide[j]).toString());
        }

        dataset.add(content);
    } //end readDataset

    buffer.close();
}

public void readPartition(String name) throws Exception
{
	FileReader fd = new FileReader(name);
    BufferedReader buff = new BufferedReader(fd);

    String str = new String();

    while((str = buff.readLine()) != null)
    {
        String[] partit = str.split(" ");
        DataRecords part = new DataRecords();
        symbols += partit[0] + " "; //get all the partition symbols
        for(int i=1; i<partit.length; i++)
        {

                part.add(Integer.valueOf(partit[i]).toString());
        }
        partition.add(part);
    }
    //display partitions
    for(int i=0; i<partition.size(); i++)
    {
        DataRecords disp = new DataRecords();
        disp = partition.get(i);

        for(int j=0; j<disp.getsize(); j++)
            System.out.print(disp.get(j));
        System.out.println();
    }
    System.out.println(symbols);
buff.close();
} //end readPartition


private static double evalLog(double num)
{
    //System.out.println("num:"+num);
    double ans;
    if(num != 0)
    {
        ans = num*(Math.log(1/num)/Math.log(2));
    }
    else
    {
        ans = 0;
    }
    return ans;
}

//This method does the actual partitioning by calculating the partition which will be 
//partitioned and the index of the attribute at which to partition
public void getMaxGain(ArrayList<Double> gain){

    ArrayList<Double> gainProcess=gain;
    ArrayList<Double> f = new ArrayList<Double>();
    Double largestGain=0.0;
    Double largestF=0.0;
    int[] largestGainIndex = new int[partition.size()];
    Integer toParition=-1;
    int partitionNum=0;
    int pp=0;
    int k=1;
    largestGain=gainProcess.get(0);
    largestGainIndex[0]=0;
    int ll=1;  //This variable is used to 
    while(k<gainProcess.size()){
        //System.out.println("k="+k);

        //System.out.println("K="+k+" || LL="+ll);
        if(largestGain<gainProcess.get(k))  
          {
            largestGain= gainProcess.get(k);
            largestGainIndex[partitionNum]= pp+1;
          }

        //attribute to partition on
        System.out.println("Storing PP:"+pp+" for partition:"+partitionNum);
        pp=pp+1;
        if(ll%(attributeCount-2)==0)
            {  
                System.out.println("**");
                if(partitionNum<=partition.size() ){
                double prob=(double)(partition.get(partitionNum).getsize())/(double)totalDataCount;
                f.add((prob*largestGain));  
                partitionNum=partitionNum+1;
                k=k+1;  //3
                if(k<gainProcess.size())
                largestGain=gainProcess.get(k);
                pp=0;
                ll=0;
                }
            }
        k=k+1; //4
        ll=ll+1;
    }

    largestF=f.get(0);
    toParition=f.indexOf(largestF);
    for(int m=1;m<f.size();m++){
        if(largestF<=f.get(m))
            largestF=f.get(m);
        toParition=f.indexOf(largestF);
        System.out.println("initiating toParition");
    }
    for(int i=0;i<f.size();i++){
        System.out.println("f="+f.get(i));
    }

    for(int i=0;i<largestGainIndex.length;i++){
        System.out.println("largestGainIndex of partition "+i+"="+largestGainIndex[i]+ " with value of toPartition:"+toParition);
    }
    System.out.println("Largest F : "+largestF+" toPartitionOnAttribute:"+largestGainIndex[toParition] );

    //Partitioning the partition to partition

    ArrayList<DataRecords> temp= partition;
    DataRecords pPartition = temp.get(toParition); // toPartiiton =1
    // Holds partitions based on similar attribute values. p1, p2, p3 are actually the new partitions holders.
    // This code assumes that there is atmost 3 types of values possible for an instance attribute which are either 0,1 or 2.
    DataRecords p1= new DataRecords(); 
    DataRecords p2= new DataRecords();
    DataRecords p3= new DataRecords();
//  String[] s= symbols.split(" ");

    DataRecords newPArray = new DataRecords();
    int f1=0,f2=0,f3=0;
    for(int x=0;x<pPartition.getsize();x++){   //partition 12 of size 12
        newPArray = dataset.get(Integer.parseInt(pPartition.get(x))-1); 
            //debug statement to print the attribute at which the partition has to happen
            //System.out.println(largestGainIndex[toParition-1]-((attribute-1)*(toParition-1)));
            int viv = Integer.parseInt(newPArray.get(largestGainIndex[toParition]));//-((noOfAttributes-1)*(toParition))));

            if(viv==0)
            {
                if(f1==0){
                    System.out.println("Replacing attribute:="+symbols.split(" ")[toParition]+", with := "+symbols.split(" ")[toParition]+"1");
                    symbols.replace(symbols.split(" ")[toParition], symbols.split(" ")[toParition]+"1");
                    p1.add(symbols.split(" ")[toParition]+"1");
                    f1=f1+1;
                }
                p1.add(pPartition.get(x));
            }
            if(viv==1)
            {

                if(f2==0){
                    System.out.println("Replacing attribute:="+symbols.split(" ")[toParition]+", with := "+symbols.split(" ")[toParition]+"2");
                    symbols.replace(symbols.split(" ")[toParition], symbols.split(" ")[toParition]+"2");
                    f2=f2+1;
                    p2.add(symbols.split(" ")[toParition]+"2");
                }
                p2.add(pPartition.get(x));
            }
            if(viv==2)
            {
                if(f3==0){
                    System.out.println("Replacing attribute:="+symbols.split(" ")[toParition]+", with := "+symbols.split(" ")[toParition]+"3");
                    symbols.replace(symbols.split(" ")[toParition], symbols.split(" ")[toParition]+"3");
                    f3=f3+1;
                    p3.add(symbols.split(" ")[toParition]+"3");
                }
                p3.add(pPartition.get(x));
            }
        }

    ArrayList<DataRecords> newParition = partition;
    newParition.remove(toParition.intValue());
    if(p1.getsize()>0)
        newParition.add(p1);
    if(p2.getsize()>0)
        newParition.add(p2);
    if(p3.getsize()>0)
        newParition.add(p3);

    //Printing the new partition
    System.out.println("BELOW IS THE NEW PARTITION DATA");
    for(int i=0;i<newParition.size();i++){
        for(int j=0;j<newParition.get(i).getsize();j++)
        System.out.print(newParition.get(i).get(j)+" ");
        System.out.println();
    }
    //writing partitions to the output file.
    writeToOutputFile(newParition);

}

public void writeToOutputFile(List<DataRecords> partition){

    try {
        BufferedWriter bw=new BufferedWriter(new FileWriter("output.txt"));
        String line="";
        for(int i=0;i<partition.size();i++){
            for(int j=0;j<partition.get(i).getsize();j++)
            line=line+partition.get(i).get(j)+" ";
            bw.append(line);
            bw.newLine();
            line="";
        }
        bw.close();
    } catch (IOException e) {

        e.printStackTrace();
    }
}

private class DataRecords {

    private ArrayList<String> dataSet = new ArrayList<String>();

    public void add(String d){
        dataSet.add(d);
    }

    public int getsize(){
        return dataSet.size();
    }

    public String get(int i){
        return dataSet.get(i);
    }


}
}