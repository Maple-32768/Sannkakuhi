package main;

import java.util.Random;

public class Sankakuhi {
	Random random = new Random();
	
	double[] type_d = new double[2],angle_d = new double[4];
	int type_i[] = new int[2],angle_i[] = new int[4],rand;
	String[] type = {"sin","cos","tan","cosec","sec","cot"},
			sin = {"0",null,"1/2","1/√2","√3/2",null,"1"},
			cos = {"1",null,"√3/2","1/√2","1/2",null,"0"},
			tan = {"0",null,"1/√3","1","√3",null,"なし"},
			cosec = {"なし",null,"2","√2","2/√3",null,"1"},
			sec = {"1",null,"2/√3","√2","2",null,"なし"},
			cot = {"なし",null,"√3","1","1/√3",null,"0"};

	
	Sankakuhi() {
		this.type_i = new int[]{1,1};
		this.angle_i = new int[]{1,1,1,1};
	}
	
	void SetTypes(double type_1,double type_2) {
		this.type_d[0] = type_1;
		this.type_d[1] = type_2;
		double k = 100 / (type_d[0] + type_d[1]);
		for(int i = 0; i < 2; i++) {
			type_i[i] = (int) (type_d[i] * k);
			if(type_d[i] * k - type_i[i] >= 0.5) {
				type_i[i]++;
			}
		}
	}
	
	void SetAngles(double angle_1,double angle_2,double angle_3,double angle_4) {
		this.angle_d[0] = angle_1;
		this.angle_d[1] = angle_2;
		this.angle_d[2] = angle_3;
		this.angle_d[3] = angle_4;
		double k = 100 / (angle_d[0] + angle_d[1] + angle_d[2] + angle_d[3]);
		for(int i = 0; i < 4; i++) {
			angle_i[i] = (int) (angle_d[i] * k);
			if(angle_d[i] * k - angle_i[i] >= 0.5) {
				angle_i[i]++;
			}
		}
	}
	
	String Select_Type() {
		rand = random.nextInt(type_i[0]+type_i[1]);
		int rand_type = random.nextInt(3);
		
		if((rand >= 0)&&(rand <= type_i[0]-1)) {

		}else if((type_i[0] <= rand)&&(rand <= type_i[0] + type_i[1]-1)){
			rand_type = rand_type+3;
		}
		return type[rand_type];
	}
	
	int Select_Angle() {
		rand = random.nextInt(angle_i[0]+angle_i[1]+angle_i[2]+angle_i[3]);
		int rand_angle;
		
		do{
			rand_angle = random.nextInt(23);	
		}while(rand_angle!=0 && rand_angle%2 != 0 && rand_angle%3 != 0);
		
		rand_angle=rand_angle*15;
		
		if((rand >= 0)&&(rand <= angle_i[0]-1)) {
			rand_angle=rand_angle*-1-360;
		}else if((angle_i[0] <= rand)&&(rand <= angle_i[0] + angle_i[1]-1)){
			rand_angle*=-1;
		}else if((angle_i[0]+angle_i[1] <= rand)&&(rand <= angle_i[0] + angle_i[1] + angle_i[2] -1)){
		  
		}else if((angle_i[0]+angle_i[1] + angle_i[2] <= rand)&&(rand <= angle_i[0] + angle_i[1] + angle_i[2] + angle_i[3] -1)){
		  rand_angle+=360;
		}
		return rand_angle;
	}
	
	String Answer(String type, int angle) {
		int l;
		String result = "";
		int minus_i = 0;

		
		//360°以内にする
		while(angle >= 360) {
			angle-=360;
		}
		while(angle < 0) {
			angle+=360;
		}
		
		//180°以内にする
		if(angle >= 180) {
			angle-=180;
			if(type != this.type[2] && type != this.type[5]) {	//tan,cot以外
				minus_i += 1;
			}
		}
		
		//90°以内にする
		if(angle > 90) {
			angle = 180-angle;
			if(type != this.type[0] && type != this.type[3]) {	//sin,cosec以外
				minus_i += 1;
			}
		}
		
		l = angle/15;

		if(type==this.type[0]) {
			result = sin[l];
		}else if(type==this.type[1]) {
			result = cos[l];
		}else if(type==this.type[2]) {
			result = tan[l];
		}else if(type==this.type[3]) {
			result = cosec[l];
		}else if(type==this.type[4]) {
			result = sec[l];
		}else if(type==this.type[5]) {
			result = cot[l];
		}
		
		if(minus_i%2!=0 && result != "0") {
			result = "-" + result;
		}
		return result;
	}
	
}
