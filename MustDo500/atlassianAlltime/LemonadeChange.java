package MustDo500.atlassianAlltime;

/*
https://leetcode.com/problems/lemonade-change/
My submission : https://leetcode.com/problems/lemonade-change/submissions/1299265173/

TC : O(n)
SC : O(1)
 */
public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {

        int fiveDollarbill = 0;
        int tenDollarbill = 0;
        int twentyDollarbill = 0;
        if(bills == null || bills.length == 0) return false;

        for(int bill: bills){
            switch(bill){
                case 5:{
                    fiveDollarbill++;
                    break;
                }
                case 10:{
                    tenDollarbill++;
                    if(fiveDollarbill <= 0)return false;
                    else fiveDollarbill--;

                    break;
                }
                case 20:{
                    twentyDollarbill++;
                    // to give back 15$ 3 5$ bills or 10$+5$
                    if(tenDollarbill >= 1 && fiveDollarbill >=1){
                        tenDollarbill--;
                        fiveDollarbill--;
                        break;
                    }else if(fiveDollarbill >= 3){
                        fiveDollarbill = fiveDollarbill-3;
                        break;
                    }else{
                        return false;
                    }
                }
                default:
                    return false;

            }
        }

        return true;

    }

    public static void main(String[] args) {
        LemonadeChange ob = new LemonadeChange();
        System.out.println(ob.lemonadeChange(new int[]{5,5,5,10,20}));
        System.out.println(ob.lemonadeChange(new int[]{5,5,10,10,20}));
        System.out.println(ob.lemonadeChange(new int[]{5,5,5,10,5,5,10,20,20,20}));

    }
}
