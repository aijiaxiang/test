package com.example.helloworld.lessonone;

/**
 * Author:aijiaxiang
 * Date:2020/3/3
 * Description:
 */
public class LessonOne {
    public static void main(String[] args) {
        int[] a = {1,2,3};
        int[] b = new int[5];
        b[0] = 1;
        b[1] = 2;
        b[2] = 3;
        b[3] = 4;
        b[4] = 5;
        System.out.println(b);

        int[] c = new int[5];
        int index;
        for (int i = 0;i<b.length;i++){
            index = b.length-1 - i;
            c[i] = b[index];

        }

        for (int j = 0;j<c.length;j++){
            System.out.println(c[j]);
        }

        /*//打印单数
        outloop: //outloop这个标示是可以自定义的比如outloop1,ol2,out5
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                System.out.println(i+":"+j);
                if(0==j%2)
                    break outloop; //如果是双数，结束外部循环
            }

        }*/
//        for (int i=1;i<100;i++){
//            if (i%3==0){
//                continue;
//            }else if (i%5==0){
//                continue;
//            }else {
//                System.out.println(i);
//            }
//        }
    }
}
