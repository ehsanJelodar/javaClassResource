//package dbTojavaClass;

//================== data class
class data {
    private static byte[] sc1(){ return new byte[]{0,3,-128,56,0,0,0,12,0,6,0,0,0,48,0,1,-128,0,0,64,0,0,64,0,0,-128,0,0,32,0,};}
    private static byte[] sc2(){ return new byte[]{1,0,0,0,16,0,2,0,0,0,8,0,2,0,0,0,8,0,4,0,0,0,4,0,4,0,0,0,4,0,};}
    private static byte[] sc3(){ return new byte[]{4,0,0,0,4,0,8,0,0,0,2,0,8,0,15,0,2,0,8,0,31,-128,2,0,127,0,31,-128,2,0,};}
    private static byte[] sc4(){ return new byte[]{62,0,31,-128,2,0,28,0,31,-128,2,0,8,0,15,0,2,0,0,0,0,0,4,0,0,0,0,0,4,0,};}
    private static byte[] sc5(){ return new byte[]{0,0,0,0,4,0,0,0,0,0,8,0,2,0,0,0,8,0,1,0,0,0,16,0,0,-128,0,0,32,0,};}
    private static byte[] sc6(){ return new byte[]{0,64,0,0,64,0,0,48,0,1,-128,0,0,12,0,6,0,0,0,3,-128,56,0,0,0,0,127,-64,0,0,};}
    private static byte[] sc7(){ return new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};}
    private static byte[] sc8(){ return new byte[]{66,77,100,0,48,0,40,0,6,0,1,0,2,0,0,0,0,0,0,0,-1,-1,-1,0,0,0,0,0,0,0,};}
    private static byte[] sc9(){ return new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};}
    private static byte[] sc10(){ return new byte[]{63,-1,-1,-1,-1,-8,96,0,0,0,0,12,64,0,0,0,0,4,64,0,0,0,0,4,64,0,0,0,0,4,};}
    private static byte[] sc11(){ return new byte[]{64,0,0,0,0,4,64,0,0,0,0,4,64,0,0,0,0,4,64,0,0,0,0,4,64,0,0,0,0,6,};}
    private static byte[] sc12(){ return new byte[]{64,0,0,0,0,7,};}
    /// Return data section from 0 - 'indexSize' (total bytes: 336).
    public static byte[] getSection(int section){ switch (section){
        case 0: return sc1();
        case 1: return sc2();
        case 2: return sc3();
        case 3: return sc4();
        case 4: return sc5();
        case 5: return sc6();
        case 6: return sc7();
        case 7: return sc8();
        case 8: return sc9();
        case 9: return sc10();
        case 10: return sc11();
        case 11: return sc12();
        default: return new byte[]{};}
    }
    private static int indexSize = 12;
    public static int getSectionsSize(){ return indexSize;}
}
//================== data class

////////////////////////////////////////////
class example {

    public static void main (String args[]) {

        for (int i=0;i<data.getSectionsSize();i++)
        {
            byte[] section = data.getSection(i);
            for(int j=0;j<section.length;j++)
            {
                System.out.print(section[j] + ",") ;// here you can use byte array or stroe in another file.
            }
        }
    }
}
////////////////////////////////////////////