package com.home.tv.widget;

import android.view.KeyEvent;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @ClassName TvRecyclerViewTest
 * @Description TODO
 * @Author Administrator
 * @Date 2021/4/12 13:35
 * @Version 1.0
 */
public class TvRecyclerViewTest {

    @Test
    public void getDirectionByKeyCode() {

        System.out.println("KeyEvent.KEYCODE_DPAD_UP , HORIZONTAL");
        int result = c(19, 0);
        int result2 = getDirect(19, 0);
        System.out.println("result:" + result);
        System.out.println("result2:" + result2);

        System.out.println("KeyEvent.KEYCODE_DPAD_UP , VERTICAL");
        int result3 = c(19, 1);
        int result4 = getDirect(19, 1);
        System.out.println("result3:" + result3);
        System.out.println("result4:" + result4);

        System.out.println("KeyEvent.KEYCODE_DPAD_DOWN , HORIZONTAL");
        int result5 = c(20, 0);
        int result6 = getDirect(20, 0);
        System.out.println("result5:" + result5);
        System.out.println("result6:" + result6);

        System.out.println("KeyEvent.KEYCODE_DPAD_DOWN , VERTICAL");
        int result7 = c(20, 1);
        int result8 = getDirect(20, 1);
        System.out.println("result7:" + result7);
        System.out.println("result8:" + result8);


        System.out.println("KeyEvent.KEYCODE_DPAD_LEFT , HORIZONTAL");
        int result9 = c(21, 0);
        int result10 = getDirect(21, 0);
        System.out.println("result9:" + result9);
        System.out.println("result10:" + result10);

        System.out.println("KeyEvent.KEYCODE_DPAD_LEFT , VERTICAL");
        int result11 = c(21, 1);
        int result12 = getDirect(21, 1);
        System.out.println("result11:" + result11);
        System.out.println("result12:" + result12);


        System.out.println("KeyEvent.KEYCODE_DPAD_RIGHT , HORIZONTAL");
        int result13 = c(22, 0);
        int result14 = getDirect(22, 0);
        System.out.println("result13:" + result13);
        System.out.println("result14:" + result14);

        System.out.println("KeyEvent.KEYCODE_DPAD_RIGHT , VERTICAL");
        int result15 = c(22, 1);
        int result16 = getDirect(22, 1);
        System.out.println("result15:" + result15);
        System.out.println("result16:" + result16);


        System.out.println("KeyEvent.KEYCODE_DPAD_CENTER , HORIZONTAL");
        int result17 = c(23, 0);
        int result18 = getDirect(23, 0);
        System.out.println("result17:" + result17);
        System.out.println("result18:" + result18);

        System.out.println("KeyEvent.KEYCODE_DPAD_CENTER , VERTICAL");
        int result19 = c(23, 1);
        int result20 = getDirect(23, 1);
        System.out.println("result19:" + result19);
        System.out.println("result20:" + result20);
    }

    //实测这个方法反编译出来有问题
    public final int a(int paramInt, int i) {
        byte b1 = 0;
        if (i == 0) {
            switch (paramInt) {
                default:
                    b1 = 17;
                    break;
                case 20:
                    b1 = 3;
                    break;
                case 19:
                    b1 = 2;
                    break;
                case 22:
                    b1 = 1;
                    break;
                case 21:
                    break;
            }
        } else if (i == 1) {
            switch (paramInt) {
                default:

                case 22:
                case 21:
                case 20:

                case 19:
                    break;
            }
            return b1;
        }
        return b1;
    }


    public final int b(int keyCode, int i) {


        byte var3;
        label64:
        {
            label65:
            {
                label66:
                {
                    var3 = 0;
                    if (i == 0) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_DPAD_UP:
                                break label65;   //1
                            case KeyEvent.KEYCODE_DPAD_DOWN:
                                break label64;   //2
                            case KeyEvent.KEYCODE_DPAD_LEFT:
                                return var3;     //0
                            case KeyEvent.KEYCODE_DPAD_RIGHT:
                                break label66;   //17
                        }
                    } else if (i == 1) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_DPAD_UP:
                                return var3;    //0
                            case KeyEvent.KEYCODE_DPAD_DOWN:
                                break label66;  //17
                            case KeyEvent.KEYCODE_DPAD_LEFT:
                                break label65;  //1
                            case KeyEvent.KEYCODE_DPAD_RIGHT:
                                break label64;  //2
                        }
                    }

                    var3 = 17;
                    return var3;
                }

                var3 = 1;
                return var3;
            }

            var3 = 2;
            return var3;
        }

        var3 = 3;
        return var3;
    }

    public final int c(int var1, int i) {
        byte var3;
        label64:
        {
            label65:
            {
                label66:
                {
                    var3 = 0;
                    if (i == 0) {
                        switch (var1) {
                            case 19:
                                break label65;
                            case 20:
                                break label64;
                            case 21:
                                return var3;
                            case 22:
                                break label66;
                        }
                    } else if (i == 1) {
                        switch (var1) {
                            case 19:
                                return var3;
                            case 20:
                                break label66;
                            case 21:
                                break label65;
                            case 22:
                                break label64;
                        }
                    }

                    var3 = 17;
                    return var3;
                }

                var3 = 1;
                return var3;
            }

            var3 = 2;
            return var3;
        }

        var3 = 3;
        return var3;
    }

    public final int getDirect(int keyCode, int orientation) {
        if (orientation == 0) {
            switch (keyCode) {
                case 19:
                    return 2;
                case 20:
                    return 3;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    return 0;
                case 22:
                    return 1;
            }
        } else if (orientation == 1) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_UP:
                    return 0;
                case 20:
                    return 1;
                case 21:
                    return 2;
                case 22:
                    return 3;
            }
        } else {
            throw new IllegalArgumentException("un support orientation:" + orientation);
        }
        return 17;
    }
}