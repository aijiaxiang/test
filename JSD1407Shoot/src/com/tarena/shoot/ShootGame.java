package com.tarena.shoot;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/** 游戏主界面 */
public class ShootGame extends JPanel {
	public static final int WIDTH = 400; //宽
	public static final int HEIGHT = 654;//高
	
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage gameover;
	public static BufferedImage pause;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	
	public Hero hero = new Hero(); //英雄机对象
	public Bullet[] bullets = {};  //子弹数组
	public FlyingObject[] flyings = {}; //敌机和小蜜蜂

	static{  //加载图片资源
		try {
			background = 
				ImageIO.read(
						ShootGame.class.getResource("background.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 重写绘制方法 */
	public void paint(Graphics g){ //g为画笔
		g.drawImage(background,0,0,null); //画图片
		paintHero(g);    //画英雄机
		paintBullets(g);    //画子弹
		paintFlyingObjects(g); //画敌人(敌机和小蜜蜂)
	}
	/** 画英雄机 */
	public void paintHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);
	}
	/** 画子弹 */
	public void paintBullets(Graphics g){
		for(int i=0;i<bullets.length;i++){
			Bullet b = bullets[i]; //每个子弹对象
			g.drawImage(b.image,b.x,b.y,null);
		}
	}
	/** 画敌人 */
	public void paintFlyingObjects(Graphics g){
		for(int i=0;i<flyings.length;i++){
			FlyingObject f = flyings[i];//每一个敌人
			g.drawImage(f.image,f.x,f.y,null);
		}
	}
	
	
	
	private Timer timer;  //定时器
	private int interval = 10; //时间间隔(毫秒)
	/** 启动执行动操作 */
	public void action(){
		//鼠标事件适配器
		MouseAdapter l = new MouseAdapter(){
			/** 重写mouseMoved(鼠标移动)方法 */
			//当鼠标移动的时候修改英雄机的x,y坐标
			public void mouseMoved(MouseEvent e){
				int x = e.getX(); //得到鼠标的x
				int y = e.getY(); //得到鼠标的y
				hero.moveTo(x, y); //英雄机移动
			}
		};
		//给当前面板添加鼠标滑动侦听
		this.addMouseMotionListener(l);
		
		
		
		timer = new Timer(); //创建定时器对象
		//如下代码，每隔10毫秒执行一次run()方法
		timer.schedule(new TimerTask(){ //定时触发
			//重写run方法
			public void run(){ //定时执行的方法
				enterAction(); //飞行物入场---new对象
				stepAction();//飞行物走步
				shootAction();  //射击(子弹入场)
				bangAction(); //子弹打敌人
				repaint();   //重绘(调用paint方法)
			}
		},interval,interval);
	}
	
	/** 子弹打敌人 */
	public void bangAction(){
		
	}
	
	
	
	
	
	int shootIndex = 0;  //控制射击频率
	/** 射击---子弹入场 */
	public void shootAction(){
		shootIndex++;
		if(shootIndex % 30 ==0){ //300毫秒发一次
			Bullet[] bs = hero.shoot(); //英雄机发射出来的
			bullets = Arrays.copyOf
			    (bullets, bullets.length+bs.length);//扩容
			System.arraycopy
			(bs,0,bullets,
					bullets.length-bs.length,bs.length);//追加
		}
	}
	
	
	
	int flyEnteredIndex = 0; //飞行物入场计数
	/** 飞行物(敌机和小蜜蜂)入场 */
	//如下代码，每400毫秒生成一个敌机或小蜜蜂
	//并装到数组中
	public void enterAction(){ //每10毫秒走一步
		flyEnteredIndex++; //走一步index增1
		if(flyEnteredIndex%40==0){//走40次，%40为0----400毫秒
			FlyingObject obj = nextOne();//随机生成一个对象
			flyings = Arrays.copyOf(flyings,flyings.length+1); //扩容
			flyings[flyings.length-1] = obj; //将敌人装到数组最后一位
		}
	}

	/** 随机生成敌人(敌机，蜜蜂) */
	//工厂方法:生产对象，一般为static的
	public static FlyingObject nextOne(){
		Random rand = new Random();
		int type = rand.nextInt(20);//[0,19]
		if(type == 0){
			return new Bee();
		}else{
			return new Airplane();
		}
	}
	
	
	
	/** 飞行物走步 */
	public void stepAction(){
		//敌人(敌机和小蜜蜂)走步
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();
		}
		//子弹走步
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();
		}
		//飞机走步----换图片
		hero.step();
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Fly"); //窗口--画框
		ShootGame game = new ShootGame(); //面板对象
		frame.add(game); //将面板加到窗口中
		
		frame.setSize(WIDTH, HEIGHT); //大小
		frame.setAlwaysOnTop(true);//总在最上
		frame.setDefaultCloseOperation
		       (JFrame.EXIT_ON_CLOSE);//默认关闭
		frame.setLocationRelativeTo(null); //初始位置

		frame.setVisible(true);//显示-尽快调用paint方法
		
		game.action();  //界面动起来
	}
	

	
}




