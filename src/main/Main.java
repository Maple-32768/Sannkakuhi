package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//SystemVariable
		String[] type_s = {"メジャーな三角比(sin,cos,tan)","マイナーな三角比(cosec,sec,cot)"},
				angle_s={"-720° ~ -361°","-360° ~ -1°","0° ~ 359°","360° ~ 719°"}; 
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("出力フォルダを指定してください。");
		try {
			String path_input = scan.nextLine();
			FileWriter question_file = new FileWriter(path_input+"\\Question.txt");
			PrintWriter question_pw = new PrintWriter(new BufferedWriter(question_file));
			
			FileWriter answer_file = new FileWriter(path_input+"\\Answer.txt");
			PrintWriter answer_pw = new PrintWriter(new BufferedWriter(answer_file));
			
			Sankakuhi question = new Sankakuhi();
			
			double[] type_input = new double[2];
			for(int i = 0; i < 2; i++ ) {
				System.out.println(type_s[i] + "の確率を入力してください。");
				type_input[i] = scan.nextDouble();
			}
			question.SetTypes(type_input[0],type_input[1]);
			
			double[] angle_input = new double[4];
			for(int i = 0; i < 4; i++ ) {
				System.out.println(angle_s[i] + "の範囲の確率を入力してください。");
				angle_input[i] = scan.nextDouble();
			}
			question.SetAngles(angle_input[0], angle_input[1], angle_input[2], angle_input[3]);
			
			System.out.println("問題数を入力してください。");
			int loop_count = scan.nextInt();
			
			for(int i =0; i < loop_count; i++) {
				String type = question.Select_Type();
				int angle = question.Select_Angle();
				
				if(angle < 0) {
					question_pw.println("("+(i+1)+") "+type+" ("+angle+"°) = ");
					answer_pw.println("("+(i+1)+") "+question.Answer(type, angle));
				}else {
					question_pw.println("("+(i+1)+") "+type+" "+angle+"° = ");
					answer_pw.println("("+(i+1)+") "+question.Answer(type, angle));
				}
				
			}
			
			question_pw.close();
			answer_pw.close();
		} catch (IOException e) {
			System.out.println("[Error] Cannot access the folder.");
			e.printStackTrace();
		}
		scan.close();
	}

}
