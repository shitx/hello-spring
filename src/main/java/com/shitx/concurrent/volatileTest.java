package com.shitx.concurrent;

public class volatileTest extends Thread{
  private static boolean flag = false;  //volatile的作用：  1  线程必须去主内存读取volatile修饰的变量。 2 线程修改了自己工作内存的副本，立刻写回主存  3 volatile无法保证原子性。多线程环境下的同一个变量的同步不能采用volatile,应该使用sychronize
    
  //无限循环,等待flag变为true时才跳出循环
   public void run() {
	   while (!flag){
		   System.out.print("xx");
	   };
	   }
      
  public static void main(String[] args) throws Exception {
      new volatileTest().start();
          //sleep的目的是等待线程启动完毕,也就是说进入run的无限循环体了
     Thread.sleep(100);
        flag = true;
     }
 }